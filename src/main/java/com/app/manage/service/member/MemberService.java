package com.app.manage.service.member;

import com.app.manage.dto.MemberDto;
import com.app.manage.entity.Member;
import com.app.manage.entity.utility.Auth;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    default Member signUpDtoToEntity(MemberDto dto) {
        Member entity = Member.builder()
                .memberId(dto.getMemberId())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .name(dto.getName())
                .auth(dto.getAuth())
                .build();
        return entity;
    }

    // 회원가입
    public String signUp(MemberDto memberDto);
    // 모든 회원 정보 불러오기
    public List<Member> getAllMember();
}
