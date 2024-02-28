package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.controller.dto.ImageProductDTO;
import us.kanddys.laia.modules.ecommerce.services.ImageProductService;

@RestController
@RequestMapping("/image-product")
public class ImageProductRestController {

   @Autowired
   private ImageProductService imageProductService;

   @RequestMapping(method = { RequestMethod.POST }, value = "/upload", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public ImageProductDTO uploadImageProduct(@RequestPart MultipartFile image, @RequestPart String productId) {
      return imageProductService.uploadImageProduct(image, Long.valueOf(productId));
   }
}
