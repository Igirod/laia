package us.kanddys.laia.services;

import java.util.List;

import us.kanddys.laia.controller.dto.ProductDTO;
import us.kanddys.laia.exception.ProductNotFoundException;

/**
 * Esta interface contiene las obligaciones que debe implementar la
 * clase ProductServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface ProductService {

   /**
    * Este método se encarga de obtener todos los productos pertenecientes
    * a un vendedor.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param slug
    * @pagina
    * @return List<ProductDTO>
    */
   List<ProductDTO> getProductsByMerchantSlug(String slug, Integer pagina);

   /**
    * Este método se encarga de obtener un producto por su id.
    *
    * @author Igirod
    * @version 1.0.0
    * @param id
    * @return ProductDTO
    * @throws ProductNotFoundException 
    */
   public ProductDTO getProductById(Long id) throws ProductNotFoundException;  
}
