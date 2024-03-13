package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa un data transfer object (DTO) de una pregunta
 * relacionada a un comerciante.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class SellerQuestionDTO {

   @JsonProperty
   private Long id;
   @JsonProperty
   private String question;
   @JsonProperty
   private String type;
   @JsonProperty
   private Integer required;
   @JsonProperty
   private Integer limit;

   public SellerQuestionDTO() {
   }
}
