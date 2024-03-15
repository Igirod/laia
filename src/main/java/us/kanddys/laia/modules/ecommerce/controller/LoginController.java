package us.kanddys.laia.modules.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import us.kanddys.laia.modules.ecommerce.controller.dto.UserDTO;
import us.kanddys.laia.modules.ecommerce.services.LoginService;

@Controller
public class LoginController {

   @Autowired
   private LoginService loginService;

   @QueryMapping("SQQGoogle")
   public UserDTO SQQGoogle(@Argument String email, @Argument Optional<String> name,
         @Argument Optional<String> lastName, @Argument Optional<String> phone, @Argument Optional<String> address) {
      return loginService.SQQGoogle(email, name, lastName, phone, address);
   }
}
