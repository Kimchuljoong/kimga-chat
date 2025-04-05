package com.kimga.kimga_chat.dtos;


import com.kimga.kimga_chat.entities.Member;

public record MemberDto(
        Long id,
        String nickName,
        String password,
        String confirmPassword,
        String role
){

    public static MemberDto from(Member member) {
        return new MemberDto(
                member.getId(),
                member.getNickName(),
                null,
                null,
                member.getRole()
        );
    }

    public static Member to(MemberDto memberDto) {
        return Member.builder()
                .id(memberDto.id())
                .nickName(memberDto.nickName())
                .role(memberDto.role())
                .build();
    }
}
