package us.kanddys.laia.modules.ecommerce.services;

import java.util.Optional;

import us.kanddys.laia.modules.ecommerce.controller.dto.UserDTO;

/**
 * Esta clase contiene las obligaciones que debe implementar la clase
 * LoginServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface LoginService {

   /**
    * Este método verifica el inicio de sesión con google.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param email
    * @param name
    * @param lastName
    * @param phone
    * @param address
    * @return UserDTO
    */
   public UserDTO SQQGoogle(String email, Optional<String> name, Optional<String> lastName, Optional<String> phone,
         Optional<String> address);

}
