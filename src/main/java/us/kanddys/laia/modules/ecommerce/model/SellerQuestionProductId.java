package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa el id de una pregunta que un usuario puede hacer a un
 * vendedor
 * perteneciente a un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Embeddable
@AllArgsConstructor
@Data
public class SellerQuestionProductId {

   private Long productId;
   private Long sellerQuestionId;

   public SellerQuestionProductId() {
   }
}
