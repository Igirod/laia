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
    public void sendEmail(MailDTO mailDTO) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true); // true indica que el mensaje admite contenido HTML
        helper.setTo(mailDTO.getTo());
        helper.setSubject(mailDTO.getSubject());
        helper.setText(
            """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Acceso a LAIA</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f4f4;
                            margin: 0;
                            padding: 0;
                        }
                        .container {
                            max-width: 600px;
                            margin: 0 auto;
                            padding: 20px;
                            background-color: #fff;
                            border-radius: 10px;
                            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                        }
                        h2 {
                            color: #333;
                        }
                        p {
                            color: #666;
                        }
                        strong {
                            font-weight: bold;
                            color: #4CAF50;
                        }
                        a {
                            text-decoration: none;
                            background-color: #4CAF50;
                            color: white;
                            padding: 10px 20px;
                            border-radius: 5px;
                            display: inline-block;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h2>Acceso a tu LAIA</h2>
                        <p>El acceso a tu LAIA está listo.</p>
                        <p>Aquí te dejamos tu contraseña para futuros ingresos en la misma:</p>
                        <p class="strong">5499ab-lt5dw2gb-24d3ba</p>
                        <p><em>LAIA entrenada por Daviel</em></p>
                        <p><strong>Coordinador de tu Experiencia con LAIA</strong></p>
                    </div>
                </body>
                </html>""", true); // true indica que el texto es HTML
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
