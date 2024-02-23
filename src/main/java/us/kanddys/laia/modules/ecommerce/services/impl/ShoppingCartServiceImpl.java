package us.kanddys.laia.modules.ecommerce.services.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ShoppingCartDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ShoppingCartProductDTO;
import us.kanddys.laia.modules.ecommerce.model.ShoppingCart;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.repository.ShoppingCartJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ShoppingCartProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.ShoppingCartService;

/**
 * Esta clase se encarga de implementar las obligaciones de la interface
 * ShoppingCartService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

   @Autowired
   private ShoppingCartJpaRepository shoppingCartJpaRepository;

   @Autowired
   private ShoppingCartProductJpaRepository shoppingCartProductJpaRepository;

   @Override
   public ShoppingCartDTO getCart(Long merchantId, Long invoiceId) {
      var shoppingCart = shoppingCartJpaRepository.existByMerchantIdAndUserId(merchantId, invoiceId);
      if (shoppingCart.isPresent())
         return new ShoppingCartDTO(shoppingCart.get().getId(), shoppingCart.get().getMerchantId(),
               shoppingCart.get().getInvoiceId(), shoppingCart.get().getCreateAt(),
               shoppingCartProductJpaRepository.countByShoppingCartId(shoppingCart.get().getId()));
      else
         try {
            return new ShoppingCartDTO(shoppingCartJpaRepository.save(new ShoppingCart(null, merchantId, invoiceId,
                  DateUtils.convertStringToDate(DateUtils.getCurrentDateString()), 0)));
         } catch (ParseException e) {
            throw new RuntimeException("Error al convertir la fecha en la clase ShoppingCartServiceImpl.");
         }
   }

   @Override
   public List<ProductDTO> getShoppingCartProducts(Long shoppingCartId) {
      
   }
}
