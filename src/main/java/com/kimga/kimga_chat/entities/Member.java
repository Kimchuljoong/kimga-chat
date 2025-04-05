package com.kimga.kimga_chat.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickName;
    private String role;

    private String password;

    public void updatePassword(String password, String confirmPassword, PasswordEncoder passwordEncoder) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }

        this.password = passwordEncoder.encode(password);
    }
}
