package us.kanddys.laia.modules.ecommerce.services.check.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.repository.MerchantJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ShoppingCartJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
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
   private UserJpaRepository userJpaRepository;

   @Autowired
   private ShoppingCartJpaRepository shoppingCartJpaRepository;

   @Override
   public Boolean existsMerchantId(Long merchantId) {
      return (merchantJpaRepository.existByMerchantId(merchantId) == null) ? false : true;
   }

   @Override
   public Boolean existsUserEmail(String userEmail) {
      return (userJpaRepository.existByUserEmail(userEmail) == null) ? false : true;
   }

   @Override
   public Boolean existsShoppingCartId(Long shoppingCartId) {
      return (shoppingCartJpaRepository.existByShoppingCartId(shoppingCartId) == null) ? false : true;
   }

   @Override
   public Boolean checkInvoiceData(Long merchantId, Long shoppingCartId, String userEmail) {
      return (existsMerchantId(merchantId) && existsShoppingCartId(shoppingCartId) && existsUserEmail(userEmail) ? true : false);
   }

   @Override
   public Boolean existsPaymentId(Long paymentId) {
      return false;
   }
}
