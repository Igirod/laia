package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.ShoppingCart;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;

/**
 * Esta clase representa un Date transfer object (DTO) para el carrito de
 * compras.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class ShoppingCartDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private Long userId;
   @JsonProperty
   private Integer count;
   @JsonProperty
   private String createAt;

   public ShoppingCartDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param shoppingCart
    */
   public ShoppingCartDTO(ShoppingCart shoppingCart) {
      super();
      this.id = (shoppingCart.getId() == null) ? null : shoppingCart.getId();
      this.merchantId = (shoppingCart.getMerchantId() == null) ? null : shoppingCart.getMerchantId();
      this.userId = (shoppingCart.getUserId() == null) ? null : shoppingCart.getUserId();
      this.count = (shoppingCart.getCount() == null) ? null : shoppingCart.getCount();
      this.createAt = (shoppingCart.getCreateAt() == null) ? null
            : DateUtils.convertDateToString(shoppingCart.getCreateAt());
   }
}
