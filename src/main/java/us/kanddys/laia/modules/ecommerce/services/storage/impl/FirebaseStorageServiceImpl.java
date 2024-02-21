package us.kanddys.laia.modules.ecommerce.services.storage.impl;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import java.util.concurrent.TimeUnit;

import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;
import us.kanddys.laia.modules.ecommerce.services.storage.utils.ImageFormatUtils;

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
   public String uploadImage(MultipartFile multipartFile, Boolean aspectRatio11) {
      try {
         StorageOptions storageOptions = StorageOptions.newBuilder()
               .setProjectId("kanddys-1088e")
               .setCredentials(
                     GoogleCredentials.fromStream(new ClassPathResource("firebase_admin.json").getInputStream()))
               .build();
         Storage storage = storageOptions.getService();
         BlobInfo blobInfo = null;
         if (aspectRatio11)
            blobInfo = BlobInfo
                  .newBuilder(BlobId.of("kanddys-1088e.appspot.com", multipartFile.getOriginalFilename() + "_1:1"))
                  .setContentType("image/png").build();
         else
            blobInfo = BlobInfo
                  .newBuilder(BlobId.of("kanddys-1088e.appspot.com", multipartFile.getOriginalFilename() + "_16:9"))
                  .setContentType("image/png").build();
         storage.create(blobInfo, ImageFormatUtils.resizeImage(multipartFile, aspectRatio11));
         return storage.signUrl(blobInfo, 3650, TimeUnit.DAYS).toString();
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
   }
}
