package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDTO;
import us.kanddys.laia.modules.ecommerce.exception.ProductNotFoundException;
import us.kanddys.laia.modules.ecommerce.model.Utils.TypeFilter;

/**
 * Esta interface contiene las obligaciones que debe implementar la
 * clase ProductServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.2
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
    * @version 1.0.1
    * @param page
    * @param merchantId
    * @param status     Optional
    * @return List<ProductDTO>
    */
   public List<ProductDTO> getProductsPaginated(Integer page, Long merchantId, Optional<Integer> status);

   /**
    * Este método se encarga de obtener todos los productos segun
    * el tipo de filtrado con un paginado de diez productos por página.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param page
    * @param typeFilter
    * @return List<ProductDTO>
    */
   public List<ProductDTO> getProductsByTypeFilterPaginated(Integer page, TypeFilter typeFilter);

   /**
    * Este método tiene la obligación de actualizar el frontPage de un producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param valueOf
    * @param image
    * @return Integer
    */
   @Modifying
   public Integer updateFrontPage(Long productId, MultipartFile image);

   /**
    * Este método tiene la obligación de crear un producto.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param merchantId
    * @param title
    * @param price
    * @param stock
    * @param status
    * @return Integer
    */
   public Integer createProduct(Long merchantId, Optional<String> title, Optional<Double> price,
         Optional<Integer> stock, Optional<Integer> status);

   /**
    * Este método tiene la obligación de actualizar un producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @param title
    * @param price
    * @param stock
    * @param status
    * @return Integer
    */
   public Integer updateProduct(Long productId, Optional<String> title, Optional<Double> price, Optional<Integer> stock,
         Optional<Integer> status);

   /**
    * Este método tiene la obligación de eliminar un producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @return Integer
    */
   public Integer deleteProduct(Long productId);
}
