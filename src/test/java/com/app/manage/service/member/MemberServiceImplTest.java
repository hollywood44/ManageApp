package com.app.manage.service.member;

import com.app.manage.dto.MemberDto;
import com.app.manage.entity.Member;
import com.app.manage.entity.utility.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Test
    @Rollback(value = false)
    void signUp() {
        MemberDto dto = MemberDto.builder()
                .memberId("member1")
                .name("member1")
                .auth(Auth.Member)
                .password("1234")
                .phoneNumber("010-0000-0001")
                .build();

        System.out.println(memberService.signUp(dto));
    }

    @Test
    void getAllMember() {
        List<Member> list = memberService.getAllMember();
        for (Member member : list) {
            System.out.println("id : " + member.getMemberId() + ", password : " + member.getPassword());
        }
    }
}