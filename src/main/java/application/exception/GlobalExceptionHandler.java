package application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Translates business-rule violations into a consistent JSON error
 * response instead of a raw stack trace.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessRuleException(BusinessRuleException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Business rule violation");
        body.put("message", exception.getMessage());
        return ResponseEntity.badRequest().body(body);
    }
}
