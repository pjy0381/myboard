package com.example.myboard.domain.member.controller;

import com.example.myboard.domain.member.Member;
import com.example.myboard.domain.member.dto.MemberSignUpDto;
import com.example.myboard.domain.member.repository.MemberRepository;
import com.example.myboard.domain.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {


    @Autowired MockMvc mockMvc;
    @Autowired EntityManager em;
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    PasswordEncoder passwordEncoder;

    private static String SIGN_UP_URL = "/signUp";

    private String username = "username";
    private String password = "password1234@";
    private String name = "shinD";
    private String nickName = "shinD cute";
    private Integer age = 22;


    private void clear() {
        em.flush();
        em.clear();
    }

    private void signUp(String signUpData) throws Exception {
        mockMvc.perform(
                        post(SIGN_UP_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(signUpData))
                .andExpect(status().isOk());
    }


    @Value("${jwt.access.header}")
    private String accessHeader;

    private static final String BEARER = "Bearer ";

    private String getAccessToken() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);


        MvcResult result = mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(map)))
                .andExpect(status().isOk()).andReturn();

        return result.getResponse().getHeader(accessHeader);
    }


    @Test
    public void 회원가입_성공() throws Exception {
        //given
        String signUpData = objectMapper.writeValueAsString(new MemberSignUpDto(username, password, name, nickName, age));

        //when
        signUp(signUpData);

        //then
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new Exception("회원이 없습니다"));
        assertThat(member.getName()).isEqualTo(name);
        assertThat(memberRepository.findAll().size()).isEqualTo(1);
    }
}