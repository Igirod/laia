package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Invoice;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;

/**
 * Esta clase representa un data transfer object (DTO) para la factura.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class InvoiceDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private String merchantEmail;
   @JsonProperty
   private String userEmail;
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
   private Boolean message;
   @JsonProperty
   private InvoiceStatus status;
   @JsonProperty
   private String voucher;
   @JsonProperty
   private String note;

   public InvoiceDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoice
    * @throws IOException
    */
   public InvoiceDTO(Invoice invoice) throws IOException {
      super();
      this.id = (invoice.getId() == null) ? null : invoice.getId();
      this.merchantEmail = (invoice.getMerchantEmail() == null) ? null : invoice.getMerchantEmail();
      this.userEmail = (invoice.getUserEmail() == null) ? null : invoice.getUserEmail();
      this.paymentId = (invoice.getPaymentId() == null) ? null : invoice.getPaymentId();
      this.code = (invoice.getCode() == null) ? null : invoice.getCode();
      this.shoppingCartId = (invoice.getShoppingCartId() == null) ? null : invoice.getShoppingCartId();
      this.reservation = (invoice.getReservation().toString() == null) ? null : invoice.getReservation().toString();
      this.total = (invoice.getTotal() == null) ? null : invoice.getTotal();
      this.message = (invoice.getMessage() == null) ? null : invoice.getMessage();
      this.status = (invoice.getStatus().toString() == null) ? null : invoice.getStatus();
      this.voucher = (invoice.getVoucher() == null) ? null
            : StreamUtils.copyToString(new ByteArrayInputStream(invoice.getVoucher()), StandardCharsets.UTF_8);
      this.note = invoice.getNote();
   }
}
