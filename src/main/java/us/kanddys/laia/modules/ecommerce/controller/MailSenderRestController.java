package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import us.kanddys.laia.modules.ecommerce.controller.dto.MailDTO;
import us.kanddys.laia.modules.ecommerce.services.MailSenderService;

@RestController
@RequestMapping("/mail")
public class MailSenderRestController {

   @Autowired
   private MailSenderService mailSenderService;

   @PostMapping("/send/change-password")
   public ResponseEntity<String> sendEmailChangePassword(@RequestBody MailDTO mailDTO) throws MessagingException {
      mailSenderService.sendEmailChangePassword(mailDTO);
      return ResponseEntity.ok("Mail sent successfully!");
   }
}
