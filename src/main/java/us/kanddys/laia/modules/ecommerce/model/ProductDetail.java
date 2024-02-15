package us.kanddys.laia.modules.ecommerce.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "products_details")
@AllArgsConstructor
@Data
public class ProductDetail {
   @Id
   @Column(name = "id")
   private Long id;
   @Column(name = "product_id")
   private Long productId;
   @Column(name = "title")
   private String title;
   @Column(name = "description")
   private String description;
   @Column(name = "image")
   private byte[] image;

   public ProductDetail() {
   }
}
