package us.kanddys.laia.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import us.kanddys.laia.controller.dto.ImageProductDTO;
import us.kanddys.laia.services.ImageService;

/**
 * Esta clase contiene la implementación de la interfaz ImageService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class ImageServiceImpl implements ImageService {

   @Override
   public List<ImageProductDTO> getImagesByProductId(Long productId) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getImagesByProductId'");
   }
}
