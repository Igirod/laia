package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.controller.dto.ImageProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ImageProductInputDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * ImageProductServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface ImageProductService {

   /**
    * Método que tiene la obligación de obtener todas las imágenes de un producto
    * buscandolo por su id.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @param page
    * @return List<ImageProductDTO>
    */
   public List<ImageProductDTO> findImagesProductsByProductId(Long productId);

   /**
    * Este método tiene la obligación de cargar una imagen de un producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param imageName
    * @param content
    * @return ImageProductDTO
    */
   public ImageProductDTO uploadImageProduct(String imageName, MultipartFile content);
}
