package uz.pdp.notificationservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.notificationservice.dto.SendEMailDTO;
import uz.pdp.notificationservice.service.MailService;
import uz.pdp.notificationservice.dto.ApiResponse;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MailSendingController {
    private final MailService mailService;
    public static final String SEND_HTML_TEMPLATE_URL = "/send-html-template";
    public static final String SEND_SIMPLE_EMAIL_URL = "/send-simple-email";

    @PostMapping(SEND_HTML_TEMPLATE_URL)
    public ResponseEntity<ApiResponse> sendHtmlTemplate(
            @RequestBody @Valid SendEMailDTO sendEMailDTO
    ) {
        return mailService.sendHtmlMail(sendEMailDTO);
    }

    @PostMapping(SEND_SIMPLE_EMAIL_URL)
    public ResponseEntity<ApiResponse> sendSimpleEmail(
            @RequestBody @Valid SendEMailDTO sendEMailDTO
    ) {
        return mailService.sendMail(sendEMailDTO);
    }
}
