package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.io.IOException;
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
   private String url;

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
      this.url = (imageProduct.getUrl() == null) ? null : imageProduct.getUrl();
   }
}
