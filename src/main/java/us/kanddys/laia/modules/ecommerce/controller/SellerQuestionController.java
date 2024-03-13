package us.kanddys.laia.modules.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.services.SellerQuestionProductService;
import us.kanddys.laia.modules.ecommerce.services.SellerQuestionService;

@Controller
public class SellerQuestionController {

   @Autowired
   private SellerQuestionService sellerQuestionService;

   @Autowired
   private SellerQuestionProductService sellerQuestionProductService;

   @MutationMapping("cSellerQuestion")
   public Long createSellerQuestion(@Argument String question, @Argument Optional<Integer> required,
         @Argument Optional<String> type, @Argument Optional<Integer> limit) {
      return sellerQuestionService.createQuestion(question, required, type, limit);
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

   @MutationMapping("cSellerQuestionProduct")
   public Integer createSellerQuestionProduct(@Argument Long productId, @Argument Long sellerQuestionId) {
      return sellerQuestionProductService.createSellerQuestionProduct(productId, sellerQuestionId);
   }

   @MutationMapping("dSellerQuestionProduct")
   public Integer deleteSellerQuestionProduct(@Argument Long productId, @Argument Long sellerQuestionId) {
      return sellerQuestionProductService.deleteSellerQuestionProduct(productId, sellerQuestionId);
   }
}
