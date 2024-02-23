package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;

import us.kanddys.laia.modules.ecommerce.controller.dto.AddressDTO;

/**
 * Esta interface contiene las obligacione que debe implementar la clase
 * AddressServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface AddressService {
   
   /**
    * Este método se encarga de obtener la dirección de un usuario.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param userId
    * @param page
    * @return List<AddressDTO>
    */
   public List<AddressDTO> findAddressByUserId(Integer page, Long userId);
   
}
