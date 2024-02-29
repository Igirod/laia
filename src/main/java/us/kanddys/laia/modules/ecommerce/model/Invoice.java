package us.kanddys.laia.modules.ecommerce.model;

import java.text.ParseException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceInputDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;

/**
 * Esta clase representa una factura.
 * 
 * @author Igirod0
 * @version 1.0.2
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
   @Column(name = "user_id")
   private Long userId;
   @Column(name = "mer_id")
   private Long merchantId;
   @Column(name = "payment_id")
   private Long paymentId;
   @Column(name = "code")
   private String code;
   @Column(name = "reservation")
   private String reservation;
   @Column(name = "total")
   private Double total;
   @Column(name = "message")
   private String message;
   @Enumerated(EnumType.STRING)
   @Column(name = "status")
   private InvoiceStatus status;
   @Column(name = "voucher")
   private String voucher;
   @Column(name = "note")
   private String note;
   @Column(name = "address_title")
   private String addressTitle;
   @Column(name = "address_direction")
   private String addressDirection;
   @Transient
   private Integer count;
   @Column(name = "batch_id")
   private Long batchId;

   public Invoice() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param invoice
    * @throws ParseException
    */
   public Invoice(InvoiceDTO invoice, Integer count) throws ParseException {
      super();
      this.id = (invoice.getId() == null) ? null : invoice.getId();
      this.merchantId = (invoice.getMerchantId() == null) ? null : invoice.getMerchantId();
      this.userId = (invoice.getUserId() == null) ? null : invoice.getUserId();
      this.paymentId = (invoice.getPaymentId() == null) ? null : invoice.getPaymentId();
      this.code = (invoice.getCode() == null) ? null
            : invoice.getCode();
      this.reservation = (invoice.getReservation() == null) ? null
            : invoice.getReservation();
      this.total = (invoice.getTotal() == null) ? null : invoice.getTotal();
      this.message = (invoice.getMessage() == null) ? null : invoice.getMessage();
      this.status = (invoice.getStatus() == null) ? null : invoice.getStatus();
      this.voucher = (invoice.getVoucher() == null) ? null : invoice.getVoucher();
      this.note = (invoice.getNote() == null) ? null : invoice.getNote();
      this.count = (count == null) ? null : count;
      this.addressDirection = (invoice.getAddressDirection() == null) ? null : invoice.getAddressDirection();
      this.addressTitle = (invoice.getAddressTitle() == null) ? null : invoice.getAddressTitle();
      this.batchId = (invoice.getBatchId() == null) ? null : invoice.getBatchId();
   }

   /**
    * Constructor personalizado para actualizar una factura existente.
    *
    * @author Igirod
    * @version 1.0.0
    * @param invoiceId
    * @param invoiceDTO
    * @throws ParseException
    */
   public Invoice(Long invoiceId, InvoiceInputDTO invoiceDTO, Integer count) throws ParseException {
      super();
      this.id = invoiceId;
      this.userId = invoiceDTO.getUserId() != null ? invoiceDTO.getUserId() : this.userId;
      this.merchantId = invoiceDTO.getMerchantId() != null ? invoiceDTO.getMerchantId() : this.merchantId;
      this.paymentId = invoiceDTO.getPaymentId() != null ? invoiceDTO.getPaymentId() : this.paymentId;
      this.code = invoiceDTO.getCode() != null ? invoiceDTO.getCode() : this.code;
      this.reservation = invoiceDTO.getReservation() != null
            ? invoiceDTO.getReservation()
            : this.reservation;
      this.total = invoiceDTO.getTotal() != null ? invoiceDTO.getTotal() : this.total;
      this.message = invoiceDTO.getMessage() != null ? invoiceDTO.getMessage() : this.message;
      this.status = invoiceDTO.getStatus() != null ? invoiceDTO.getStatus() : this.status;
      this.voucher = invoiceDTO.getVoucher() != null ? invoiceDTO.getVoucher() : this.voucher;
      this.note = invoiceDTO.getNote() != null ? invoiceDTO.getNote() : this.note;
      this.count = (count == null) ? null : count;
      this.addressDirection = invoiceDTO.getAddressDirection() != null ? invoiceDTO.getAddressDirection()
            : this.addressDirection;
      this.addressTitle = invoiceDTO.getAddressTitle() != null ? invoiceDTO.getAddressTitle() : this.addressTitle;
      this.batchId = invoiceDTO.getBatchId() != null ? invoiceDTO.getBatchId() : this.batchId;
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceId
    * @param merchantId
    * @throws ParseException
    */
   public Invoice(Long userId, Long merchantId) throws ParseException {
      super();
      this.id = null;
      this.userId = (userId == null) ? null : userId;
      this.merchantId = (merchantId == null) ? null : merchantId;
      this.status = InvoiceStatus.INITIAL;
   }
}
