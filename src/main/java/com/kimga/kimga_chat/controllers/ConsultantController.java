package com.kimga.kimga_chat.controllers;

import com.kimga.kimga_chat.dtos.MemberDto;
import com.kimga.kimga_chat.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/consultants")
@Controller
public class ConsultantController {

    private final CustomUserDetailsService customUserDetailsService;

    @ResponseBody
    @PostMapping
    public MemberDto saveMember(@RequestBody MemberDto memberDto) {
        return customUserDetailsService.saveMember(memberDto);
    }

    @GetMapping
    public String index() {{
        return "consultants/index.html";
    }

}
