package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderProductDTO;
import us.kanddys.laia.modules.ecommerce.repository.OrderJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.OrderProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

   @Autowired
   private OrderJpaRepository orderJpaRepository;

   @Autowired
   private OrderProductCriteriaRepository orderProductCriteriaRepository;

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateOrderStatus(Long id, String status) {
      orderJpaRepository.updateStatus(id, status);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateOrderNote(Long id, String status) {
      orderJpaRepository.updateNote(id, status);
      return 1;
   }

   @Override
   public OrderDTO getOrderById(Long orderId) {
      var order = orderJpaRepository.findById(orderId).orElse(null);
      var listProducts = orderProductCriteriaRepository.findOrderProductsByOrderId(orderId).stream()
            .map(OrderProductDTO::new)
            .collect(Collectors.toList());
      return new OrderDTO(order, listProducts);
   }
}
