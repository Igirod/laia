package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa un carro de compras.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Entity
@Table(name = "shopping_carts")
@Data
@AllArgsConstructor
public class ShoppingCart {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "merchant_id")
   private Long merchantId;
   @Column(name = "user_id")
   private Long userId;

   public ShoppingCart() {}
}
