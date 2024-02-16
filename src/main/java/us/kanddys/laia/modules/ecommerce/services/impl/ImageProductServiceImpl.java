package us.kanddys.laia.modules.ecommerce.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.ImageProductDTO;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.repository.ImageProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.ImageProductService;

/**
 * Esta clase implementa las obligaciones de ImageProductService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class ImageProductServiceImpl implements ImageProductService {

   @Autowired
   private ImageProductJpaRepository imageProductJpaRepository;

   @Override
   public List<ImageProductDTO> findImagesProductsByProductId(Long productId) {
      return imageProductJpaRepository.findAllByProductId(productId).stream().map(t -> {
         try {
            return new ImageProductDTO(t);
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         }
      }).collect(Collectors.toList());
   }

}
