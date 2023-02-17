package com.example.notificationservice.repository;

import com.example.notificationservice.entity.CodeVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeVerificationRepository extends JpaRepository<CodeVerificationEntity, Long> {

    public CodeVerificationEntity findByEmail(String email);

    public void deleteByEmail(String email);
}
