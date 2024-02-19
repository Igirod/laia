package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.ImageProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ImageProductInputDTO;
import us.kanddys.laia.modules.ecommerce.services.ImageProductService;

@Controller
public class ImageProductController {

   @Autowired
   private ImageProductService imageProductService;

   @QueryMapping("imagesPId")
   public List<ImageProductDTO> getImagesProductsByProductId(@Argument Long productId) {
      return imageProductService.findImagesProductsByProductId(productId);
   }

   @MutationMapping("uIProduct")
   public ImageProductDTO createImageProductDTO(@Argument ImageProductInputDTO imageProductInputDTO) {
      return imageProductService.uploadImageProduct(imageProductInputDTO);
   }
}
