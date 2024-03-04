package us.kanddys.laia.modules.ecommerce.services;

public interface OrderService {

   public Integer updateOrderStatus(Long id, String status);

   public Integer updateOrderNote(Long id, String status);
}
