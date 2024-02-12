package us.kanddys.laia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import us.kanddys.laia.controller.dto.ProductDTO;
import us.kanddys.laia.services.ProductService;

@Controller
@RequestMapping("/shop")
public class ShopController {

   @Autowired
   private ProductService productService;

   @QueryMapping("findProductsByMerchantSlug")
   public List<ProductDTO> getProductsByMerchantSlug(@Argument(name = "slug") String slug,
         @Argument(name = "pagina") Integer pagina) {
      return productService.getProductsByMerchantSlug(slug, pagina);
   }

   @QueryMapping("findProductById")
   public ProductDTO getProductById(@Argument(name = "id") Long id) {
      return productService.getProductById(id);
   }
}
