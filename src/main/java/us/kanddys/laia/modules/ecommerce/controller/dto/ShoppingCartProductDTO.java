package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.ShoppingCartProduct;

/**
 * Esta clase representa un data transfer object (DTO) de un producto en el
 * carrito de compras.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class ShoppingCartProductDTO {
   @JsonProperty
   private Long shoppingCartId;
   @JsonProperty
   private Long productId;
   @JsonProperty
   private Integer quantity;

   public ShoppingCartProductDTO() {
   }

   /**
    * Constructor personlizado utilizado en diferentes servicios.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param shoppingCartProduct
    */
   public ShoppingCartProductDTO(ShoppingCartProduct shoppingCartProduct) {
      this.shoppingCartId = shoppingCartProduct.getId().getShoppingCartId();
      this.productId = shoppingCartProduct.getId().getProductId();
      this.quantity = shoppingCartProduct.getQuantity();
   }

}
