package us.kanddys.laia.services;

import java.util.List;

import us.kanddys.laia.controller.dto.ImageProductDTO;

/**
 * Esta interfaz contiene las obligaciones que debe implementar la
 * clase ImageServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface ImageService {
   
   /**
    * Este método se encarga de obtener todas las imágenes pertenecientes a un
    * producto.
    *
    * @param productId
    * @return List<ImageProduct>
    */
   public List<ImageProductDTO> getImagesByProductId(Long productId);
}
