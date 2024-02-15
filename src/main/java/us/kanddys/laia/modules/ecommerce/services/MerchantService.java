package us.kanddys.laia.modules.ecommerce.services;
import us.kanddys.laia.modules.ecommerce.controller.dto.MerchantDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la
 * clase MerchantServiceImpl.
 */
public interface MerchantService {
   
   /**
    * Este método tiene la obligación de buscar un comerciante por su slug.
    * 
    * @author Igirod0 
    * @version 1.0.0
    * @param slug
    * @return MerchantDTO
    */
   public MerchantDTO findMerchantBySlug(String slug);
}
