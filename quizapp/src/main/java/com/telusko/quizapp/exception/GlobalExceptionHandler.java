package com.telusko.quizapp.exception;

import com.telusko.quizapp.dto.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidation(MethodArgumentNotValidException ex) {
        // take only the FIRST field error (your custom validator already ensures only one anyway)
        String msg = ex.getBindingResult().getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Validation failed");

        ApiResponse body = new ApiResponse(400, "failed", msg);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // (Optional) handle malformed JSON, etc., in the same format
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleBadJson(Exception ex) {
        return ResponseEntity.badRequest()
                .body(new ApiResponse(400, "failed", "Malformed JSON request"));
    }
}