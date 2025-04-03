package com.kimga.kimga_chat.controllers;

import com.kimga.kimga_chat.dtos.ChatMessage;
import com.kimga.kimga_chat.dtos.ChatroomDto;
import com.kimga.kimga_chat.entities.Chatroom;
import com.kimga.kimga_chat.entities.Message;
import com.kimga.kimga_chat.services.ChatService;
import com.kimga.kimga_chat.services.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/chats")
@RestController
public class ChatController {

    public final ChatService chatService;

    @PostMapping
    public ChatroomDto createChatroom(
            @AuthenticationPrincipal CustomOAuth2User user,
            @RequestParam String title
    ) {
        Chatroom chatroom = chatService.createChatroom(user.getMember(), title);
        return ChatroomDto.from(chatroom);
    }

    @PostMapping("/{chatroomId}")
    public Boolean joinChatroom(
            @AuthenticationPrincipal CustomOAuth2User user,
            @PathVariable Long chatroomId,
            @RequestParam(required = false) Long currentChatroomId
    ) {
        return chatService.joinChatroom(user.getMember(), chatroomId, currentChatroomId);
    }

    @DeleteMapping("/{chatroomId}")
    public Boolean leaveChatroom(
            @AuthenticationPrincipal CustomOAuth2User user,
            @PathVariable Long chatroomId
    ) {
        return chatService.leaveChatroom(user.getMember(), chatroomId);
    }

    @GetMapping
    public List<ChatroomDto> getChatrooms(
            @AuthenticationPrincipal CustomOAuth2User user
    ) {
        List<Chatroom> chatrooms = chatService.getChatroomList(user.getMember());
        return chatrooms.stream()
                .map(ChatroomDto::from)
                .toList();
    }

    @GetMapping("/{chatroomId}/messages")
    public List<ChatMessage> getMessageList(@PathVariable Long chatroomId) {
        List<Message> messageList = chatService.getMessageList(chatroomId);

        return messageList.stream()
                .map(message -> new ChatMessage(message.getMember().getNickName(), message.getText()))
                .toList();
    }

}
