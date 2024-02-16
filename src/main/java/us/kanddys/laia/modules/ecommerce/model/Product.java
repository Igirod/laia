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
 * Esta clase representa un producto.
 * 
 * @author Igirod0
 * @version 1.0.2
 */
@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "name")
   private String name;
   @Column(name = "price")
   private Double price;
   @Column(name = "stock")
   private Integer stock;
   @Column(name = "front_page")
   private byte[] frontPage;
   @Column(name = "merchant_id")
   private Long merchantId;
   
   public Product() {
   }
}
