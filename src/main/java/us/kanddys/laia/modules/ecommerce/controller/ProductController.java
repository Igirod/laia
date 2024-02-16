package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDTO;
import us.kanddys.laia.modules.ecommerce.services.ProductService;

@Controller
public class ProductController {

   @Autowired
   private ProductService productService;

   @QueryMapping("findProductById")
   public ProductDTO getProductById(@Argument(name = "productId") Long productId) {
      return productService.getProductById(productId);
   }

   @QueryMapping("findProductsPaginated")
   public List<ProductDTO> getProductsPaginated(@Argument(name = "page") Integer page, @Argument(name = "status") Optional<Integer> status) {
      return productService.getProductsPaginated(page, status);
   }
}
