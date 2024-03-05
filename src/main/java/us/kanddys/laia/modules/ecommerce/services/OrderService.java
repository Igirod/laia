package us.kanddys.laia.modules.ecommerce.services;

import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.controller.dto.OrderDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderPaymentDTO;

/**
 * Esta interfaz contiene las obligaciones que debe implementar la clase
 * OrderServiceImpl.
 * 
 * @autor Igirod0
 * @version 1.0.0
 */
public interface OrderService {

   /**
    * Este método tiene la obligación de actualizar el estado de la orden.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param id
    * @param status
    * @return
    */
   public Integer updateOrderStatus(Long id, String status);

   /**
    * Este método tiene la obligación de actualizar el estado de la orden.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param id
    * @param status
    * @return
    */
   public Integer updateOrderNote(Long id, String status);

   /**
    * Este método tiene la obligación de buscar una orden por su numero
    * de id.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param orderId
    * @return OrderDTO
    */
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
