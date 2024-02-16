package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.ImageProduct;

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
   private Long id;
   @JsonProperty
   private Long productId;
   @JsonProperty
   private String image;

   public ImageProductDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param imageProduct
    * @throws IOException 
    */
   public ImageProductDTO(ImageProduct imageProduct) throws IOException {
      super();
      this.id = (imageProduct.getId() == null) ? null : imageProduct.getId();
      this.productId = (imageProduct.getProductId() == null) ? null : imageProduct.getProductId();
      this.image = (imageProduct.getImage() == null) ? null : StreamUtils.copyToString(new ByteArrayInputStream(imageProduct.getImage()), StandardCharsets.UTF_8);
   }
}
