package com.example.notificationservice;

import com.example.notificationservice.entity.CodeVerificationEntity;
import com.example.notificationservice.entity.NotificationHistoryEntity;
import com.example.notificationservice.exceptions.NoSuchEmailException;
import com.example.notificationservice.model.NotificationStatus;
import com.example.notificationservice.model.NotificationType;
import com.example.notificationservice.repository.CodeVerificationRepository;
import com.example.notificationservice.repository.NotificationHistoryRepository;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailNotificationService {

    private final CodeVerificationRepository notificationRepository;
    private final NotificationHistoryRepository notificationHistoryRepository;
    private final JavaMailSender javaMailSender;

    //    @Value("${jwt.jwtSecret}")
    private String from = "quntxm@gmail.com";

    @Transactional
    public void sendVerificationCode(String email) {

        var verificationCode = generateCode();

        String message = "Код подтверждения: ";
        String subject = "Код подтверждения";

        notificationRepository.deleteByEmail(email);

        NotificationHistoryEntity notificationHistoryEntity = NotificationHistoryEntity.
                builder().status(NotificationStatus.SENT).
                createdAt(LocalDateTime.now()).type(NotificationType.CODE)
                .verificationCode(verificationCode).email(email).build();
        //TODO создать экземпляр NotificationHistoryEntity с необходимыми данными (status по умолчанию SENT, createdAt принимает LocalDateTime.now()), с помощью builder (не забыть @Builder в сущности)

        try {
            sendEmail(email, message + verificationCode, subject);
            notificationRepository.save(new CodeVerificationEntity(email, verificationCode));
            notificationHistoryRepository.save(notificationHistoryEntity);
        } catch (Exception e) {
            //TODO изменить статус в NotificationHistoryEntity на SEND_ERROR, так как произошла ошибка
            notificationHistoryEntity.setStatus(NotificationStatus.SEND_ERROR);
            notificationHistoryRepository.save(notificationHistoryEntity);
        }

    }

    @Transactional
    public void sendMessage(String email, String message) {

        String subject = "Уведомление";
        NotificationHistoryEntity notificationHistoryEntity = NotificationHistoryEntity.
                builder().status(NotificationStatus.SENT).
                createdAt(LocalDateTime.now()).message(message).type(NotificationType.MESSAGE)
                .email(email).build();

        try {
            sendEmail(email, message, subject);
            notificationHistoryRepository.save(notificationHistoryEntity);
        } catch (Exception e) {
            //TODO изменить статус в NotificationHistoryEntity на SEND_ERROR, так как произошла ошибка
            notificationHistoryEntity.setStatus(NotificationStatus.SEND_ERROR);
            notificationHistoryRepository.save(notificationHistoryEntity);
        }

    }

    @Transactional
    public boolean verify(String email, String code) {
        var verification = notificationRepository.findByEmail(email);

        if (verification == null) {
            throw new NoSuchEmailException(email);
        } else if (verification.getVerificationCode().equals(code)){
            notificationRepository.deleteByEmail(email);
            return true;
        }else{
            return false;
        }
    }

    public void sendEmail(String email, String message, String subject) {

        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setFrom(new InternetAddress(from, "m.46.band.user"));
            mimeMessage.setContent(message, "text/html; charset=UTF-8");
            mimeMessage.setSubject(subject, "UTF-8");
            mimeMessage.setSentDate(new Date());
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setHeader("Content-Type", "text/html; charset=UTF-8");
        };
        javaMailSender.send(preparator);
    }

    private String generateCode() {
        return RandomStringUtils.randomNumeric(6);
    }
}
