package com.example.notificationservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "code_verification", schema = "notification")
@NoArgsConstructor
public class CodeVerificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "verification_code")
    private String verificationCode;

    public CodeVerificationEntity(String email, String verificationCode) {
        this.email = email;
        this.verificationCode = verificationCode;
    }
}
