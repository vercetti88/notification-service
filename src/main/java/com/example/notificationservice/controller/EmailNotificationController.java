package com.example.notificationservice.controller;

import com.example.notificationservice.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/email/send")
public class EmailNotificationController {

    private final EmailNotificationService notificationService;

    @PostMapping(path = "/code")
    public void sendVerificationCode(@RequestParam(name = "email") String email) {
        notificationService.sendVerificationCode(email);

    }

    @PostMapping(path = "/message")
    public void sendMessage(@RequestParam(name = "email") String email, @RequestParam(name = "message") String message)  {
        notificationService.sendMessage(email,message);


    }

    @GetMapping(path = "/verify")
    public ResponseEntity<Boolean> verify(@RequestParam(name = "email") String email, @RequestParam(name = "code") String code) {


        return new ResponseEntity<>(notificationService.verify(email,code),HttpStatus.OK);
    }
}


