package com.example.myboard.domain.member.service;

import com.example.myboard.domain.member.dto.MemberInfoDto;
import com.example.myboard.domain.member.dto.MemberSignUpDto;

public interface MemberService {
    void signUp(MemberSignUpDto memberSignUpDto) throws Exception;


    void updatePassword(String checkPassword, String toBePassword) throws Exception;

    void withdraw(String checkPassword) throws Exception;

    MemberInfoDto getInfo(Long id) throws Exception;

    MemberInfoDto getMyInfo() throws Exception;
}
