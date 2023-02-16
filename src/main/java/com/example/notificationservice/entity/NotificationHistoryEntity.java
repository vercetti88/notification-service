package com.example.notificationservice.entity;

import com.example.notificationservice.model.NotificationStatus;
import com.example.notificationservice.model.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notification_history", schema = "notification")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "verification_code")
    private String verificationCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status")
    private NotificationStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type")
    private NotificationType type;

    @Column(name = "message")
    private String message;

}
