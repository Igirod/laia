package us.kanddys.laia.modules.ecommerce.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.controller.dto.ImageProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ImageProductInputDTO;
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

	@Override
	public ImageProductDTO uploadImageProduct(ImageProductDTO imageProductDTO) {
      try {
         var contentByte = content.getBytes();
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
      imageProductJpaRepository.save(new ImageProductDTO(null, , imageName))
      
	}

}
