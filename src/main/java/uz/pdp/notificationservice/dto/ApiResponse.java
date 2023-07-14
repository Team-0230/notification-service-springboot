package uz.pdp.notificationservice.dto;

import org.springframework.http.HttpStatus;

public record ApiResponse(
        HttpStatus status,
        boolean success,
        String message
) {
}
