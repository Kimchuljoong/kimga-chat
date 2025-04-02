package com.kimga.kimga_chat.services;

import com.kimga.kimga_chat.entities.Chatroom;
import com.kimga.kimga_chat.entities.Member;
import com.kimga.kimga_chat.entities.MemberChatroomMapping;
import com.kimga.kimga_chat.entities.Message;
import com.kimga.kimga_chat.repositories.ChatroomRepository;
import com.kimga.kimga_chat.repositories.MemberChatMappingRepository;
import com.kimga.kimga_chat.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatroomRepository chatroomRepository;
    private final MemberChatMappingRepository memberChatMappingRepository;
    private final MessageRepository messageRepository;

    @Transactional
    public Chatroom createChatroom(Member member, String title) {
        Chatroom newChatroom = Chatroom.builder()
                .title(title)
                .createdAt(LocalDateTime.now())
                .build();

        Chatroom savedChatroom = chatroomRepository.save(newChatroom);

        MemberChatroomMapping newMemberChatroomMapping = savedChatroom.addMember(member);

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

    @Transactional
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

    public Message saveMessage(Member member, Long chatroomId, String text) {
        Chatroom chatroom = chatroomRepository.findById(chatroomId).get();

        Message message = Message.builder()
                .text(text)
                .member(member)
                .chatroom(chatroom)
                .build();

        return messageRepository.save(message);
    }

    public List<Message> getMessageList(Long chatroomId) {
        return messageRepository.findAllByChatroomId(chatroomId);
    }
}
