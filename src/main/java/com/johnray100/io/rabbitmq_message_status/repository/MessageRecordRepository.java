package com.johnray100.io.rabbitmq_message_status.repository;

import com.johnray100.io.rabbitmq_message_status.model.MessageRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRecordRepository extends JpaRepository<MessageRecord, Long> {
    Optional<Object> findByContent(String content);
}
