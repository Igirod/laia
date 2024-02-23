package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.UserService;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserJpaRepository userJpaRepository;

   public Integer checkEmail(@Argument Long userId, @Argument String email, @Argument String password) {
      var id = userJpaRepository.existByUserEmail(email);
      if (id != null) {
         if (userId.intValue() == id) {
            return -1;
         }
         return 1;
      } else
         return 0;
   }

   @Override
   public Integer loginUser(@Argument String email, @Argument String password) {
      var id = userJpaRepository.existByUserEmail(email);
      if (id != null)
         return 1;
      else
         return 0;
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
}
