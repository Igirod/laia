package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.Date;

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
 * @version 1.0.1
 */
@Data
@AllArgsConstructor
public class ShoppingCartDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private Long invoiceId;
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
   public ShoppingCartDTO(Long id, Long merchantId, Long invoiceId, Date createAt, Integer count) {
      super();
      this.id = (id == null) ? null : id;
      this.merchantId = (merchantId == null) ? null : merchantId;
      this.invoiceId = (invoiceId == null) ? null : invoiceId;
      this.createAt = (createAt == null) ? null
            : DateUtils.convertDateToString(createAt);
      this.count = (count == null) ? 0 : count;
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
      this.invoiceId = (shoppingCart.getInvoiceId() == null) ? null : shoppingCart.getInvoiceId();
      this.createAt = (shoppingCart.getCreateAt() == null) ? null
            : DateUtils.convertDateToString(shoppingCart.getCreateAt());
      this.count = (shoppingCart.getCount() == null) ? 0 : shoppingCart.getCount();
   }
}
