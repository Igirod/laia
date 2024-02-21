package us.kanddys.laia.modules.ecommerce.services;

import us.kanddys.laia.modules.ecommerce.controller.dto.ShoppingCartDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * ShoppingCartServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface ShoppingCartService {
   
   /**
    * Este método se encarga de obtener el carrito de compras de un usuario.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param merchantId
    * @param userId
    * @return ShoppingCartDTO
    */
   public ShoppingCartDTO getCart(Long merchantId, Long userId);
}
