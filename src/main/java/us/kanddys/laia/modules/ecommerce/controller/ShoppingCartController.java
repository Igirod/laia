package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.ShoppingCartDTO;
import us.kanddys.laia.modules.ecommerce.services.ShoppingCartService;

@Controller
public class ShoppingCartController {

   @Autowired
   private ShoppingCartService shoppingCartService;

   @QueryMapping("gCart")
   public ShoppingCartDTO getCart(@Argument Long merchantId, @Argument Long userId) {
      return shoppingCartService.getCart(merchantId, userId);
   }
}
