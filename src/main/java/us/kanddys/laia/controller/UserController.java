package us.kanddys.laia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.entities.UserDTO;
import us.kanddys.laia.services.UserService;

@Controller
public class UserController {

   @Autowired
   private UserService userService;

   @QueryMapping("findAllUsers")
   public List<UserDTO> findAllUsers() {
      return userService.findAllUsers();
   }

}
