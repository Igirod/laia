package us.kanddys.laia.controller.dto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.util.StreamUtils;

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
   private String image;

   public ImageProductDTO() {
   }

   public ImageProductDTO(ImageProduct imageProduct) throws IOException {
      super();
      this.id = (imageProduct.getId() != null) ? imageProduct.getId() : null;
      this.productId = (imageProduct.getProductId() != null) ? imageProduct.getProductId() : null;
      this.image = (imageProduct.getImage() != null)
            ? StreamUtils.copyToString(new ByteArrayInputStream(imageProduct.getImage()), StandardCharsets.UTF_8)
            : null;
   }
}
