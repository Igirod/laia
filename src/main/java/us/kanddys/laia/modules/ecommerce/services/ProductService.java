package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;

import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDTO;
import us.kanddys.laia.modules.ecommerce.exception.ProductNotFoundException;

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
    * @param page
    * @return List<ProductDTO>
    */
   List<ProductDTO> getProductsByMerchantSlug(String slug, Integer page);

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
    * @return List<ProductDTO>
    */
   public List<ProductDTO> getProducts(Integer page);
}
