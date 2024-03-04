package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderPaymentDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderProductDTO;
import us.kanddys.laia.modules.ecommerce.repository.OrderJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.OrderProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.services.OrderService;
import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

@Service
public class OrderServiceImpl implements OrderService {

   @Autowired
   private OrderJpaRepository orderJpaRepository;

   @Autowired
   private FirebaseStorageService firebaseStorageService;

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

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public OrderPaymentDTO updateVoucher(MultipartFile voucher, Long orderId) {
      var voucherUrl = firebaseStorageService.uploadFile(voucher, "vouchers");
      orderJpaRepository.updateVoucherByOrderId(voucherUrl, orderId);
      // * Se utiliza este tipo de DTO para no crear uno nuevo.
      return new OrderPaymentDTO(voucherUrl, null);
   }
}
