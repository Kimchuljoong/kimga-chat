package com.kimga.kimga_chat.dtos;


import com.kimga.kimga_chat.entities.Chatroom;

import java.time.LocalDateTime;

public record ChatroomDto (
        Long id,
        String title,
        Integer memberCount,
        LocalDateTime createAt ) {

    public static ChatroomDto from(Chatroom chatroom) {
        return new ChatroomDto(
                chatroom.getId(), chatroom.getTitle(),
                chatroom.getMemberChatroomMappings().size(), chatroom.getCreatedAt());
    }
}
