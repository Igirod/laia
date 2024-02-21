package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.controller.dto.ShoppingCartProductInputDTO;

/**
 * Esta clase representa un producto en el carrito de compras.
 * 
 * @author Igirod
 * @version 1.0.1
 */
@Entity
@Table(name = "shopping_carts_products")
@AllArgsConstructor
@Data
public class ShoppingCartProduct {

   @EmbeddedId
   private ShoppingCartProductId id;
   @Column(name = "quantity")
   private Integer quantity;
   @ManyToOne
   @JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)
   private Product product;

   public ShoppingCartProduct() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param shoppingCartProductInputDTO
    */
   public ShoppingCartProduct(Long shoppingCartId, Long productId) {
      super();
      this.id = new ShoppingCartProductId(shoppingCartId, productId);
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param shoppingCartProductInputDTO
    */
   public ShoppingCartProduct(ShoppingCartProductInputDTO shoppingCartProductInputDTO) {
      super();
      this.id = new ShoppingCartProductId(shoppingCartProductInputDTO.getShoppingCartId(),
            shoppingCartProductInputDTO.getProductId());
      this.quantity = shoppingCartProductInputDTO.getQuantity();
   }
}
