package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.MailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderProductDTO;
import us.kanddys.laia.modules.ecommerce.repository.OrderJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.OrderProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.MailSenderService;

@Service
public class MailSenderServiceImpl implements MailSenderService {

   @Autowired
   private JavaMailSender javaMailSender;

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Autowired
   private OrderJpaRepository orderJpaRepository;

   @Autowired
   private OrderProductCriteriaRepository orderProductCriteriaRepository;

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
                  "</head>\n" +
                  "<body>\n" +
                  "    <div class=\"container\">\n" +
                  "        <h2>Acceso a tu LAIA</h2>\n" +
                  "        <p>Aquí te dejamos tu contraseña para futuros ingresos en la misma:</p>\n" +
                  "        <p><strong>" + getPassword(mailDTO.getTo()) + "</strong></p>\n" +
                  "        <p>LAIA entrenada por Daviel -</p>\n" +
                  "        <p><strong>Coordinador de tu Experiencia con LAIA</strong></p>\n" +
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

   @Override
   public void sendUserOrder(MailDTO mailDTO) throws MessagingException {
      List<OrderProductDTO> products = orderProductCriteriaRepository.findOrderProductsByOrderId(mailDTO.getOrderId())
            .stream().map(t -> {
               try {
                  return new OrderProductDTO(t);
               } catch (Exception e) {
                  throw new RuntimeException(e.getMessage());
               }
            }).collect(Collectors.toList());
      OrderDTO orderDTO = new OrderDTO(orderJpaRepository.findById(mailDTO.getOrderId()).get(), products);
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true); // HTML
      helper.setTo(mailDTO.getTo());
      helper.setSubject(mailDTO.getSubject());
      var emailContent = "<div style=\"font-family: Arial, sans-serif; margin: 0; padding: 0;\">" +
            "<div style=\"max-width: 800px; margin: 20px auto; padding: 20px; border: 1px solid #ccc; border-radius: 8px; background-color: #f9f9f9;\">"
            +
            "<h1 style=\"text-align: center;\">Factura</h1>" +
            "<p><strong>Reserva:</strong> " + orderDTO.getReservation() + "</p>" +
            "<p><strong>Estado:</strong> " + getOrderStatus(orderDTO.getStatus()) + "</p>" +
            "<p><strong>Destinatario:</strong>" + mailDTO.getTo() + "</p>" +
            "<p><strong>Dirección de envío:</strong> " + orderDTO.getAddressDirection() + "</p>" +
            "<p><strong>Creación:</strong> " + orderDTO.getCreateAt() + "</p>" +
            "<table style=\"width: 100%; border-collapse: collapse; margin-top: 20px;\">" +
            "<thead>" +
            "<tr>" +
            "<th style=\"border: 1px solid #ddd; padding: 8px; text-align: left; background-color: #f2f2f2;\">Producto</th>"
            +
            "<th style=\"border: 1px solid #ddd; padding: 8px; text-align: left; background-color: #f2f2f2;\">Cantidad</th>"
            +
            "<th style=\"border: 1px solid #ddd; padding: 8px; text-align: left; background-color: #f2f2f2;\">Precio unitario</th>"
            +
            "<th style=\"border: 1px solid #ddd; padding: 8px; text-align: left; background-color: #f2f2f2;\">Total</th>"
            +
            "</tr>" +
            "</thead>" +
            "<tbody>";
      double total = 0.0;
      for (OrderProductDTO product : orderDTO.getProducts()) {
         emailContent += "<tr>" +
               "<td style=\"border: 1px solid #ddd; padding: 8px;\">" + product.getProduct().getTitle() + "</td>" +
               "<td style=\"border: 1px solid #ddd; padding: 8px;\">" + product.getQuantity() + "</td>" +
               "<td style=\"border: 1px solid #ddd; padding: 8px;\"> $" + product.getProduct().getPrice() + "</td>";
         double productTotal = product.getProduct().getPrice() * product.getQuantity();
         total += productTotal; // Suma el total de cada producto al total general
         emailContent += "<td style=\"border: 1px solid #ddd; padding: 8px;\"> $" + productTotal + "</td>" +
               "</tr>";
      }
      // Agregar fila para el total
      emailContent += "<tr>" +
            "<td colspan=\"3\" style=\"border: 1px solid #ddd; padding: 8px; text-align: right;\">Total</td>" +
            "<td style=\"border: 1px solid #ddd; padding: 8px;\"> $" + total + "</td>" +
            "</tr>" +
            "</tbody>" +
            "</table>" +
            "<div style=text-align: center;>"
            + "<img src=\"" + orderDTO.getVoucher()
            + "\" alt=\"Imagen de la factura\" style=\"display: block; max-width: 100%; margin-top: 20px;\">" +
            "</div>" +
            "</div>";
      helper.setText(emailContent, true);
      javaMailSender.send(message);
   }

   /**
    * Método que retorna el estado de la orden en español.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param status
    * @return
    */
   private String getOrderStatus(String status) {
      switch (status) {
         case "PENDING":
            return "PENDIENTE";
         case "COMPLETE":
            return "COMPLETADO";
         case "INITIAL":
            return "INICIAL";
         default:
            return "DESCONOCIDO";
      }
   }
}
