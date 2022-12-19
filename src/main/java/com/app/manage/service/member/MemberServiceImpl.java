package com.app.manage.service.member;


import com.app.manage.dto.MemberDto;
import com.app.manage.entity.Member;
import com.app.manage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {


    private final MemberRepository memberRepository;

    @Override
    public String signUp(MemberDto memberDto) {
        Optional<Member> check = memberRepository.findById(memberDto.getMemberId());

        if (!check.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            memberDto.setPassword(encoder.encode(memberDto.getPassword()));
            Member member = signUpDtoToEntity(memberDto);
            memberRepository.save(member);
        } else {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }

        return memberDto.getMemberId();
    }

    @Override
    public List<Member> getAllMember() {
        List<Member> allMember = memberRepository.findAll();
        return allMember;
    }


    // memberId를 가지고 db에서 값을 찾아옴, 자동으로 password를 비교해주는 기능이 있다
    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("ID Not Found!!"));
    }


}
