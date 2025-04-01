package com.kimga.kimga_chat.controllers;

import com.kimga.kimga_chat.entities.Chatroom;
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
    public Chatroom createChatroom(
            @AuthenticationPrincipal CustomOAuth2User user,
            @RequestParam String title
    ) {
        return chatService.createChatroom(user.getMember(), title);
    }

    @PostMapping("/{chatroomId}")
    public Boolean joinChatroom(
            @AuthenticationPrincipal CustomOAuth2User user,
            @PathVariable Long chatroomId
    ) {
        return chatService.joinChatroom(user.getMember(), chatroomId);
    }

    @DeleteMapping
    public Boolean leaveChatroom(
            @AuthenticationPrincipal CustomOAuth2User user,
            @PathVariable Long chatroomId
    ) {
        return chatService.leaveChatroom(user.getMember(), chatroomId);
    }

    @GetMapping
    public List<Chatroom> getChatrooms(
            @AuthenticationPrincipal CustomOAuth2User user
    ) {
        return chatService.getChatroomList(user.getMember());
    }
}
