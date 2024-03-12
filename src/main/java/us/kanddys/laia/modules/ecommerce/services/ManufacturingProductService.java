package us.kanddys.laia.modules.ecommerce.services;

import java.util.Optional;

/**
 * @author Igirod0
 * @version 1.0.0
 */
public interface ManufacturingProductService {

   public Integer createManufacturingProduct(Long productId, Optional<String> type, Optional<Integer> time);
}
