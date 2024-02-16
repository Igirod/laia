package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;

import us.kanddys.laia.modules.ecommerce.controller.dto.ImageProductDTO;

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
}
