package uz.pdp.notificationservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import uz.pdp.notificationservice.dto.ApiResponse;
import uz.pdp.notificationservice.dto.SendEMailDTO;
import uz.pdp.notificationservice.exception.SendHtmlTemplateFailedException;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public ResponseEntity<ApiResponse> sendHtmlMail(SendEMailDTO sendEMailDTO) {
        MimeMessage mimeMessage;
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessage = mailSender.createMimeMessage();
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            // Setting up necessary details
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(sendEMailDTO.email());
            mimeMessageHelper.setSubject(sendEMailDTO.subject());
            mimeMessageHelper.setText(sendEMailDTO.text(), true);

            // Sending the mail
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            String errorMessage = """
                    Failed to send html template to: %s
                    Error message: %s
                    """.formatted(sendEMailDTO.email(), e.getMessage());
            throw new SendHtmlTemplateFailedException(errorMessage);
        }
        return ResponseEntity
                .ok(new ApiResponse(HttpStatus.OK, true, "Email sent successfully"));
    }

    public ResponseEntity<ApiResponse> sendMail(SendEMailDTO sendEMailDTO) {
        SimpleMailMessage mailMessage
                = new SimpleMailMessage();

        // Setting up necessary details
        mailMessage.setFrom(sender);
        mailMessage.setTo(sendEMailDTO.email());
        mailMessage.setSubject(sendEMailDTO.subject());
        mailMessage.setText(sendEMailDTO.text());

        // Sending the mail
        mailSender.send(mailMessage);
        return ResponseEntity
                .ok(new ApiResponse(HttpStatus.OK, true, "Email sent successfully"));
    }
//
//    private void sendHtmlTemplateEmail(String to,
//                                       String subject,
//                                       String templateName,
//                                       Map<String, Object> templateModel) {
//        MimeMessage mimeMessage;
//        MimeMessageHelper mimeMessageHelper;
//        Context thymeleafContext;
//        String htmlBody;
//
//        try {
//            mimeMessage = mailSender.createMimeMessage();
//            mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
//
//            // generate html using thymeleaf
//            thymeleafContext = new Context();
//            thymeleafContext.setVariables(templateModel);
//            htmlBody = thymeleafTemplateEngine.process(templateName, thymeleafContext);
//
//            // Setting up necessary details
//            mimeMessageHelper.setFrom(sender);
//            mimeMessageHelper.setTo(to);
//            mimeMessageHelper.setSubject(subject);
//            mimeMessageHelper.setText(htmlBody, true);
//
//            // Sending the mail
//            mailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            String errorMessage = """
//                    Failed to send html template to: %s
//                    Template name: %s
//                    Template model: %s
//                    Error message: %s
//                    """.formatted(to, templateName, templateModel, e.getMessage());
//            throw new SendHtmlTemplateFailedException(errorMessage);
//        }
//    }
}