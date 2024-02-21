package us.kanddys.laia.modules.ecommerce.services.storage;

import org.springframework.web.multipart.MultipartFile;

/**
 * Esta interfaz contiene las obligaciones necesarias que debe implementar
 * la clase FirebaseServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface FirebaseStorageService {

   /**
    * Este método tiene la obligación de subir un archivo a Firebase Storage.
    * 
    * @author Igirod
    * @version 1.0.0
    * @param multipartFile
    * @param aspectRatio11
    * @return String
    */
   public String uploadImage(MultipartFile multipartFile, Boolean aspectRatio11);
}
