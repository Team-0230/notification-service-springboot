package uz.pdp.notificationservice.exception;

import jakarta.mail.SendFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {
            SendHtmlTemplateFailedException.class,
            SendFailedException.class
    })
    public ResponseEntity<ApiExceptionResponse> handleInternalServerErrorException(RuntimeException e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(
                new ApiExceptionResponse(
                        e.getMessage(),
                        status,
                        LocalDateTime.now(),
                        null
                ),
                status
        );
    }
}
