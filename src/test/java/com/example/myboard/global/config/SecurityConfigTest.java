package com.example.myboard.global.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SecurityConfigTest {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void 암호화된_비밀번호_매치() throws Exception {
        //given
        String password = "신동훈ShinDongHun";

        //when
        String encodePassword = passwordEncoder.encode(password);
        //then
        assertThat(passwordEncoder.matches(password, encodePassword)).isTrue();

    }
}