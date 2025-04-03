package com.kimga.kimga_chat.repositories;

import com.kimga.kimga_chat.entities.MemberChatroomMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberChatroomMappingRepository extends JpaRepository<MemberChatroomMapping, Long> {

    Boolean existsByMemberIdAndChatroomId(Long memberId, Long chatroomId);

    void deleteByMemberIdAndChatroomId(Long memberId, Long chatroomId);

    List<MemberChatroomMapping> findAllByMemberId(Long memberId);

    Optional<MemberChatroomMapping> findByMemberIdAndChatroomId(Long id, Long currentChatroomId);
}
