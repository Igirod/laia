package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductRestController {

   @Autowired
   private ProductService productService;

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @RequestMapping(method = { RequestMethod.POST }, value = "/upload", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public Integer uploadFrontPage(@RequestPart MultipartFile image, @RequestPart String productId) {
      return productService.updateFrontPage(Long.valueOf(productId), image);
   }
}
