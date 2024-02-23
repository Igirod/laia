package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceProductInputDTO;

/**
 * Esta clase representa un producto en el carrito de compras.
 * 
 * @author Igirod
 * @version 1.0.1
 */
@Entity
@Table(name = "invoices_products")
@AllArgsConstructor
@Data
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

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceId
    * @param productId
    */
   public InvoiceProduct(Long productId, Long invoiceId) {
      super();
      this.id = new InvoiceProductId(productId, invoiceId);
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param InvoiceProductInputDTO
    */
   public InvoiceProduct(InvoiceProductInputDTO InvoiceProductInputDTO) {
      super();
      this.id = new InvoiceProductId(InvoiceProductInputDTO.getShoppingCartId(),
            InvoiceProductInputDTO.getProductId());
      this.quantity = InvoiceProductInputDTO.getQuantity();
   }
}
