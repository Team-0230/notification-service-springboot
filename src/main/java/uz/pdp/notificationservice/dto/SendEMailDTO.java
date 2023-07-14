package uz.pdp.notificationservice.dto;

import jakarta.validation.constraints.NotBlank;

public record SendEMailDTO(
        @NotBlank(message = "Email must not be empty")
        String email,
        @NotBlank(message = "Subject must not be empty")
        String subject,
        @NotBlank(message = "Text must not be empty")
        String text
) {
}
