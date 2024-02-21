package us.kanddys.laia.modules.ecommerce.services;

import us.kanddys.laia.modules.ecommerce.controller.dto.ShoppingCartProductDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase 
 * ShoppingCartProductServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface ShoppingCartProductService {
   
   /**
    * Este método se encarga de agregar un producto al carrito de compras.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param shoppingCartId
    * @param productId
    * @return ShoppingCartProductDTO
    */
   public ShoppingCartProductDTO addShoppingCartProduct(Long shoppingCartId, Long productId); 
}
