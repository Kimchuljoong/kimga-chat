package com.kimga.kimga_chat.repositories;

import com.kimga.kimga_chat.entities.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
}
