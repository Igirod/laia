package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.KeyWordDTO;
import us.kanddys.laia.modules.ecommerce.services.KeyWordProductService;
import us.kanddys.laia.modules.ecommerce.services.KeyWordService;

@Controller
public class KeywordController {

   @Autowired
   private KeyWordService keywordService;

   @Autowired
   private KeyWordProductService keywordProductService;

   @MutationMapping("cKeyWord")
   public Long createKeyWordProduct(@Argument String word) {
      return keywordService.createKeyWord(word);
   }

   @MutationMapping("uKeyWord")
   public Integer updateKeyWordProduct(@Argument Long keyWordId, @Argument String word) {
      return keywordService.updateKeyWord(keyWordId, word);
   }

   @MutationMapping("dKeyWord")
   public Integer deleteKeyWordProduct(@Argument Long keyWordId) {
      return keywordService.deleteKeyWord(keyWordId);
   }

   @MutationMapping("cKeyWordProduct")
   public Integer addKeyWordProduct(@Argument Long productId, @Argument Long keyWordId) {
      return keywordProductService.createKeyWordProduct(productId, keyWordId);
   }

   @MutationMapping("dKeyWordProduct")
   public Integer deleteKeyWordProduct(@Argument Long productId, @Argument Long keyWordId) {
      return keywordProductService.deleteKeyWordProduct(productId, keyWordId);
   }

   @QueryMapping("gAdminSellKeyWords")
   public List<KeyWordDTO> getKeywords(@Argument Long productId) {
      return keywordService.getKeywordsByProductId(productId);
   }

   // @QueryMapping("wAdminSellKeyWords")
   // public List<KeyWordDTO> getKeywords() {
   // return keywordService.getKeywords();
   // }

   // @MutationMapping("uAdminSellKeyWords")
   // public List<KeyWordDTO> updateKeywords(@Argument Long productId, @Argument
   // List<Long> keyWordIds) {
   // return keywordService.updateKeywords(productId, keyWordIds);
   // }
}
