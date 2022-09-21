package com.example.myboard.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.management.relation.Role;
import javax.persistence.*;

@Table(name = "MEMBER")
@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id; //primary Key(회원 번호)

    @Column(nullable = false, length = 30, unique = true)
    private String email; //이메일(아이디)

    private String password; //비밀번호

    @Column(nullable = false, length = 30)
    private String name; //이름

    @Column(nullable = false, length = 30)
    private String nickName; //닉네임

    @Column(nullable = false, length = 30)
    private Integer age; //나이

    @Enumerated(EnumType.STRING)
    private Role role; //권한(USER,ADMIN)


    //==회원 정보 수정==/
    //비밀번호
    public void updatePassword(PasswordEncoder passwordEncoder, String password){
        this.password = passwordEncoder.encode(password);
    }

    //이름
    public void updateName(String name){
        this.name = name;
    }

    //닉네임
    public void updateNickName(String nickName){
        this.nickName = nickName;
    }

    //나이
    public void updateNickName(Integer age){
        this.age = age;
    }

    //==패스워드 암호화==//
    public void encodingPassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }
}