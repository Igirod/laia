package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Order;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;

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
   private List<OrderProductDTO> products;

   public OrderDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    * 
    * @param order
    * @param fromTime
    * @param toTime
    * @param products
    */
   public OrderDTO(Order order, String fromTime, String toTime, String reservationType, String userEmail,
         String userName, String userLastName,
         List<OrderProductDTO> products) {
      super();
      this.id = (order.getId() == null) ? null : order.getId();
      this.merchantId = (order.getMerchantId() == null) ? null : order.getMerchantId();
      this.userId = (order.getUserId() == null) ? null : order.getUserId();
      this.code = (order.getCode() == null) ? null : order.getCode();
      this.reservation = (order.getReservation() == null) ? null
            : DateUtils.convertDateToString(order.getReservation());
      this.total = (order.getTotal() == null) ? null : order.getTotal();
      this.message = (order.getMessage() == null) ? null : order.getMessage();
      this.status = (order.getStatus() == null) ? null : order.getStatus().toString();
      this.voucher = (order.getVoucher() == null) ? null : order.getVoucher();
      this.note = (order.getNote() == null) ? null : order.getNote();
      this.addressDirection = (order.getAddressDirection() == null) ? null : order.getAddressDirection();
      this.addressLat = (order.getAddressLat() == null) ? null : order.getAddressLat();
      this.addressLng = (order.getAddressLng() == null) ? null : order.getAddressLng();
      this.createdAt = (order.getCreatedAt() == null) ? null : DateUtils.convertDateToString(order.getCreatedAt());
      this.batchFrom = (fromTime == null) ? null : fromTime;
      this.batchTo = (toTime == null) ? null : toTime;
      this.updatedAt = (order.getUpdatedAt() == null) ? null : DateUtils.convertDateToString(order.getUpdatedAt());
      this.reservationType = (reservationType == null) ? null : reservationType;
      this.userEmail = (userEmail == null) ? null : userEmail;
      this.userName = (userName == null) ? null : userName;
      this.userLastName = (userLastName == null) ? null : userLastName;
      this.products = products;
   }
}
