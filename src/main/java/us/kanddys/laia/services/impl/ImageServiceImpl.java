package us.kanddys.laia.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.controller.dto.ImageProductDTO;
import us.kanddys.laia.exception.IOJavaException;
import us.kanddys.laia.repository.ImageProductJpaRepository;
import us.kanddys.laia.services.ImageService;

/**
 * Esta clase contiene la implementación de la interfaz ImageService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class ImageServiceImpl implements ImageService {

   @Autowired
   private ImageProductJpaRepository imageProductJpaRepository;

   @Override
   public List<ImageProductDTO> getImagesByProductId(Long productId) {
      return imageProductJpaRepository.findAllByProductId(productId).stream().map(t -> {
         try {
            return new ImageProductDTO(t);
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         }
      }).collect(Collectors.toList());
   }
}
