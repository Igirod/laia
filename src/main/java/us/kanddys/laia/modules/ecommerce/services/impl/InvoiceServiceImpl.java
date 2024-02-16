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
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Invoice;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.InvoiceService;

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

   @Override
   public List<InvoiceDTO> findInvoicesByMerchantEmailPaginated(Integer page, String merchantEmail,
         Optional<String> userEmail, Optional<InvoiceStatus> status) {
      return invoiceCriteriaRepository.findinvoicesPaginated(page, merchantEmail, userEmail, status).stream().map(t -> {
         try {
            return new InvoiceDTO(t);
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         }
      }).collect(Collectors.toList());
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
      try {
         return new InvoiceDTO(invoiceJpaRepository.save(new Invoice(invoiceDTO)));
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      } catch (ParseException e) {
         throw new RuntimeException(e);
      }
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO) {
      var updateInvoice = invoiceJpaRepository.findById(invoiceDTO.getId());
      if (updateInvoice.isPresent()) {
         try {
            updateInvoice = Optional.of(new Invoice(invoiceDTO.getId(), invoiceDTO));
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
}
