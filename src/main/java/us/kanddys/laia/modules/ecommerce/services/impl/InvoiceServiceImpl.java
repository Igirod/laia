package us.kanddys.laia.modules.ecommerce.services.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceInputDTO;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceCheckCodeException;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Invoice;
import us.kanddys.laia.modules.ecommerce.model.User;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.InvoiceService;
import us.kanddys.laia.modules.ecommerce.services.check.InvoiceCheckService;
import us.kanddys.laia.modules.ecommerce.services.check.ProductCheckStockService;
import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

/**
 * Esta clase implementa las obligaciones de la interface InvoiceService.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

   @Autowired
   private InvoiceCriteriaRepository invoiceCriteriaRepository;

   @Autowired
   private InvoiceJpaRepository invoiceJpaRepository;

   @Autowired
   private ProductCheckStockService productCheckStockService;

   @Autowired
   private InvoiceCheckService invoiceCheckService;

   @Autowired
   private FirebaseStorageService firebaseStorageService;

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Autowired
   private InvoiceProductJpaRepository invoiceProductJpaRepository;

   @Autowired
   private InvoiceProductCriteriaRepository invoiceProductCriteriaRepository;

   @Override
   public List<InvoiceDTO> findInvoicesByMerchantIdAndOptionalParamsPaginated(Integer page, Long merchantId,
         Optional<String> userEmail, Optional<InvoiceStatus> status) {
      return invoiceCriteriaRepository.findInvoicesPaginated(page, merchantId, userEmail, status).stream().map(t -> {
         try {
            return new InvoiceDTO(t);
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         }
      }).collect(Collectors.toList());
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public InvoiceDTO createInvoice(Long merchantId) {
      var userNew = userJpaRepository.save(new User());
      try {
         return new InvoiceDTO(invoiceJpaRepository.save(new Invoice(userNew.getId(), merchantId)));
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      } catch (ParseException e) {
         throw new RuntimeException(e);
      }
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public InvoiceDTO updateInvoice(InvoiceInputDTO invoiceInputDTO) {
      if (invoiceCheckService.existsMerchantId(invoiceInputDTO.getMerchantId())) {
         var updateInvoice = invoiceJpaRepository.findById(invoiceInputDTO.getId());
         if (updateInvoice.isPresent()) {
            try {
               updateInvoice = Optional.of(new Invoice(invoiceInputDTO.getId(), invoiceInputDTO,
                     invoiceProductJpaRepository.countByInvoiceId(invoiceInputDTO.getId())));
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

   @Override
   public InvoiceDTO findInvoiceById(Long invoiceId) {
      var invoice = invoiceJpaRepository.findById(invoiceId);
      if (invoice.isEmpty())
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      try {
         invoice.get().setCount(
               invoiceProductJpaRepository.countByInvoiceId(invoice.get() == null ? 0 : invoice.get().getId()));
         return new InvoiceDTO(invoice.get());
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceMessage(Long invoiceId, Integer message) {
      if (invoiceJpaRepository.existsById(invoiceId) == false)
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      invoiceJpaRepository.updateMessageByInvoiceId(message, invoiceId);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoicePayment(Long invoiceId, Long paymentId) {
      if (invoiceJpaRepository.existsById(invoiceId) == false)
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      invoiceJpaRepository.updatePaymentByInvoiceId(paymentId, invoiceId);
      invoiceJpaRepository.updateStatusByInvoiceId(InvoiceStatus.PENDING.toString(), invoiceId);
      var invoiceProducts = invoiceProductCriteriaRepository.findInvoiceProductsByInvoiceId(invoiceId);
      invoiceProducts.stream().map(t -> {
         productCheckStockService.checkStock(t.getProduct().getId(), t.getProduct().getStock(), t.getQuantity());
         return t;
      }).collect(Collectors.toList());
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceNote(Long invoiceId, String note) {
      if (invoiceJpaRepository.existsById(invoiceId) == false)
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      invoiceJpaRepository.updateNoteByInvoiceId(note, invoiceId);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceStatus(Long invoiceId, InvoiceStatus status) {
      if (invoiceJpaRepository.existsById(invoiceId) == false)
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      invoiceJpaRepository.updateStatusByInvoiceId(status.toString(), invoiceId);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceVoucher(MultipartFile voucher, Long invoiceId) {
      if (invoiceJpaRepository.existsById(invoiceId) == false)
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      invoiceJpaRepository.updateVoucherByInvoiceId(firebaseStorageService.uploadFile(voucher), invoiceId);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceAddress(Long invoiceId, String title, String direction) {
      if (invoiceJpaRepository.existsById(invoiceId) == false)
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      invoiceJpaRepository.updateAddressByInvoiceId(title, direction, invoiceId);
      return 1;
   }
}
