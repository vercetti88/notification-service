package com.example.notificationservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@AllArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such email")
public class NoSuchEmailException extends RuntimeException {
    private String email;
}
