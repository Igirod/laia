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
@Table(name = "invoices_products")
public class InvoiceProduct {
   @EmbeddedId
   private InvoiceProductId id;
   @Column(name = "quantity")
   private Integer quantity;
   @ManyToOne
   @JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)
   private Product product;

   public InvoiceProduct() {
   }

   public InvoiceProduct(OrderProduct orderProduct, Long orderId) {
      super();
      this.id = new InvoiceProductId(orderId, orderProduct.getId().getProductId());
      this.quantity = orderProduct.getQuantity();
      this.product = orderProduct.getProduct();
   }

   public InvoiceProduct(Long productId, Long orderId, Product product) {
      super();
      this.id = new InvoiceProductId(orderId, productId);
      this.quantity = 1;
      this.product = product;
   }
}
