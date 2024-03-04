package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import us.kanddys.laia.modules.ecommerce.services.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {

   @Autowired
   private UserService userService;

   @RequestMapping(method = { RequestMethod.POST }, value = "/upload", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public Integer uploadUserImage(@RequestPart MultipartFile image, @RequestPart String userId) {
      return userService.updateProfileImage(Long.valueOf(userId), image);
   }
}
