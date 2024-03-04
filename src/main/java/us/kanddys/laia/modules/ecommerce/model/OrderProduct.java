package us.kanddys.laia.modules.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
@Table(name = "orders_products")
public class OrderProduct {
   @EmbeddedId
   private OrderProductId id;
   @JsonProperty
   private Integer quantity;

   public OrderProduct() {
   }

   public OrderProduct(InvoiceProduct invoiceProduct, Long orderId) {
      super();
      this.id = new OrderProductId(invoiceProduct.getId().getProductId(), orderId);
      this.quantity = invoiceProduct.getQuantity();
   }
}
