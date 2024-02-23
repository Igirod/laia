package us.kanddys.laia.modules.ecommerce.services;

import java.util.Optional;

/**
 * Esta interface contiene las obligaciones que debe implementar 
 * la clase UserServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface UserService {
   
   /**
    * Este método verifica si el email y la contraseña son correctos.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param email
    * @param password
    * @return
    */
   public Integer checkEmail(Long userId, String email, String password);

   /**
    * Este método verifica si el email y la contraseña son correctos.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param email
    * @param password
    * @return Integer
    */
	public Integer loginUser(String email, String password);

   /**
    * Este método actualiza los datos del usuario.
    * 
    * @param userId
    * @param email
    * @param password
    * @param phone
    * @param lastName
    * @param name
    * @return Integer
    */
	public Integer updateUser(Long userId, Optional<String> email, Optional<String> password, Optional<String> phone, Optional<String> lastName, Optional<String> name);
}
