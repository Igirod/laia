package us.kanddys.laia.modules.ecommerce.services;

import java.util.Optional;

import us.kanddys.laia.modules.ecommerce.controller.dto.RCheckEmailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.RLoginUserDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.UserDTO;

/**
 * Esta clase contiene las obligaciones que debe implementar la clase
 * LoginServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.1
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
    * @param image
    * @return UserDTO
    */
   public UserDTO sqqGoogle(String email, Optional<String> name, Optional<String> lastName, Optional<String> phone,
         Optional<String> image);

   /**
    * Este método verifica si el correo se encuentra registrado.
    *
    * @author Igirod0
    * @version 1.0.2
    * @param userId
    * @param email
    * @return Map<String, Object>
    */
   public RCheckEmailDTO sqqCheck(String email);

   /**
    * Este método verifica si el email y la contraseña son correctos.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param userId
    * @param password
    * @return RLoginUserDTO
    */
   public RLoginUserDTO sqqLogin(Long userId, String password);

}
