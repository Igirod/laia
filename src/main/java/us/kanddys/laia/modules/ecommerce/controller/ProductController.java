package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.ArticleDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.TypeFilter;
import us.kanddys.laia.modules.ecommerce.services.ProductService;

@Controller
public class ProductController {

   @Autowired
   private ProductService productService;

   @QueryMapping("products")
   public List<ProductDTO> getProductsPaginated(@Argument(name = "page") Integer page,
         @Argument(name = "merchantId") Long merchantId,
         @Argument(name = "status") Optional<Integer> status) {
      return productService.getProductsPaginated(page, merchantId, status);
   }

   @QueryMapping("productsTf")
   public List<ProductDTO> getProductsByFilterPaginated(@Argument(name = "page") Integer page,
         @Argument(name = "typeFilter") TypeFilter typeFilter) {
      return productService.getProductsByTypeFilterPaginated(page, typeFilter);
   }

   @MutationMapping("uProduct")
   public Integer updateProduct(@Argument Long productId, @Argument Optional<String> title,
         @Argument Optional<Double> price, @Argument Optional<Integer> stock, @Argument Optional<Integer> status,
         @Argument Optional<String> typeOfSale, Optional<String> typeOfPrice) {
      return productService.updateProduct(productId, title, price, stock, status, typeOfSale, typeOfPrice);
   }

   @MutationMapping("dProduct")
   public Integer deleteProduct(@Argument Long productId) {
      return productService.deleteProduct(productId);
   }

   @QueryMapping("gProduct")
   public ArticleDTO getProduct(@Argument Long productId) {
      return productService.getArticleById(productId);
   }

}
