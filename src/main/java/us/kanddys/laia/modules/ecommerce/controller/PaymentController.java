package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.PaymentDTO;
import us.kanddys.laia.modules.ecommerce.services.PaymentService;

@Controller
public class PaymentController {

   @Autowired
   private PaymentService paymentService;

   @QueryMapping("findPaymentsPaginated")
   public List<PaymentDTO> findPaymentsPaginated(@Argument Integer page) {
      return paymentService.findPaymentsPaginated(page);
   }
}
