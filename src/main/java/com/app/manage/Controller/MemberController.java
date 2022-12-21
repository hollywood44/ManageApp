package com.app.manage.Controller;

import com.app.manage.dto.MemberDto;
import com.app.manage.entity.Member;
import com.app.manage.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/main")
    public String homePage() {
        return "MainPage";
    }

    @PostMapping("/signUp")
    public String singUp(MemberDto dto) {
        memberService.signUp(dto);
        return dto.getMemberId();
    }

    @GetMapping("/getMyInfo")
    public MemberDto getMyInfo(@AuthenticationPrincipal Member member) {
        return memberService.getMyAccountInfo(member);
    }

    @GetMapping("/memberInfo/{memberId}")
    public MemberDto getMemberInfo(@PathVariable(name = "memberId") String memberId,@AuthenticationPrincipal Member member) {
        return memberService.getAccountInfo(memberId,member.getAuth());
    }

    @PutMapping("/modifyMyInfo/{memberId}")
    public MemberDto modifyMyInfo(MemberDto memberDto, @PathVariable(name = "memberId")String memberId) {
       return memberService.modifyMyAccountInfo(memberId,memberDto);
    }
}
