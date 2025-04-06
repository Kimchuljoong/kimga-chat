package com.kimga.kimga_chat.controllers;

import com.kimga.kimga_chat.dtos.ChatroomDto;
import com.kimga.kimga_chat.dtos.MemberDto;
import com.kimga.kimga_chat.services.ConsultantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/consultants")
@Controller
public class ConsultantController {

    private final ConsultantService consultantService;

    @ResponseBody
    @PostMapping
    public MemberDto saveMember(@RequestBody MemberDto memberDto) {
        return consultantService.saveMember(memberDto);
    }

    @GetMapping
    public String index() {
        return "consultants/index.html";
    }

    @ResponseBody
    @GetMapping("chats")
    public List<ChatroomDto> getAllChatrooms() {
        return consultantService.getAllChatrooms();
    }

}
