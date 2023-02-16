package com.example.notificationservice.repository;

import com.example.notificationservice.entity.CodeVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeVerificationRepository extends JpaRepository<CodeVerificationEntity, Long> {

    //TODO создать метод который возвращает CodeVerificationEntity по email с помощью ключевых слов JPA
    public CodeVerificationEntity findByEmail(String email);

    //TODO создать void метод который удаляет запись по email с помощью ключевых слов JPA
    public void deleteByEmail(String email);
}
