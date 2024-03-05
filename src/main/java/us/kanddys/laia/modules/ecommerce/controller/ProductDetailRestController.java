package us.kanddys.laia.modules.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDetailDTO;
import us.kanddys.laia.modules.ecommerce.services.ProductDetailService;

@RestController
@RequestMapping("/product-detail")
public class ProductDetailRestController {

   @Autowired
   private ProductDetailService productDetailService;

   @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "/upload", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public ProductDetailDTO uploadProductDetail(@RequestPart Optional<String> title,
         @RequestPart Optional<MultipartFile> image, @RequestPart String productId,
         @RequestPart Optional<String> description) {
      return productDetailService.createProductDetail(title, image, Long.valueOf(productId), description);
   }
}
