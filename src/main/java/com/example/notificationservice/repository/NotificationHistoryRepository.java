package com.example.notificationservice.repository;

import com.example.notificationservice.entity.NotificationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationHistoryRepository extends JpaRepository<NotificationHistoryEntity, Long> {
}
