package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.model.OrderProduct;

/**
 * Esta clase contiene los datos de un producto de una orden.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class OrderProductDTO {
   @JsonProperty
   private Long productId;
   @JsonProperty
   private Long orderId;
   @JsonProperty
   private Integer quantity;
   @JsonProperty
   private ProductDTO product;

   public OrderProductDTO() {
   }

   public OrderProductDTO(InvoiceProductDTO invoiceProductDTO) {
      super();
      this.productId = invoiceProductDTO.getProductId();
      this.quantity = invoiceProductDTO.getQuantity();
      this.product = invoiceProductDTO.getProduct();
   }

   public OrderProductDTO(OrderProduct orderProduct) {
      super();
      this.orderId = orderProduct.getId().getOrderId();
      this.productId = orderProduct.getId().getProductId();
      this.quantity = orderProduct.getQuantity();
      try {
         this.product = new ProductDTO(orderProduct.getProduct());
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
   }
}
