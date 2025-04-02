package com.kimga.kimga_chat.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    @Transient
    Boolean hasNewMessage;

    public void setHasNewMessage(Boolean hasNewMessage) {
        this.hasNewMessage = hasNewMessage;
    }

    public MemberChatroomMapping addMember(Member member) {
        if (this.getMemberChatroomMappings() == null) {
            this.memberChatroomMappings = new HashSet<>();
        }

        MemberChatroomMapping memberChatroomMapping = MemberChatroomMapping.builder()
                .member(member)
                .chatroom(this)
                .build();

        this.memberChatroomMappings.add(memberChatroomMapping);

        return memberChatroomMapping;
    }
}
