package com.kimga.kimga_chat.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class MemberChatroomMapping {

    @Column(name = "member_chatroom_mapping_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne
    private Chatroom chatroom;

    LocalDateTime lastCheckedAt;
}
