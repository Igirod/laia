package us.kanddys.laia.modules.ecommerce.services;

import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.controller.dto.ImageProductDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * ImageProductServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
public interface ImageProductService {

   /**
    * Este método tiene la obligación de cargar una imagen de un producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param multipartFile
    * @param imageName
    * @return ImageProductDTO
    */
   public ImageProductDTO uploadImageProduct(MultipartFile multipartFile, String imageName);
}
