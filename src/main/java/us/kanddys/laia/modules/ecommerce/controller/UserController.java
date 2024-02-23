package us.kanddys.laia.modules.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.services.UserService;

@Controller
public class UserController {

   @Autowired
   private UserService userService;

   @QueryMapping("cUserE")
   public Integer checkUserEmail(@Argument Long userId, @Argument String email, @Argument String password) {
      return userService.checkEmail(userId, email, password);
   }

   @QueryMapping("lUser")
   public Integer loginUser(@Argument String email, @Argument String password) {
      return userService.loginUser(email, password);
   }

   @MutationMapping("uUser")
   public Integer updateUser(@Argument Long userId, @Argument Optional<String> email,
         @Argument Optional<String> password, @Argument Optional<String> phone, @Argument Optional<String> lastName,
         @Argument Optional<String> name) {
      return userService.updateUser(userId, email, password, phone, lastName, name);
   }
}
