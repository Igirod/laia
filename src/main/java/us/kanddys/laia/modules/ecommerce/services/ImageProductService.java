package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;

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
    * @param productId
    * @return ImageProductDTO
    */
   public ImageProductDTO uploadImageProduct(MultipartFile multipartFile, Long productId);

   /**
    * Este método tiene la obligación de traer todas las imagenes de un
    * producto buscando por si id.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @return List<ImageProductDTO>
    */
   public List<ImageProductDTO> findImagesProductByProductId(Long productId);
}
