package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoicePaymentDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceProductDTO;
import us.kanddys.laia.modules.ecommerce.exception.OrderNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.services.InvoiceService;
import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

/**
 * Esta clase implementa las obligaciones de la interface InvoiceService.
 * 
 * @author Igirod0
 * @version 1.0.2
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

   @Autowired
   private InvoiceJpaRepository invoiceJpaRepository;

   @Autowired
   private FirebaseStorageService firebaseStorageService;

   @Autowired
   private InvoiceProductCriteriaRepository invoiceProductCriteriaRepository;

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceStatus(Long id, String status) {
      invoiceJpaRepository.updateInvoiceStatus(id, status);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceNote(Long id, String status) {
      invoiceJpaRepository.updateInvoiceNote(id, status);
      return 1;
   }

   @Override
   public InvoiceDTO getInvoiceById(Long invoiceId) {
      var order = invoiceJpaRepository.findById(invoiceId).orElse(null);
      if (order == null)
         throw new OrderNotFoundException(ExceptionMessage.ORDER_NOT_FOUND);
      var listProducts = invoiceProductCriteriaRepository.findInvoiceProductsByInvoiceId(invoiceId).stream()
            .map(InvoiceProductDTO::new)
            .collect(Collectors.toList());
      return new InvoiceDTO(order, listProducts);
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public InvoicePaymentDTO updateInvoiceVoucher(MultipartFile voucher, Long invoiceId) {
      var voucherUrl = firebaseStorageService.uploadFile(voucher, "invoice-voucher-" + invoiceId.toString(),
            "vouchers");
      invoiceJpaRepository.updateVoucherByInvoiceId(voucherUrl, invoiceId);
      // * Se utiliza este tipo de DTO para no crear uno nuevo.
      return new InvoicePaymentDTO(voucherUrl, null, null);
   }
}
