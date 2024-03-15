package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
   public UserDTO SQQGoogle(String email, Optional<String> name, Optional<String> lastName, Optional<String> phone,
         Optional<String> address) {
      var existUser = userJpaRepository.findUserByEmail(email);
      if (existUser == null) {
         return new UserDTO(userJpaRepository
               .save(new User(null, name.orElse(null), lastName.orElse(null), email, phone.orElse(null),
                     address.orElse(null), null, 0)),
               1);
      }
      if (existUser.getFirst() == null || existUser.getFirst() == 0) {
         existUser.setFirst(1);
         userJpaRepository.save(existUser);
      }
      return new UserDTO(existUser, 1);
   }
}
