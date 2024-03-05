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

   public String uploadFile(MultipartFile multipartFile, String folderName) {
      try {
         StorageOptions storageOptions = StorageOptions.newBuilder()
               .setProjectId("laia-c5d59")
               .setCredentials(
                     GoogleCredentials.fromStream(new ClassPathResource("firebase_admin.json").getInputStream()))
               .build();
         Storage storage = storageOptions.getService();
         String objectName = folderName + "/" + multipartFile.getOriginalFilename();
         BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("laia-c5d59.appspot.com", objectName))
               .setContentType("image/png")
               .build();
         storage.create(blobInfo, ImageFormatUtils.resizeImage(multipartFile));
         return storage.signUrl(blobInfo, 3650, TimeUnit.DAYS).toString();
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
   }
}
