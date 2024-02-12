package us.kanddys.laia.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.model.Product;

/**
 * Esta clase representa un data transfer object (DTO) de un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class ProductDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private String title;
   @JsonProperty
   private Double price;
   @JsonProperty
   private Integer stock;
   @JsonProperty
   private String description;
   @JsonProperty
   private String frontPage;

   public ProductDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    */
   public ProductDTO(Product product) {
      super();
      this.id = (product.getId() != null) ? product.getId() : null;
      this.title = (product.getTitle() != null) ? product.getTitle() : null;
      this.price = (product.getPrice() != null) ? product.getPrice() : null;
      this.stock = (product.getStock() != null) ? product.getStock() : null;
      this.description = (product.getDescription() != null) ? product.getDescription() : null;
      this.frontPage = (product.getFrontPage() != null) ? product.getFrontPage().toString() : null;
   }
}
