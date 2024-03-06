package us.kanddys.laia.modules.ecommerce.services.check.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.MerchantJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.check.InvoiceCheckService;

/**
 * Esta clase implementa las obligaciones de la interfaz
 * InvoiceCheckService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class InvoiceCheckServiceImpl implements InvoiceCheckService {

   @Autowired
   private MerchantJpaRepository merchantJpaRepository;

   @Autowired
   private InvoiceJpaRepository invoiceJpaRepository;

   @Override
   public Boolean existsMerchantId(Long merchantId) {
      return (merchantJpaRepository.existByMerchantId(merchantId) == null) ? false : true;
   }

   @Override
   public Boolean existInvoiceId(Long invoiceId) {
      return (invoiceJpaRepository.existByInvoiceId(invoiceId) == null) ? false : true;
   }

   @Override
   public Boolean existsPaymentId(Long paymentId) {
      return false;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public void updateTotal(Long invoiceId, Double total) {
      invoiceJpaRepository.updateTotal(invoiceId, total);
   }
}
