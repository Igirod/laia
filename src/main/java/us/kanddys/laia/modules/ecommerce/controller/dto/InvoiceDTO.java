package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Invoice;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;

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
   private String merchantTitle;
   @JsonProperty
   private String userEmail;
   @JsonProperty
   private String userName;
   @JsonProperty
   private String userLastName;
   @JsonProperty
   private String code;
   @JsonProperty
   private String reservation;
   @JsonProperty
   private String reservationType;
   @JsonProperty
   private Double total;
   @JsonProperty
   private String message;
   @JsonProperty
   private String status;
   @JsonProperty
   private String voucher;
   @JsonProperty
   private String note;
   @JsonProperty
   private String batchFrom;
   @JsonProperty
   private String batchTo;
   @JsonProperty
   private String createdAt;
   @JsonProperty
   private String updatedAt;
   @JsonProperty
   private String addressLat;
   @JsonProperty
   private String addressLng;
   @JsonProperty
   private String addressDirection;
   @JsonProperty
   private List<InvoiceProductDTO> products;

   public InvoiceDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    * 
    * @param invoice
    * @param fromTime
    * @param toTime
    * @param products
    */
   public InvoiceDTO(Invoice invoice, List<InvoiceProductDTO> products) {
      super();
      this.id = (invoice.getId() == null) ? null : invoice.getId();
      this.code = (invoice.getCode() == null) ? null : invoice.getCode();
      this.reservation = (invoice.getReservation() == null) ? null
            : DateUtils.convertDateToString(invoice.getReservation());
      this.total = (invoice.getTotal() == null) ? null : invoice.getTotal();
      this.message = (invoice.getMessage() == null) ? null : invoice.getMessage();
      this.status = (invoice.getStatus() == null) ? null : invoice.getStatus().toString();
      this.voucher = (invoice.getVoucher() == null) ? null : invoice.getVoucher();
      this.note = (invoice.getNote() == null) ? null : invoice.getNote();
      this.addressDirection = (invoice.getAddressDirection() == null) ? null : invoice.getAddressDirection();
      this.addressLat = (invoice.getAddressLat() == null) ? null : invoice.getAddressLat();
      this.addressLng = (invoice.getAddressLng() == null) ? null : invoice.getAddressLng();
      this.createdAt = (invoice.getCreatedAt() == null) ? null : DateUtils.convertDateToString(invoice.getCreatedAt());
      this.batchFrom = (invoice.getBatchFrom() == null) ? null : invoice.getBatchFrom();
      this.batchTo = (invoice.getBatchTo() == null) ? null : invoice.getBatchTo();
      this.updatedAt = (invoice.getUpdatedAt() == null) ? null : DateUtils.convertDateToString(invoice.getUpdatedAt());
      this.reservationType = (invoice.getType() == null) ? null : invoice.getType();
      this.userEmail = (invoice.getUserEmail() == null) ? null : invoice.getUserEmail();
      this.userName = (invoice.getUserName() == null) ? null : invoice.getUserName();
      this.userLastName = (invoice.getUserLastName() == null) ? null : invoice.getUserLastName();
      this.merchantTitle = (invoice.getMerchantTitle() == null) ? null : invoice.getMerchantTitle();
      this.products = products;
   }
}
