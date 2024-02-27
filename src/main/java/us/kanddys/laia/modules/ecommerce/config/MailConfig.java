package us.kanddys.laia.modules.ecommerce.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

   @Bean
   public JavaMailSender getJavaMailSender() {
      JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
      mailSender.setHost("smtp.gmail.com");
      mailSender.setPort(587);
      mailSender.setUsername("laiaecommercetest@gmail.com");
      mailSender.setPassword("a b g r h k e u z y m u l g h z");

      Properties props = mailSender.getJavaMailProperties();
      props.put("mail.transport.protocol", "smtp");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.debug", "true"); // Activa el modo de depuración para ver los logs

      return mailSender;
   }

}
