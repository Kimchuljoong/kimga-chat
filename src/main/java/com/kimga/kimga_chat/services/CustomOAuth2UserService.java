package com.kimga.kimga_chat.services;

import com.kimga.kimga_chat.entities.Member;
import com.kimga.kimga_chat.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());
        Map<String, Object> attributeMap = oAuth2User.getAttribute("kakao_account.profile");
        System.out.println(attributeMap);
//        String nickName = (String) attributeMap.get("nickname");

//        Member member = memberRepository.findByNickName(nickName)
//                .orElseGet(
//                        () -> registerMember(attributeMap)
//                );

        return new CustomOAuth2User(null, oAuth2User.getAttributes());
    }

    private Member registerMember(Map<String, Object> attributeMap) {

        System.out.println();
        Member newMember = Member.builder()
                .nickName((String) attributeMap.get("nickname"))
                .role("USER_ROLE")
                .build();

        return memberRepository.save(newMember);
    }
}
