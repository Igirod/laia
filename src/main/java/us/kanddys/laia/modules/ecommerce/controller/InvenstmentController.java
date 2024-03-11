package us.kanddys.laia.modules.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.services.InvenstmentService;

@Controller
public class InvenstmentController {

   @Autowired
   private InvenstmentService invenstmentService;

   @MutationMapping("cInvenstment")
   public Integer createInvenstment(@Argument Long productId, @Argument Optional<Double> amount,
         @Argument Optional<String> note) {
      return invenstmentService.createInvenstment(productId, amount, note);
   }

   @MutationMapping("dInvenstment")
   public Integer deleteInvenstment(@Argument Long invenstmentId) {
      return invenstmentService.deleteInvenstment(invenstmentId);
   }

   @MutationMapping("uInvenstment")
   public Integer updateInvenstment(@Argument Long invenstmentId, @Argument Long productId,
         @Argument Optional<Double> amount, @Argument Optional<String> note) {
      return invenstmentService.updateInvenstment(invenstmentId, amount, note);
   }
}
