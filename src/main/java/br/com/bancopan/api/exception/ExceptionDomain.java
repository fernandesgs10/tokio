package br.com.bancopan.api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionDomain extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;

    public ExceptionDomain(String message) {
        super(message);
    }
}