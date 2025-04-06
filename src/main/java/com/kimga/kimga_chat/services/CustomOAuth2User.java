package com.kimga.kimga_chat.services;

import com.kimga.kimga_chat.entities.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    Member member;
    Map<String, Object> attributesMap;

    public Member getMember() {
        return this.member;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributesMap;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> this.member.getNickName());
    }

    @Override
    public String getName() {
        return this.member.getNickName();
    }
}
