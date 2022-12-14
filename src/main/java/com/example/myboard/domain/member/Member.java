package com.example.myboard.domain.member;

import com.example.myboard.domain.BaseTimeEntity;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Table(name = "MEMBER")
@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name_id")
    private Long id; //primary Key(회원 번호)

    @Column(nullable = false, length = 30, unique = true)
    private String username; //이메일(아이디)

    private String password; //비밀번호

    @Column(nullable = false, length = 30)
    private String name; //이름

    @Column(nullable = false, length = 30)
    private String nickName; //닉네임

    @Column(nullable = false, length = 30)
    private Integer age; //나이

    @Enumerated(EnumType.STRING)
    private Role role; //권한(USER,ADMIN)

    @Column(length = 1000)
    private String refreshToken;

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
    public void updateAge(Integer age){
        this.age = age;
    }

    //jwt
    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

    //jwt 제거
    public void destroyRefreshToken(){
        this.refreshToken = null;
    }

    //==패스워드 암호화==//
    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

    //== 권한 부여 ==//
    public void addUserAuthority() {
        this.role = Role.USER;
    }

}
