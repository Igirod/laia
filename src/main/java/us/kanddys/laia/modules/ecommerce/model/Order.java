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
 * Esta clase representa la entidad Order.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
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

   public Order() {
   }

   public Order(Invoice invoice, String merchantTitle, Long merchantId, String userName, String userLastName,
         String userEmail, String batchTo, String batchFrom) {
      super();
      this.id = (invoice.getId() == null) ? null : invoice.getId();
      // ! Siempre se deben tener disponibles estos datos.
      this.merchantTitle = merchantTitle;
      this.userLastName = userLastName;
      this.userEmail = userEmail;
      this.userName = userName;
      this.batchFrom = batchFrom;
      this.batchTo = batchTo;
      this.merchantId = merchantId;
      this.code = (invoice.getCode() == null) ? null
            : invoice.getCode();
      try {
         this.reservation = (invoice.getReservation() == null) ? null
               : DateUtils.convertStringToDate(invoice.getReservation());
      } catch (ParseException e) {
         throw new RuntimeException("Error al convertir la fecha de la reserva.");
      }
      this.total = (invoice.getTotal() == null) ? null : invoice.getTotal();
      this.message = (invoice.getMessage() == null) ? null : invoice.getMessage();
      this.status = (invoice.getStatus() == null) ? null : invoice.getStatus();
      this.voucher = (invoice.getVoucher() == null) ? null : invoice.getVoucher();
      this.note = (invoice.getNote() == null) ? null : invoice.getNote();
      this.addressDirection = (invoice.getAddressDirection() == null) ? null : invoice.getAddressDirection();
      this.addressLat = (invoice.getAddressLat() == null) ? null : invoice.getAddressLat();
      this.addressLng = (invoice.getAddressLng() == null) ? null : invoice.getAddressLng();
      this.addressNumber = (invoice.getAddressNumber() == null) ? null : invoice.getAddressNumber();
      this.createdAt = (invoice.getCreateAt() == null) ? null : invoice.getCreateAt();
      this.type = (invoice.getType() == null) ? null : invoice.getType();
   }
}
