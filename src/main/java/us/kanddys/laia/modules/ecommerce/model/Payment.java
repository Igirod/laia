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
import us.kanddys.laia.modules.ecommerce.model.Utils.TypePayment;

/**
 * Esta clase representa un tipo de pago.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
public class Payment {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Enumerated(EnumType.STRING)
   @Column(name = "type")
   private TypePayment typePayment;
   @Column(name = "title")
   private String title;
   @Column(name = "cvu")
   private String cvu;
   @Column(name = "status")
   private Integer status;
   @Column(name = "mer_id")
   private Long merchantId;

   public Payment() {
   }
}
