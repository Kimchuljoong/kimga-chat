package com.kimga.kimga_chat.repositories;

import com.kimga.kimga_chat.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByNickName(String nickName);
}
