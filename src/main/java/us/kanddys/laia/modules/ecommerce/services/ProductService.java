package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Optional;

import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDTO;
import us.kanddys.laia.modules.ecommerce.exception.ProductNotFoundException;

/**
 * Esta interface contiene las obligaciones que debe implementar la
 * clase ProductServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
public interface ProductService {

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

   /**
    * Este método se encarga de obtener todos los productos con un
    * paginado de diez productos por página.
    *
    * @author Igirod
    * @version 1.0.0
    * @param page
    * @param status Optional
    * @return List<ProductDTO>
    */
   public List<ProductDTO> getProductsPaginated(Integer page, Optional<Integer> status);
}
