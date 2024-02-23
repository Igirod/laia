package us.kanddys.laia.modules.ecommerce.services;

import java.util.Optional;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedShopDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * ShopServiceImpl.
 */
public interface CombinedService {

	CombinedShopDTO findCombinedShop(String slug, Optional<Long> userId);

   CombinedProductDTO findCombinedProduct(Long productId, String slug, Optional<Long> userId);
}
