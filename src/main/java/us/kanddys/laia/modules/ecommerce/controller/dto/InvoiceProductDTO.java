package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProduct;

/**
 * Esta clase representa un data transfer object (DTO) de un producto en el
 * carrito de compras.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Data
@AllArgsConstructor
public class InvoiceProductDTO {
   @JsonProperty
   private Long InvoiceId;
   @JsonProperty
   private Long productId;
   @JsonProperty
   private Integer quantity;
   @JsonProperty
   private ProductDTO product;

   public InvoiceProductDTO() {
   }

   /**
    * Constructor personlizado utilizado en diferentes servicios.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceProduct
    */
   public InvoiceProductDTO(InvoiceProduct invoiceProduct) {
      this.InvoiceId = (invoiceProduct.getId().getInvoiceId() == null ? 0 : invoiceProduct.getId().getInvoiceId());
      this.productId = (invoiceProduct.getId().getProductId() == null ? 0 : invoiceProduct.getId().getProductId());
      this.quantity = (invoiceProduct.getQuantity() == null ? 0 : invoiceProduct.getQuantity());
      try {
         this.product = new ProductDTO(invoiceProduct.getProduct());
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
   }
}
