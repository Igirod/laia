package us.kanddys.laia.modules.ecommerce.services;

import jakarta.mail.MessagingException;
import us.kanddys.laia.modules.ecommerce.controller.dto.MailDTO;

public interface MailSenderService {

   public void sendEmailChangePassword(MailDTO mailDTO) throws MessagingException;

   public void sendUserOrder(MailDTO mailDTO) throws MessagingException;

}
