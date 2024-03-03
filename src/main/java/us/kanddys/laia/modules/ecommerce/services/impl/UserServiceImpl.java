package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.MailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.UserDTO;
import us.kanddys.laia.modules.ecommerce.model.User;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.MailSenderService;
import us.kanddys.laia.modules.ecommerce.services.UserService;
import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Autowired
   private FirebaseStorageService firebaseStorageService;

   @Autowired
   private MailSenderService mailSenderService;

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   public Integer checkEmail(@Argument Long userId, @Argument String email) {
      var userAtributes = userJpaRepository.findUserIdByEmail(email);
      if (userAtributes.get("email") == null) {
         if (!email.equals(userJpaRepository.findEmailByUserId(userId))) {
            return -1;
         }
         userJpaRepository.updateEmailByUserId(userId, email);
         try {
            mailSenderService.sendEmailChangePassword(new MailDTO(email, "Bienvenido", "", ""));
         } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo");
         }
         return 0;
      }
      if (userAtributes.get("email") != null && Long.valueOf(userAtributes.get("id").toString()).equals(userId))
         return 1;
      return Integer.valueOf(userAtributes.get("id").toString());
   }

   @Override
   public UserDTO loginUser(@Argument Long userId, @Argument String email, @Argument String password) {
      Optional<User> user = userJpaRepository.findById(userId);
      User userError;
      if (user.isPresent()) {
         if (user.get().getPassword().equals(password) && user.get().getMail().equals(email))
            return new UserDTO(user.get(), 1);
         else
            userError = new User(0L, null, null, null, null, null, null, 0);
         return new UserDTO(userError, 0);
      } else
         userError = new User(0L, null, null, null, null, null, null, 0);
      return new UserDTO(userError, 0);
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateUser(Long userId, Optional<String> email, Optional<String> password, Optional<String> phone,
         Optional<String> lastName, Optional<String> name) {
      var user = userJpaRepository.findById(userId);
      if (user.isPresent()) {
         var userToUpdate = user.get();
         email.ifPresent(userToUpdate::setMail);
         password.ifPresent(userToUpdate::setPassword);
         phone.ifPresent(userToUpdate::setPhone);
         lastName.ifPresent(userToUpdate::setLastName);
         name.ifPresent(userToUpdate::setName);
         userJpaRepository.save(userToUpdate);
         return 1;
      } else
         return 0;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateProfileImage(Long userId, MultipartFile image) {
      if (userJpaRepository.existsById(userId)) {
         userJpaRepository.updateUserImage(userId, firebaseStorageService.uploadFile(image, "userImages"));
         return 1;
      } else
         return 0;
   }
}
