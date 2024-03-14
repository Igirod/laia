package us.kanddys.laia.modules.ecommerce.model;

import java.text.ParseException;
import java.util.Date;

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
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.model.Utils.Status;

/**
 * Esta clase representa una factura.
 * 
 * @author Igirod0
 * @version 1.0.4
 */
@Entity
@Table(name = "invoices")
@AllArgsConstructor
@Data
public class Invoice {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "user_name")
   private String userName;
   @Column(name = "user_last_name")
   private String userLastName;
   @Column(name = "user_email")
   private String userEmail;
   @Column(name = "mer_id")
   private Long merchantId;
   @Column(name = "merchant_title")
   private String merchantTitle;
   @Column(name = "code")
   private String code;
   @Column(name = "reservation")
   private Date reservation;
   @Column(name = "total")
   private Double total;
   @Column(name = "message")
   private String message;
   @Enumerated(EnumType.STRING)
   @Column(name = "status")
   private Status status;
   @Column(name = "voucher")
   private String voucher;
   @Column(name = "note")
   private String note;
   @Column(name = "address_lat")
   private String addressLat;
   @Column(name = "address_lng")
   private String addressLng;
   @Column(name = "address_direction")
   private String addressDirection;
   @Column(name = "address_number")
   private Integer addressNumber;
   @Column(name = "type")
   private String type;
   @Column(name = "created_at")
   private Date createdAt;
   @Column(name = "updated_at")
   private Date updatedAt;
   @Column(name = "batch_to")
   private String batchTo;
   @Column(name = "batch_from")
   private String batchFrom;

   public Invoice() {
   }

   public Invoice(Order order, String merchantTitle, Long merchantId, String userName, String userLastName,
         String userEmail, String batchTo, String batchFrom) {
      super();
      this.id = (order.getId() == null) ? null : order.getId();
      // ! Siempre se deben tener disponibles estos datos.
      this.merchantTitle = merchantTitle;
      this.userLastName = userLastName;
      this.userEmail = userEmail;
      this.userName = userName;
      this.batchFrom = batchFrom;
      this.batchTo = batchTo;
      this.merchantId = merchantId;
      this.code = (order.getCode() == null) ? null
            : order.getCode();
      try {
         this.reservation = (order.getReservation() == null) ? null
               : DateUtils.convertStringToDate(order.getReservation());
      } catch (ParseException e) {
         throw new RuntimeException("Error al convertir la fecha de la reserva.");
      }
      this.total = (order.getTotal() == null) ? null : order.getTotal();
      this.message = (order.getMessage() == null) ? null : order.getMessage();
      this.status = (order.getStatus() == null) ? null : order.getStatus();
      this.voucher = (order.getVoucher() == null) ? null : order.getVoucher();
      this.note = (order.getNote() == null) ? null : order.getNote();
      this.addressDirection = (order.getAddressDirection() == null) ? null : order.getAddressDirection();
      this.addressLat = (order.getAddressLat() == null) ? null : order.getAddressLat();
      this.addressLng = (order.getAddressLng() == null) ? null : order.getAddressLng();
      this.addressNumber = (order.getAddressNumber() == null) ? null : order.getAddressNumber();
      this.createdAt = (order.getCreateAt() == null) ? null : order.getCreateAt();
      this.type = (order.getType() == null) ? null : order.getType();
   }
}
