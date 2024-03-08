package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa una relación entre una categoría y un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Entity
@Table(name = "categories_products")
public class CategoryProduct {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "category_id")
   private Long categoryId;
   @ManyToOne
   @JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)
   private Product product;

   public CategoryProduct() {
   }
}
