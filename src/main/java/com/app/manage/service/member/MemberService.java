package com.app.manage.service.member;

import com.app.manage.dto.MemberDto;
import com.app.manage.entity.Member;
import com.app.manage.entity.utility.Auth;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    // 회원가입 시 사용할 dto to entity
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

    default MemberDto entityToDto(Member entity) {
        MemberDto dto = MemberDto.builder()
                .memberId(entity.getMemberId())
                .phoneNumber(entity.getPhoneNumber())
                .name(entity.getName())
                .auth(entity.getAuth())
                .build();
        return dto;
    }

    // 회원가입
    public String signUp(MemberDto memberDto);

    // 모든 회원 정보 불러오기
    public List<MemberDto> getAllMember();

    // 로그인된 회원 자신 정보 가져오기
    public MemberDto getMyAccountInfo(Member presentMember);

    // 회원 정보 가져오기
    public MemberDto getAccountInfo(String memberId,Auth auth);

    // 로그인된 회원 자신 정보 수정하기
    public MemberDto modifyMyAccountInfo(String memberId,MemberDto modifyInfo);

    // 회원 정보 수정
    public MemberDto modifyMemberInfo(String memberId, MemberDto modifyInfo);

    // 회원 등급 수정
    public MemberDto modifyMemberAuth(String memberId,String auth);

    // 회원 탈퇴
    public String deleteMember(String memberId,Member member);
}
