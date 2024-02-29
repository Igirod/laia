package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.MailDTO;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.MailSenderService;

@Service
public class MailSenderServiceImpl implements MailSenderService {

   @Autowired
   private JavaMailSender javaMailSender;

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   public void sendEmailChangePassword(MailDTO mailDTO) throws MessagingException {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true); // HTML
      helper.setTo(mailDTO.getTo());
      helper.setSubject(mailDTO.getSubject());
      helper.setText(
            "<!DOCTYPE html>\n" +
                  "<html lang=\"es\">\n" +
                  "<head>\n" +
                  "    <meta charset=\"UTF-8\">\n" +
                  "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                  "    <title>Acceso a LAIA</title>\n" +
                  "    <style>\n" +
                  "        body {\n" +
                  "            font-family: Arial, sans-serif;\n" +
                  "            background-color: #f4f4f4;\n" +
                  "            margin: 0;\n" +
                  "            padding: 0;\n" +
                  "        }\n" +
                  "        .container {\n" +
                  "            max-width: 600px;\n" +
                  "            margin: 0 auto;\n" +
                  "            padding: 20px;\n" +
                  "            background-color: #fff;\n" +
                  "            border-radius: 10px;\n" +
                  "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                  "        }\n" +
                  "        h2 {\n" +
                  "            color: #333;\n" +
                  "        }\n" +
                  "        p {\n" +
                  "            color: #666;\n" +
                  "        }\n" +
                  "        strong {\n" +
                  "            font-weight: bold;\n" +
                  "            color: #9747ff;\n" +
                  "        }\n" +
                  "        a {\n" +
                  "            text-decoration: none;\n" +
                  "            background-color: #4CAF50;\n" +
                  "            color: white;\n" +
                  "            padding: 10px 20px;\n" +
                  "            border-radius: 5px;\n" +
                  "            display: inline-block;\n" +
                  "        }\n" +
                  "    </style>\n" +
                  "</head>\n" +
                  "<body>\n" +
                  "    <div class=\"container\">\n" +
                  "        <h2>Acceso a tu LAIA</h2>\n" +
                  "        <p>Aquí te dejamos tu contraseña para futuros ingresos en la misma:</p>\n" +
                  "        <p><strong>" + getPassword(mailDTO.getTo()) + "</strong></p>\n" +
                  "        <p>LAIA entrenada por Daviel - <strong>Coordinador de tu Experiencia con LAIA</strong></p>\n"
                  +
                  "    </div>\n" +
                  "</body>\n" +
                  "</html>",
            true); // true indica que el texto es HTML
      javaMailSender.send(message);
   }

   private String getPassword(String email) {
      userJpaRepository.updatePasswordByUserId(
            userJpaRepository.findByUserEmail(email),
            generateHash());
      return userJpaRepository.getPasswordByUserId(
            userJpaRepository.findByUserEmail(email));
   }

   public static String generateHash() {
      String first = UUID.randomUUID().toString().substring(0, 6);
      String last = UUID.randomUUID().toString().substring(0, 6);
      String time = Long.toString(System.currentTimeMillis(), 36);
      return first + "-" + time + "-" + last;
   }
}
