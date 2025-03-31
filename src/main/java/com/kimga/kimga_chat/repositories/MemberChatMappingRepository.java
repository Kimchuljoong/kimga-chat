package com.kimga.kimga_chat.repositories;

import com.kimga.kimga_chat.entities.MemberChatroomMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberChatMappingRepository extends JpaRepository<MemberChatroomMapping, Long> {
}
