package com.kimga.kimga_chat.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

@Slf4j
@Configuration
public class StompChatController {

    @MessageMapping("/chats")
    @SendTo("/sub/chats")
    public String handleMessage(@Payload String message) {
        log.info("{} received", message);

        return message;
    }
}
