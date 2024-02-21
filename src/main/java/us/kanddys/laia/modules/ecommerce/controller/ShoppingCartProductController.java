package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.ShoppingCartProductDTO;
import us.kanddys.laia.modules.ecommerce.services.ShoppingCartProductService;

@Controller
public class ShoppingCartProductController {

   @Autowired
   private ShoppingCartProductService shoppingCartProductService;

   @MutationMapping("aSCartP")
   public ShoppingCartProductDTO addShoppingCartProduct(@Argument Long shoppingCartId, @Argument Long productId) {
      return shoppingCartProductService.addShoppingCartProduct(shoppingCartId, productId);
   }
}
