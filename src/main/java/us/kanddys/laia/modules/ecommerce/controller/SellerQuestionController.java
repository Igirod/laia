package us.kanddys.laia.modules.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import us.kanddys.laia.modules.ecommerce.services.SellerQuestionService;

@Controller
public class SellerQuestionController {

   @Autowired
   private SellerQuestionService sellerQuestionService;

   @MutationMapping("cSellerQuestion")
   public Long createSellerQuestion(@Argument String question, @Argument Optional<Integer> required,
         @Argument String type, @Argument Optional<Integer> limit, @Argument Long productId) {
      return sellerQuestionService.createQuestion(question, required, type, limit, productId);
   }

   @MutationMapping("dSellerQuestion")
   public Integer deleteSellerQuestion(@Argument Long sellerQuestionId) {
      return sellerQuestionService.deleteQuestion(sellerQuestionId);
   }

   @MutationMapping("uSellerQuestion")
   public Integer updateSellerQuestion(@Argument Long sellerQuestionId, @Argument Optional<String> question,
         @Argument Optional<String> type,
         @Argument Optional<Integer> limit, @Argument Optional<Integer> required) {
      return sellerQuestionService.updateQuestion(sellerQuestionId, question, type, limit, required);
   }
}
