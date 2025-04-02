package com.kimga.kimga_chat.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "message_id")
    private Long id;

    private String text;

    @JoinColumn(name = "member_id")
    @ManyToOne
    Member member;

    @JoinColumn(name = "chatroom_id")
    @ManyToOne
    Chatroom chatroom;
}
