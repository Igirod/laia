package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDetailDTO;
import us.kanddys.laia.modules.ecommerce.services.ProductDetailService;

@Controller
@RequestMapping("product-detail")
public class ProductDetailController {

   @Autowired
   private ProductDetailService productDetailService;

   @QueryMapping("findProductDetails")
   public List<ProductDetailDTO> getProductDetails(@Argument(name = "productId") Long productId) {
      return productDetailService.getProductDetailsByProductId(productId);
   }
}
