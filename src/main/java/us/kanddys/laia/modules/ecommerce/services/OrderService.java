package us.kanddys.laia.modules.ecommerce.services;

import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.controller.dto.OrderDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderPaymentDTO;

public interface OrderService {

   public Integer updateOrderStatus(Long id, String status);

   public Integer updateOrderNote(Long id, String status);

   public OrderDTO getOrderById(Long orderId);

   /**
    * Este método tiene la obligación de actualizar la imagen del voucher de la
    * orden.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param voucher
    * @param orderId
    * @return OrderPaymentDTO
    */
   public OrderPaymentDTO updateVoucher(MultipartFile voucher, Long orderId);
}
