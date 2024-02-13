package us.kanddys.laia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Esta clase representa una imagen de un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@Entity
@Table(name = "images")
public class ImageProduct {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "product_id")
   private Long productId;
   @Lob
   @Column(name = "image")
   private byte[] image;
}
