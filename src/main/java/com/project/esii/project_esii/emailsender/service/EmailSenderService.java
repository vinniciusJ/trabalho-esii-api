package com.project.esii.project_esii.emailsender.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender javaMailSender;

    public String sendVerificationEmail(String dstEmail, Long userId) {
        String link = "http://localhost:8080/user/verify-email/" + userId;

        // Corpo do e-mail com HTML
        String emailBody = """
        <html>
            <body>
                <p>Olá,</p>
                <p>Clique no botão abaixo para verificar seu e-mail:</p>
                <form action="%s" method="POST">
                    <button type="submit" style="background-color: #4CAF50; color: white; border: none; padding: 10px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin: 4px 2px; cursor: pointer;">Verificar E-mail</button>
                </form>
                <p>Se você não solicitou este e-mail, pode ignorá-lo.</p>
            </body>
        </html>
        """.formatted(link);

        try {
            log.info("Enviando email!");

            // Configurando e enviando e-mail com HTML
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(dstEmail);
            helper.setSubject("Verifique seu cadastro!");
            helper.setText(emailBody, true); // 'true' habilita HTML no corpo do e-mail

            javaMailSender.send(mimeMessage);

            log.info("E-mail enviado");
            return "E-mail enviado!";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Não foi possível enviar o e-mail");
            return "Não foi possível enviar o e-mail";
        }
    }
}
