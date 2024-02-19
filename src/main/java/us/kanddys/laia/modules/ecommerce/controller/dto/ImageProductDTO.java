package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * Este data transfer object (DTO) representa una imagen de un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class ImageProductDTO {
   @JsonProperty
   private String url;

   public ImageProductDTO() {
   }
}
