package us.kanddys.laia.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.model.ImageProduct;

@Data
@AllArgsConstructor
public class ImageProductDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private Long productId;
   @JsonProperty
   private byte[] image;

   public ImageProductDTO() {
   }

   public ImageProductDTO(ImageProduct imageProduct) {
      super();
      this.id = (imageProduct.getId() != null) ? imageProduct.getId() : null;
      this.productId = (imageProduct.getProductId() != null) ? imageProduct.getProductId() : null;
      this.image = (imageProduct.getImage() != null) ? imageProduct.getImage() : null;
   }
}
