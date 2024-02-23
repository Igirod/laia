package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoiceProductInputDTO {
   @JsonProperty
   private Long shoppingCartId;
   @JsonProperty
   private Long productId;
   @JsonProperty
   private Integer quantity;
}
