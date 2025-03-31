package com.kimga.kimga_chat.controllers;

import com.kimga.kimga_chat.dtos.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.security.Principal;
import java.util.Map;

@Slf4j
@Configuration
public class StompChatController {

    @MessageMapping("/chats")
    @SendTo("/sub/chats")
    public ChatMessage handleMessage(
            @AuthenticationPrincipal Principal principal,
            @Payload Map<String, String> payload) {
        log.info("{} received {}", principal.getName(), payload);

        return new ChatMessage(principal.getName(), payload.get("message"));
    }
}
