package com.kimga.kimga_chat.services;

import com.kimga.kimga_chat.entities.Chatroom;
import com.kimga.kimga_chat.entities.Member;
import com.kimga.kimga_chat.entities.MemberChatroomMapping;
import com.kimga.kimga_chat.repositories.ChatroomRepository;
import com.kimga.kimga_chat.repositories.MemberChatMappingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatroomRepository chatroomRepository;
    private final MemberChatMappingRepository memberChatMappingRepository;

    public Chatroom createChatroom(Member member, String title) {
        Chatroom newChatroom = Chatroom.builder()
                .title(title)
                .createdAt(LocalDateTime.now())
                .build();

        Chatroom savedChatroom = chatroomRepository.save(newChatroom);

        MemberChatroomMapping newMemberChatroomMapping = MemberChatroomMapping.builder()
                .member(member)
                .chatroom(savedChatroom)
                .build();

        memberChatMappingRepository.save(newMemberChatroomMapping);

        return savedChatroom;
    }

    public Boolean joinChatroom(Member member, Long chatroomId) {
        if (memberChatMappingRepository.existsByMemberIdAndChatroomId(member.getId(), chatroomId)) {
            log.info("이미 참여한 채팅방입니다.");
            return false;
        }

        Chatroom chatroom = chatroomRepository.findById(chatroomId).get();

        MemberChatroomMapping memberChatroomMapping = MemberChatroomMapping.builder()
                .member(member)
                .chatroom(chatroom)
                .build();

        memberChatMappingRepository.save(memberChatroomMapping);

        return true;
    }

    public Boolean leaveChatroom(Member member, Long chatroomId) {
        if (!memberChatMappingRepository.existsByMemberIdAndChatroomId(member.getId(), chatroomId)) {
            log.info("참여하지 않은 방입니다.");
            return false;
        }

        memberChatMappingRepository.deleteByMemberIdAndChatroomId(member.getId(), chatroomId);

        return true;
    }

    public List<Chatroom> getChatroomList(Member member) {
        List<MemberChatroomMapping> memberChatroomMappingList = memberChatMappingRepository.findAllByMemberId(member.getId());

        return memberChatroomMappingList.stream()
                .map(MemberChatroomMapping::getChatroom)
                .toList();

    }
}
