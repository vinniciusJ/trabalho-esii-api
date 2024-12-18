package com.project.esii.project_esii.emailsender.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender javaMailSender;

    public boolean sendRegistrationVerificationEmail(String url, String dstEmail) {
        String link = "http://localhost:8080" + url;

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
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(dstEmail);
            helper.setSubject("Verifique seu cadastro em Eventos+!");
            helper.setText(emailBody, true);

            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
