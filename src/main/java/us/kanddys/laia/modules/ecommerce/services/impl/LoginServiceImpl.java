package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.RCheckEmailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.RLoginUserDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.UserDTO;
import us.kanddys.laia.modules.ecommerce.model.User;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.LoginService;

/**
 * Esta clase implementa las obligaciones de la interfaz LoginService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class LoginServiceImpl implements LoginService {

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Override
   public UserDTO sqqGoogle(String email, Optional<String> name, Optional<String> lastName, Optional<String> phone,
         Optional<String> image) {
      var existUser = userJpaRepository.findUserByEmail(email);
      if (existUser == null) {
         return new UserDTO(userJpaRepository
               .save(new User(name.orElse(null), lastName.orElse(null), email, "cocodrilo", phone.orElse(null),
                     image.orElse(null))),
               1);
      }
      if (existUser.getFirst() == null || existUser.getFirst() == 0) {
         existUser.setFirst(1);
         userJpaRepository.save(existUser);
      }
      return new UserDTO(existUser, 1);
   }

   @Override
   public RLoginUserDTO sqqLogin(Long userId, String password) {
      var userAtributes = userJpaRepository.findPhoneAndImageAndPasswordByUserId(userId);
      if (userAtributes.get("password").toString().equals(password))
         return new RLoginUserDTO(
               (userAtributes.get("phone") == null ? null : Integer.valueOf(userAtributes.get("phone").toString())),
               (userAtributes.get("image") == null ? null : userAtributes.get("image").toString()), 1);
      return new RLoginUserDTO(null, null, 0);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   public RCheckEmailDTO sqqCheck(@Argument String email) {
      var userAtributes = userJpaRepository.findUserIdAndNameAndLastNameByEmail(email);
      if (userAtributes.size() == 0) {
         return new RCheckEmailDTO(0, userJpaRepository.save(new User(true, email)).getId(), null, null);
      } else
         return new RCheckEmailDTO(1,
               (userAtributes.get("id") == null ? null : Long.valueOf(userAtributes.get("id").toString())),
               (userAtributes.get("name") == null ? null : userAtributes.get("name").toString()),
               (userAtributes.get("last_name") == null ? null : userAtributes.get("last_name").toString()));
   }
}
