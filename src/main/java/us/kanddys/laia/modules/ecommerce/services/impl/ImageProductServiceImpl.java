package us.kanddys.laia.modules.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.ImageProductDTO;
import us.kanddys.laia.modules.ecommerce.services.ImageProductService;
import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

/**
 * Esta clase implementa las obligaciones de ImageProductService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class ImageProductServiceImpl implements ImageProductService {

   @Autowired
   private FirebaseStorageService firebaseStorageService;

   @Transactional(rollbackOn = Exception.class)
   @Override
   public ImageProductDTO uploadImageProduct(MultipartFile multipartFile, String imageName) {
      return new ImageProductDTO(firebaseStorageService.uploadFile(multipartFile, imageName));
   }
}
