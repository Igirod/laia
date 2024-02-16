package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import us.kanddys.laia.modules.ecommerce.controller.dto.MerchantDTO;
import us.kanddys.laia.modules.ecommerce.services.MerchantService;

@Controller
@RequestMapping("merchant")
public class MerchantController {

   @Autowired
   private MerchantService merchantService;

   @QueryMapping("findMerchantBySlug")
   public MerchantDTO getMerchantBySlug(@Argument String slug) {
      return merchantService.findMerchantBySlug(slug);
   }

   @QueryMapping("findMerchantById")
   public MerchantDTO getMerchantById(@Argument Long merchantId) {
      return merchantService.findMerchantById(merchantId);
   }
}
