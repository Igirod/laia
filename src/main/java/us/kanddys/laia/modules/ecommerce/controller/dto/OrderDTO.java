package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Order;

/**
 * Esta clase representa un data transfer object (DTO) para la entidad Order.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class OrderDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private Long userId;
   @JsonProperty
   private String code;
   @JsonProperty
   private String reservation;
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
   private String addressLat;
   @JsonProperty
   private String addressLng;
   @JsonProperty
   private String addressDirection;
   @JsonProperty
   private List<ProductDTO> products;

   public OrderDTO() {
   }

   public OrderDTO(Order order) {
      super();
      this.id = (order.getId() == null) ? null : order.getId();
      this.merchantId = (order.getMerchantId() == null) ? null : order.getMerchantId();
      this.userId = (order.getUserId() == null) ? null : order.getUserId();
      this.code = (order.getCode() == null) ? null : order.getCode();
      this.reservation = (order.getReservation() == null) ? null
            : order.getReservation();
      this.total = (order.getTotal() == null) ? null : order.getTotal();
      this.message = (order.getMessage() == null) ? null : order.getMessage();
      this.status = (order.getStatus() == null) ? null : order.getStatus().toString();
      this.voucher = (order.getVoucher() == null) ? null : order.getVoucher();
      this.note = (order.getNote() == null) ? null : order.getNote();
      this.addressDirection = (order.getAddressDirection() == null) ? null : order.getAddressDirection();
      this.addressLat = (order.getAddressLat() == null) ? null : order.getAddressLat();
      this.addressLng = (order.getAddressLng() == null) ? null : order.getAddressLng();
   }
}
