package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.controller.dto.OrderPaymentDTO;
import us.kanddys.laia.modules.ecommerce.services.OrderService;

@RestController
public class OrderRestController {

   @Autowired
   private OrderService orderService;

   @RequestMapping(method = { RequestMethod.POST }, value = "/update-voucher", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public OrderPaymentDTO updateOrderVoucher(@RequestPart MultipartFile voucher,
         @RequestPart String orderId,
         @RequestPart String paymentId) {
      return orderService.updateVoucher(voucher, Long.valueOf(orderId));
   }

}
