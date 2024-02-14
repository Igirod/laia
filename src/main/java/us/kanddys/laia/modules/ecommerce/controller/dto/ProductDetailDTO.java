package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.ProductDetail;

/**
 * Esta clase representa el detalle de un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class ProductDetailDTO {
   @JsonProperty("id")
   private Long id;
   @JsonProperty("title")
   private String title;
   @JsonProperty("description")
   private String description;
   @JsonProperty("image")
   private String image;

   public ProductDetailDTO() {}

   /**
    * Constructor personalizado se implementa en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @throws IOException
    */
   public ProductDetailDTO(ProductDetail productDetail) throws IOException {
      this.id = (productDetail.getId() == null) ? null : productDetail.getId();
      this.title = (productDetail.getTitle() != null) ? productDetail.getTitle() : null;
      this.description = (productDetail.getDescription() != null) ? productDetail.getDescription() : null;
      this.image = (productDetail.getImage() != null)
            ? StreamUtils.copyToString(new ByteArrayInputStream(productDetail.getImage()), StandardCharsets.UTF_8)
            : null;
   }
}
