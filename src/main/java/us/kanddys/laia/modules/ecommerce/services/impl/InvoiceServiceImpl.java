package us.kanddys.laia.modules.ecommerce.services.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceInputDTO;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceCheckCodeException;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Invoice;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.InvoiceCodeService;
import us.kanddys.laia.modules.ecommerce.services.InvoiceService;
import us.kanddys.laia.modules.ecommerce.services.check.InvoiceCheckService;

/**
 * Esta clase implementa las obligaciones de la interface InvoiceService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

   @Autowired
   private InvoiceCriteriaRepository invoiceCriteriaRepository;

   @Autowired
   private InvoiceJpaRepository invoiceJpaRepository;

   @Autowired
   private InvoiceCodeService invoiceCodeService;

   @Autowired
   private InvoiceCheckService invoiceCheckService;

   @Override
   public List<InvoiceDTO> findInvoicesByMerchantIdAndOptionalParamsPaginated(Integer page, Long merchantId,
         Optional<String> userEmail, Optional<InvoiceStatus> status) {
      return invoiceCriteriaRepository.findinvoicesPaginated(page, merchantId, userEmail, status).stream().map(t -> {
         try {
            return new InvoiceDTO(t);
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         }
      }).collect(Collectors.toList());
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public InvoiceDTO createInvoice(InvoiceInputDTO invoiceInputDTO) {
      if (invoiceCheckService.checkInvoiceData(invoiceInputDTO.getMerchantId(), invoiceInputDTO.getShoppingCartId(),
            invoiceInputDTO.getUserEmail())) {
         try {
            return invoiceCodeService
                  .generateInvoiceCode(new InvoiceDTO(invoiceJpaRepository.save(new Invoice(invoiceInputDTO))));
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         } catch (ParseException e) {
            throw new RuntimeException(e);
         }
      } else {
         throw new InvoiceCheckCodeException(ExceptionMessage.INOICE_CHECK_SERVICE);
      }
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public InvoiceDTO updateInvoice(InvoiceInputDTO invoiceInputDTO) {
      if (invoiceCheckService.checkInvoiceData(invoiceInputDTO.getMerchantId(), invoiceInputDTO.getShoppingCartId(),
            invoiceInputDTO.getUserEmail())) {
         var updateInvoice = invoiceJpaRepository.findById(invoiceInputDTO.getId());
         if (updateInvoice.isPresent()) {
            try {
               updateInvoice = Optional.of(new Invoice(invoiceInputDTO.getId(), invoiceInputDTO));
               return Optional.of(new InvoiceDTO(invoiceJpaRepository.save(updateInvoice.get()))).get();
            } catch (ParseException e) {
               throw new RuntimeException(e);
            } catch (IOException e) {
               throw new IOJavaException(e.getMessage());
            }
         } else {
            throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
         }
      }
      throw new InvoiceCheckCodeException(ExceptionMessage.INOICE_CHECK_SERVICE);
   }
}