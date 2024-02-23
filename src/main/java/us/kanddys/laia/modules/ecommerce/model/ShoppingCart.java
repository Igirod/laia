package us.kanddys.laia.modules.ecommerce.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
   @Column(name = "invoice_id")
   private Long invoiceId;
   @Column(name = "createAt")
   private Date createAt;
   @Transient
   private Integer count;

   public ShoppingCart() {}
}
