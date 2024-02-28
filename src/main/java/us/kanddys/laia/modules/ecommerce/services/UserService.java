package us.kanddys.laia.modules.ecommerce.services;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.controller.dto.UserDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar
 * la clase UserServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
public interface UserService {

   /**
    * Este método verifica si el correo se encuentra registrado.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param userId
    * @param email
    * @param invoiceId
    * @return Integer
    */
   public UserDTO checkEmail(Long userId, String email, Optional<Long> invoiceId);

   /**
    * Este método verifica si el email y la contraseña son correctos.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param email
    * @param password
    * @return Integer
    */
   public UserDTO loginUser(Long userId, String email, String password);

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
   public Integer updateUser(Long userId, Optional<String> email, Optional<String> password, Optional<String> phone,
         Optional<String> lastName, Optional<String> name);

   /**
    * Este método tiene la obligación de actualizar la imagen de perfil del
    * usuario.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param userId
    * @param image
    * @return Integer
    */
   public Integer updateProfileImage(Long userId, MultipartFile image);
}
