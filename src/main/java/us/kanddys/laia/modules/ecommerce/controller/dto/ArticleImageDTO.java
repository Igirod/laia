package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ArticleImageDTO {
   @JsonProperty
   private String url;
   @JsonProperty
   private String type;

   public ArticleImageDTO() {
   }
}
