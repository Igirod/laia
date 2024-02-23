package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.ShoppingCartDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ShoppingCartProductDTO;
import us.kanddys.laia.modules.ecommerce.services.ShoppingCartService;

@Controller
public class ShoppingCartController {

   @Autowired
   private ShoppingCartService shoppingCartService;

   @QueryMapping("gCart")
   public ShoppingCartDTO getCart(@Argument Long merchantId, @Argument Long invoiceId) {
      return shoppingCartService.getCart(merchantId, invoiceId);
   }

   @QueryMapping("lSCartP") 
   public List<ShoppingCartProductDTO> getShoppingCartProducts(@Argument Long shoppingCartId) {
      return shoppingCartService.getShoppingCartProducts(shoppingCartId);
   }
}
