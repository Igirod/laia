package us.kanddys.laia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.controller.dto.ImageProductDTO;
import us.kanddys.laia.services.ImageService;

@Controller
public class ImageController {

   @Autowired
   private ImageService imageService;

   @QueryMapping("findImagesByProductId")
   public List<ImageProductDTO> findImagesByProductId(@Argument(name = "id") Long id) {
      return imageService.getImagesByProductId(id);
   }
}
