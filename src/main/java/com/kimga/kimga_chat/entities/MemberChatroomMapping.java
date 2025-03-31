package com.kimga.kimga_chat.entities;

import jakarta.persistence.*;

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
}
