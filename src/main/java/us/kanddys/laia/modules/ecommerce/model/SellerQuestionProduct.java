package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa una pregunta que un usuario puede hacer a un vendedor
 * perteneciente a un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Table(name = "sellers_questions_products")
@Entity
public class SellerQuestionProduct {

   @EmbeddedId
   private SellerQuestionProductId id;

   public SellerQuestionProduct() {
   }
}
