package com.kimga.kimga_chat.services;

import com.kimga.kimga_chat.dtos.ChatroomDto;
import com.kimga.kimga_chat.dtos.MemberDto;
import com.kimga.kimga_chat.entities.Chatroom;
import com.kimga.kimga_chat.entities.Member;
import com.kimga.kimga_chat.repositories.ChatroomRepository;
import com.kimga.kimga_chat.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConsultantService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final ChatroomRepository chatroomRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByNickName(username).get();

        if (Role.fromCode(member.getRole()) != Role.CONSULTANT) {
            throw new AccessDeniedException("상담사가 아닙니다");
        }

        return new CustomUserDetails(member, null);
    }

    public MemberDto saveMember(MemberDto memberDto) {
        Member member = MemberDto.to(memberDto);
        member.updatePassword(memberDto.password(), memberDto.confirmPassword(), passwordEncoder);

        return MemberDto.from(memberRepository.save(member));
    }

    public List<ChatroomDto> getAllChatrooms() {
        List<Chatroom> chatroomList = chatroomRepository.findAll();
        return chatroomList.stream().map(ChatroomDto::from).toList();
    }

    public Page<ChatroomDto> getChatroomPage(Pageable pageable) {
        Page<Chatroom> chatroomPage = chatroomRepository.findAll(pageable);
        return chatroomPage.map(ChatroomDto::from);

    }
}
