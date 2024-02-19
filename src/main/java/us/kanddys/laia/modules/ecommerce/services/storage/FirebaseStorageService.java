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
    * @param file
    * @param path
    * @return String
    */
   public String uploadFile(MultipartFile multipartFile, String imageName);

   /**
    * Este método tiene la obligación de obtener la URL de un archivo de Firebase
    * Storage.
    * 
    * @param path
    * @return String
    */
   public String getFileUrl(String path);
}
