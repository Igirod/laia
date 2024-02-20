package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.model.ProductDetailShort;

/**
 * Este clase representa un data transfer object (DTO) para el detalle corto de
 * un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class ProductDetailShortDTO {
   @JsonProperty("id")
   private Long productId;
   @JsonProperty("stock")
   private Integer stock;
   @JsonProperty("images")
   private List<ImageProductDTO> images;

   public ProductDetailShortDTO() {
   };

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param productDetailShort
    */
   public ProductDetailShortDTO(ProductDetailShort productDetailShort) {
      this.productId = (productDetailShort.getProductId() == null) ? null : productDetailShort.getProductId();
      this.stock = (productDetailShort.getStock() == null) ? null : productDetailShort.getStock();
      this.images = (productDetailShort.getImages() == null) ? null : productDetailShort.getImages().stream().map(t -> {
         try {
            return new ImageProductDTO(t);
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         }
      }).toList();
   }
}
