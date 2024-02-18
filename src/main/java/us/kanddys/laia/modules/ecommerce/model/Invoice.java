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
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceInputDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;

/**
 * Esta clase representa una factura.
 * 
 * @author Igirod0
 * @version 1.0.0
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
   @Column(name = "merchant_id")
   private Long merchantId;
   @Column(name = "mer_email")
   private String merchantEmail;
   @Column(name = "user_email")
   private String userEmail;
   @Column(name = "payment_id")
   private Long paymentId;
   @Column(name = "code")
   private String code;
   @Column(name = "shopping_cart_id")
   private Long shoppingCartId;
   @Column(name = "reservation")
   private Date reservation;
   @Column(name = "total")
   private Float total;
   @Column(name = "message")
   private Boolean message;
   @Enumerated(EnumType.STRING)
   @Column(name = "status")
   private InvoiceStatus status;
   @Column(name = "voucher")
   private byte[] voucher;
   @Column(name = "note")
   private String note;

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
   public Invoice(InvoiceDTO invoice) throws ParseException {
      super();
      this.id = (invoice.getId() == null) ? null : invoice.getId();
      this.merchantId = (invoice.getMerchantId() == null) ? null : invoice.getMerchantId();
      this.merchantEmail = (invoice.getMerchantEmail() == null) ? null : invoice.getMerchantEmail();
      this.userEmail = (invoice.getUserEmail() == null) ? null : invoice.getUserEmail();
      this.paymentId = (invoice.getPaymentId() == null) ? null : invoice.getPaymentId();
      this.code = (invoice.getCode() == null) ? null
            : invoice.getCode();
      this.shoppingCartId = (invoice.getShoppingCartId() == null) ? null : invoice.getShoppingCartId();
      this.reservation = (invoice.getReservation() == null) ? null
            : DateUtils.convertStringToDate(invoice.getReservation());
      this.total = (invoice.getTotal() == null) ? null : invoice.getTotal();
      this.message = (invoice.getMessage() == null) ? null : invoice.getMessage();
      this.status = (invoice.getStatus() == null) ? null : invoice.getStatus();
      this.voucher = (invoice.getVoucher().getBytes() == null) ? null : invoice.getVoucher().getBytes();
      this.note = (invoice.getNote() == null) ? null : invoice.getNote();
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
   public Invoice(Long invoiceId, InvoiceInputDTO invoiceDTO) throws ParseException {
      super();
      this.id = invoiceId;
      this.merchantId = invoiceDTO.getMerchantId() != null ? invoiceDTO.getMerchantId() : this.merchantId;
      this.merchantEmail = invoiceDTO.getMerchantEmail() != null ? invoiceDTO.getMerchantEmail() : this.merchantEmail;
      this.userEmail = invoiceDTO.getUserEmail() != null ? invoiceDTO.getUserEmail() : this.userEmail;
      this.paymentId = invoiceDTO.getPaymentId() != null ? invoiceDTO.getPaymentId() : this.paymentId;
      this.code = invoiceDTO.getCode() != null ? invoiceDTO.getCode() : this.code;
      this.shoppingCartId = invoiceDTO.getShoppingCartId() != null ? invoiceDTO.getShoppingCartId()
            : this.shoppingCartId;
      this.reservation = invoiceDTO.getReservation() != null
            ? DateUtils.convertStringToDate(invoiceDTO.getReservation())
            : this.reservation;
      this.total = invoiceDTO.getTotal() != null ? invoiceDTO.getTotal() : this.total;
      this.message = invoiceDTO.getMessage() != null ? invoiceDTO.getMessage() : this.message;
      this.status = invoiceDTO.getStatus() != null ? invoiceDTO.getStatus() : this.status;
      this.voucher = invoiceDTO.getVoucher() != null ? invoiceDTO.getVoucher().getBytes() : this.voucher;
      this.note = invoiceDTO.getNote() != null ? invoiceDTO.getNote() : this.note;
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoice
    * @throws ParseException
    */
   public Invoice(InvoiceInputDTO invoice) throws ParseException {
      super();
      this.id = (invoice.getId() == null) ? null : invoice.getId();
      this.merchantId = (invoice.getMerchantId() == null) ? null : invoice.getMerchantId();
      this.merchantEmail = (invoice.getMerchantEmail() == null) ? null : invoice.getMerchantEmail();
      this.userEmail = (invoice.getUserEmail() == null) ? null : invoice.getUserEmail();
      this.paymentId = (invoice.getPaymentId() == null) ? null : invoice.getPaymentId();
      this.code = (invoice.getCode() == null) ? null
            : invoice.getCode();
      this.shoppingCartId = (invoice.getShoppingCartId() == null) ? null : invoice.getShoppingCartId();
      this.reservation = (invoice.getReservation() == null) ? null
            : DateUtils.convertStringToDate(invoice.getReservation());
      this.total = (invoice.getTotal() == null) ? null : invoice.getTotal();
      this.message = (invoice.getMessage() == null) ? null : invoice.getMessage();
      this.status = (invoice.getStatus() == null) ? null : invoice.getStatus();
      this.voucher = (invoice.getVoucher().getBytes() == null) ? null : invoice.getVoucher().getBytes();
      this.note = (invoice.getNote() == null) ? null : invoice.getNote();
   }

}
