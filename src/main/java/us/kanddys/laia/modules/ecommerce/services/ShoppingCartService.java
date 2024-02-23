package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;

import us.kanddys.laia.modules.ecommerce.controller.dto.ShoppingCartDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ShoppingCartProductDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * ShoppingCartServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
public interface ShoppingCartService {
   
   /**
    * Este método se encarga de obtener el carrito de compras de un usuario.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param merchantId
    * @param invoiceId
    * @return ShoppingCartDTO
    */
   public ShoppingCartDTO getCart(Long merchantId, Long invoiceId);

   /**
    * Este método retorna la lista de productos del carrito de compras de un usuario.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param shoppingCartId
    * @return List<ShoppingCartProduct>
    */
   public List<ShoppingCartProductDTO> getShoppingCartProducts(Long shoppingCartId);
}
