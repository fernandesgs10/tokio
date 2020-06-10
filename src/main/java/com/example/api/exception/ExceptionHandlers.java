package com.example.api.exception;

import com.example.api.message.ResponseMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.*;

@Slf4j
@ControllerAdvice
@RestController
public class ExceptionHandlers {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ResponseMessage> exceptionError(Exception ex, WebRequest request) {
        ResponseMessage responseMensage = ResponseMessage.builder()
                .messageError("Ocorreu um erro inesperado...")
                .error(Boolean.TRUE)
                .date(new Date())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.OK).body(responseMensage);
    }

    @ExceptionHandler(ExceptionDomain.class)
    public ResponseEntity<ResponseMessage> exceptionDomainError(ExceptionDomain exception, HttpServletRequest request) {
        Throwable throwable = exception;
        ResponseMessage responseMensage = ResponseMessage.builder()
                .messageError(throwable.getMessage())
                .date(new Date())
                .error(Boolean.TRUE)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();

        log.error(throwable.getMessage(), throwable);
        return ResponseEntity.status(HttpStatus.OK).body(responseMensage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseMessage> exceptionContraintError(ConstraintViolationException exception, HttpServletRequest request) {
        Throwable throwable = exception;
        ResponseMessage responseMensage = ResponseMessage.builder()
                .messageError(throwable.getMessage())
                .date(new Date())
                .error(Boolean.TRUE)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();

        log.error(throwable.getMessage(), throwable);
        return ResponseEntity.status(HttpStatus.OK).body(responseMensage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage> exceptionMethodArgumentError(MethodArgumentNotValidException exception, HttpServletRequest request) {

        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        Error error = processFieldErrors(fieldErrors);

        List<String> listMessageErro = new ArrayList<>();
        error.getFieldErrors().forEach(item -> {
            listMessageErro.add(item.getDefaultMessage());
        });

        ResponseMessage responseMensage = ResponseMessage.builder()
                .messageError(listMessageErro.toString())
                .date(new Date())
                .error(Boolean.TRUE)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();

        log.error(listMessageErro.toString());
        return ResponseEntity.status(HttpStatus.OK).body(responseMensage);
    }

    private Error processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        Error error = new Error(HttpStatus.BAD_REQUEST.value(), "validation error");
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
            error.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }

    @Getter
    @Setter
    static class Error {
        private final int status;
        private final String message;
        private List<FieldError> fieldErrors = new ArrayList<>();

        Error(int status, String message) {
            this.status = status;
            this.message = message;
        }
            public void addFieldError(String path, String message) {
                FieldError error = new FieldError(path, path, message);
                fieldErrors.add(error);
            }
        }

    }
