package com.example.myboard.domain.member.service;

import com.example.myboard.domain.member.Member;
import com.example.myboard.domain.member.dto.MemberInfoDto;
import com.example.myboard.domain.member.dto.MemberSignUpDto;
import com.example.myboard.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void signUp(MemberSignUpDto memberSignUpDto) throws Exception {
        Member member = memberSignUpDto.toEntity();
        member.addUserAuthority();
        member.encodePassword(passwordEncoder);

        if(memberRepository.findByUsername(memberSignUpDto.getUsername()).isPresent()){
            throw new Exception("이미 존재하는 아이디입니다.");
        }

        memberRepository.save(member);
    }

    @Override
    public void updatePassword(String checkPassword, String toBePassword) throws Exception {

    }

    @Override
    public void withdraw(String checkPassword) throws Exception {

    }

    @Override
    public MemberInfoDto getInfo(Long id) throws Exception {
        return null;
    }

    @Override
    public MemberInfoDto getMyInfo() throws Exception {
        return null;
    }
}
