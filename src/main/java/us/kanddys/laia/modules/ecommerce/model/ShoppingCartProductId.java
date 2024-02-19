package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa la llave compuesta entre el shopping cart id y product
 * id.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Embeddable
@Data
@AllArgsConstructor
public class ShoppingCartProductId {
   
   private Long shoppingCartId;
   private Long productId;

   public ShoppingCartProductId() {
   }
}
