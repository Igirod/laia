package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Payment;
import us.kanddys.laia.modules.ecommerce.model.Utils.TypePayment;

@Data
@AllArgsConstructor
public class PaymentDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private TypePayment typePayment;
   @JsonProperty
   private String title;
   @JsonProperty
   private String cvu;

   public PaymentDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param payment
    */
   public PaymentDTO(Payment payment) {
      super();
      this.id = payment.getId();
      this.typePayment = payment.getTypePayment();
      this.title = payment.getTitle();
      this.cvu = payment.getCvu();
   }
}
