package us.kanddys.laia.modules.ecommerce.services;

import us.kanddys.laia.modules.ecommerce.controller.dto.MailDTO;

public interface MailSenderService {
   
   public void sendEmail(MailDTO mailDTO);
   
}
