package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

   @Transactional(rollbackOn = { Exception.class , RuntimeException.class })
   public void sendEmail(MailDTO mailDTO) {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(mailDTO.getTo());
      message.setSubject(mailDTO.getSubject());
      message.setText("PASSWORD IN EMAIL\r\n" + //
            "El acceso a tu LAIA está listo.\r\n" + //
            "Aquí te dejamos tu contraseña para futuros ingresos en la misma:\r\n" + //
            "" + getPassword(mailDTO.getTo()) + "\r\n" + //
            "\r\n" + //
            "LAIA entrenada por Daviel\r\n" + //
            "\r\n" + //
            "Coordinador de tu Experiencia con LAIA \r\n" + //
            "ACCESO SIN CLAVE ---------------------\r\n" + //
            "El acceso sin claves a tu LAIA está listo. Simplemente haz clic en el siguiente enlace "
            + mailDTO.getAccessUrl()
            + " y entra de forma segura. Ten en cuenta que el enlace es válido solo por unos minutos.\r\n"
            + //
            "\r\n" + //
            "Saludos,\r\n" + //
            "LAIA entrenada por Daviel\r\n" + //
            "Coordinador de tu Experiencia con LAIA");
      javaMailSender.send(message);
   }

   private String getPassword(String email) {
      userJpaRepository.updatePasswordByUserId(userJpaRepository.findByUserEmail(email), hashNumbers(concatenateNumbers(generateRandomNumbers(10))));
      return userJpaRepository.getPasswordByUserId(userJpaRepository.findByUserEmail(email));
   }

   private static List<Integer> generateRandomNumbers(int count) {
      List<Integer> numbers = new ArrayList<>();
      Random random = new Random();
      for (int i = 0; i < count; i++) {
         numbers.add(random.nextInt(10000000)); // Genera números aleatorios entre 0 y 9999999
      }
      return numbers;
   }

   private static String concatenateNumbers(List<Integer> numbers) {
      StringBuilder sb = new StringBuilder();
      for (Integer number : numbers) {
         sb.append(number);
      }
      return sb.toString();
   }

   private static String hashNumbers(String concatenatedNumbers) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      return encoder.encode(concatenatedNumbers);
   }
}
