package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Product;

/**
 * Esta clase representa un data transfer object (DTO) de un producto.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Data
@AllArgsConstructor
public class ProductDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private String name;
   @JsonProperty
   private Double price;
   @JsonProperty
   private Integer stock;
   @JsonProperty
   private String frontPage;
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private List<ProductDetailDTO> productsDetails;

   public ProductDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.1
    */
   public ProductDTO(Product product) {
      super();
      this.id = (product.getId() != null) ? product.getId() : null;
      this.name = (product.getName() != null) ? product.getName() : null;
      this.price = (product.getPrice() != null) ? product.getPrice() : null;
      this.stock = (product.getStock() != null) ? product.getStock() : null;
      this.frontPage = (product.getFrontPage() != null) ? product.getFrontPage().toString() : null;
      this.merchantId = (product.getMerchantId() != null) ? product.getMerchantId() : null;
      this.productsDetails = (product.getProductDetail() != null) ? product.getProductDetail().stream().map(arg0 -> {
         try {
            return new ProductDetailDTO(arg0);
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         }
      }).collect(Collectors.toList()) : null;
   }
}
