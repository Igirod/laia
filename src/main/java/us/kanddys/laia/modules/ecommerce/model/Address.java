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
 * Esta clase representa la dirección de un usuario.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Entity
@Table(name = "address_users")
@AllArgsConstructor
@Data
public class Address {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "user_id")
   private Long userId;
   @Column(name = "title")
   private String title;
   @Column(name = "lng")
   private String lng;
   @Column(name = "lat")
   private String lat;
   @Column(name = "direction")
   private String direction;
}
