package us.kanddys.laia.modules.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.ShoppingCartProductDTO;
import us.kanddys.laia.modules.ecommerce.exception.ShoppingCartNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.ShoppingCartProduct;
import us.kanddys.laia.modules.ecommerce.repository.ShoppingCartJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ShoppingCartProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.ShoppingCartProductService;

/**
 * Esta clase implementa las obligaciones de la interface
 * ShoppingCartProductService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class ShoppingCartProductServiceImpl implements ShoppingCartProductService {

   @Autowired
   private ShoppingCartProductJpaRepository shoppingCartProductJpaRepository;

   @Autowired
   private ShoppingCartJpaRepository shoppingCartJpaRepository;

   @Override
   public ShoppingCartProductDTO addShoppingCartProduct(Long shoppingCartId, Long productId) {
      if (shoppingCartJpaRepository.existByShoppingCartId(shoppingCartId) == null)
         throw new ShoppingCartNotFoundException(ExceptionMessage.SHOPPING_CART_NOT_FOUND);
      return new ShoppingCartProductDTO(
            shoppingCartProductJpaRepository.save(new ShoppingCartProduct(shoppingCartId, productId)));
   }
}
