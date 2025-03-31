package com.kimga.kimga_chat.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chatroom")
public class Chatroom {

    @Column(name = "chatroom_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "chatroom")
    private Set<MemberChatroomMapping> memberChatroomMappings = new HashSet<>();

    private LocalDateTime createdAt;
}
