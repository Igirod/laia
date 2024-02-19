package us.kanddys.laia.modules.ecommerce.services.storage.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

/**
 * Esta clase implementa las obligaciones de la interface
 * FirebaseStorageService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

   @Override
   public String uploadFile(MultipartFile multipartFile, String imageName) {
      try {
         // Inicializa las opciones de almacenamiento
         StorageOptions storageOptions = StorageOptions.newBuilder()
               .setProjectId("kanddys-1088e")
               .setCredentials(
                     GoogleCredentials.fromStream(new ClassPathResource("firebase_admin.json").getInputStream()))
               .build();
         // Obtiene una instancia de almacenamiento
         Storage storage = storageOptions.getService();
         // Genera el nombre del archivo
         String objectName = imageName;
         // Crea el BlobId
         BlobId blobId = BlobId.of("kanddys-1088e.appspot.com", objectName);
         // Crea el BlobInfo
         BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();
         // Sube el archivo a Firebase Storage
         storage.create(blobInfo, resizeImage(multipartFile));
         // Obtiene la URL de descarga del archivo
         URL url = storage.signUrl(blobInfo, 5, TimeUnit.DAYS, Storage.SignUrlOption.withV4Signature());
         return url.toString();
      } catch (IOException e) {
         e.printStackTrace();
         return null;
      }
   }

   @Override
   public String getFileUrl(String path) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getFileUrl'");
   }

   /**
    * Este método rescala la imagen a una resolución de 300x300.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param image
    * @return byte[]
    */
   public byte[] resizeImage(MultipartFile inputFile) {
      byte[] imageInByte = null;
      try {
         BufferedImage originalImage = ImageIO.read(inputFile.getInputStream());
         BufferedImage resizedImage = new BufferedImage(300, 300,
               originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType());
         resizedImage.getGraphics().drawImage(originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH), 0, 0,
               null);
         resizedImage.getGraphics().dispose();
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         ImageIO.write(resizedImage, "jpg", baos);
         baos.flush();
         imageInByte = baos.toByteArray();
         baos.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return imageInByte;
   }
}
