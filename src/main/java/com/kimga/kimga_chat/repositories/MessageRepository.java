package com.kimga.kimga_chat.repositories;

import com.kimga.kimga_chat.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByChatroomId(Long chatroomId);

    Boolean existsByChatroomIdAndCreatedAtAfter(Long chatroomId, LocalDateTime createdAt);
}
