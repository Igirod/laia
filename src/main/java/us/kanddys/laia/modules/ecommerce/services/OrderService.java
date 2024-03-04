package us.kanddys.laia.modules.ecommerce.services;

import us.kanddys.laia.modules.ecommerce.controller.dto.OrderDTO;

public interface OrderService {

   public Integer updateOrderStatus(Long id, String status);

   public Integer updateOrderNote(Long id, String status);

   public OrderDTO getOrderById(Long orderId);
}
