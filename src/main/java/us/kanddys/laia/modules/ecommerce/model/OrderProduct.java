package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
   @Column(name = "quantity")
   private Integer quantity;
   @ManyToOne
   @JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)
   private Product product;

   public OrderProduct() {
   }

   public OrderProduct(InvoiceProduct invoiceProduct, Long orderId) {
      super();
      this.id = new OrderProductId(invoiceProduct.getId().getProductId(), orderId);
      this.quantity = invoiceProduct.getQuantity();
      this.product = invoiceProduct.getProduct();
   }
}
