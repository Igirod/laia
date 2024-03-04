package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.OrderDTO;
import us.kanddys.laia.modules.ecommerce.services.OrderService;

@Controller
public class OrderController {

   @Autowired
   private OrderService orderService;

   @MutationMapping("uOrderS")
   public Integer updateOrderStatus(Long orderId, String status) {
      return orderService.updateOrderStatus(orderId, status);
   }

   @MutationMapping("uOrderN")
   public Integer updateOrderNote(Long orderId, String note) {
      return orderService.updateOrderNote(orderId, note);
   }

   @QueryMapping("gOrder")
   public OrderDTO getOrder(@Argument Long orderId) {
      return orderService.getOrderById(orderId);
   }
}
