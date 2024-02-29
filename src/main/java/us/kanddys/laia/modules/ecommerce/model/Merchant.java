package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Utils.TypeShipment;

@Entity
@Table(name = "merchants")
@Data
@AllArgsConstructor
public class Merchant {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "mer_email")
   private String email;
   @Column(name = "slug")
   private String slug;
   @Column(name = "message")
   private String message;
   @Enumerated(EnumType.STRING)
   @Column(name = "type_shipment")
   private TypeShipment typeShipment;
   @Column(name = "title")
   private String title;
   @Column(name = "image")
   private String image;
   @Column(name = "address")
   private String address;

   public Merchant() {
   }
}
