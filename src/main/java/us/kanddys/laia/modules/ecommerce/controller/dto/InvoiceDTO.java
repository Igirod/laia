package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Invoice;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.model.Utils.Status;

/**
 * Esta clase representa un data transfer object (DTO) para la factura.
 * 
 * @author Igirod0
 * @version 1.0.3
 */
@Data
@AllArgsConstructor
public class InvoiceDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private Long userId;
   @JsonProperty
   private Long paymentId;
   @JsonProperty
   private String code;
   @JsonProperty
   private Long shoppingCartId;
   @JsonProperty
   private String reservation;
   @JsonProperty
   private Double total;
   @JsonProperty
   private String message;
   @JsonProperty
   private Status status;
   @JsonProperty
   private String voucher;
   @JsonProperty
   private String note;
   @JsonProperty
   private Integer count;
   @JsonProperty
   private String addressLat;
   @JsonProperty
   private String addressLng;
   @JsonProperty
   private String addressDirection;
   @JsonProperty
   private Long batchId;
   @JsonProperty
   private Integer addressNumber;
   @JsonProperty
   private String type;
   @JsonProperty
   private String merchantDirection;
   @JsonProperty
   private String merchantLat;
   @JsonProperty
   private String merchantLng;
   @JsonProperty
   private String createAt;

   public InvoiceDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param invoice
    * @throws IOException
    */
   public InvoiceDTO(Invoice invoice) throws IOException {
      super();
      this.id = (invoice.getId() == null) ? null : invoice.getId();
      this.merchantId = (invoice.getMerchantId() == null) ? null : invoice.getMerchantId();
      this.userId = (invoice.getUserId() == null) ? null : invoice.getUserId();
      this.paymentId = (invoice.getPaymentId() == null) ? null : invoice.getPaymentId();
      this.code = (invoice.getCode() == null) ? null : invoice.getCode();
      this.reservation = (invoice.getReservation() == null) ? null
            : invoice.getReservation();
      this.total = (invoice.getTotal() == null) ? null : invoice.getTotal();
      this.message = (invoice.getMessage() == null) ? null : invoice.getMessage();
      this.status = (invoice.getStatus().toString() == null) ? null : invoice.getStatus();
      this.voucher = (invoice.getVoucher() == null) ? null : invoice.getVoucher();
      this.note = (invoice.getNote() == null) ? null : invoice.getNote();
      this.count = (invoice.getCount() == null) ? null : invoice.getCount();
      this.addressDirection = (invoice.getAddressDirection() == null) ? null : invoice.getAddressDirection();
      this.addressLat = (invoice.getAddressLat() == null) ? null : invoice.getAddressLat();
      this.addressLng = (invoice.getAddressLng() == null) ? null : invoice.getAddressLng();
      this.batchId = (invoice.getBatchId() == null) ? null : invoice.getBatchId();
      this.type = (invoice.getType() == null) ? null : invoice.getType();
      this.addressNumber = (invoice.getAddressNumber() == null) ? null : invoice.getAddressNumber();
      this.createAt = (invoice.getCreateAt() == null) ? null : DateUtils.convertDateToString(invoice.getCreateAt());
   }
}
