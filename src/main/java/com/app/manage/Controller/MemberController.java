package com.app.manage.Controller;

import com.app.manage.dto.MemberDto;
import com.app.manage.entity.Member;
import com.app.manage.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/main")
    public String homePage() {
        return "MainPage";
    }

    @GetMapping("/getMyInfo")
    public MemberDto getMyInfo(@AuthenticationPrincipal Member member) {
        return memberService.getMyAccountInfo(member);
    }

}
