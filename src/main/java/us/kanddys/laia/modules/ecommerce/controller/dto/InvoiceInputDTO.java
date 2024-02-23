package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;

/**
 * Esta clase representa el data transfer object (DTO) que se utiliza para la entrada 
 * de datos de la creación de una factura.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class InvoiceInputDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private Long userId;
   @JsonProperty
   private Long paymentId;
   @JsonProperty
   private String code;
   @JsonProperty
   private Long shoppingCartId;
   @JsonProperty
   private String reservation;
   @JsonProperty
   private Float total;
   @JsonProperty
   private Boolean message;
   @JsonProperty
   private InvoiceStatus status;
   @JsonProperty
   private String voucher;
   @JsonProperty
   private String note;

   public InvoiceInputDTO() {}
}
