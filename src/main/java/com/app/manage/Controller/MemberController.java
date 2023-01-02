package com.app.manage.Controller;

import com.app.manage.dto.MemberDto;
import com.app.manage.entity.Member;
import com.app.manage.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/memberList")
    public List<MemberDto> getMemberList() {
        return memberService.getAllMember();
    }

    @GetMapping("/memberInfo/{memberId}")
    public MemberDto getMemberInfo(@PathVariable(name = "memberId") String memberId,@AuthenticationPrincipal Member member) {
        return memberService.getAccountInfo(memberId,member.getAuth());
    }

    @PutMapping("/modifyMyInfo/{memberId}")
    public MemberDto modifyMyInfo(MemberDto memberDto, @PathVariable(name = "memberId")String memberId) {
       return memberService.modifyMyAccountInfo(memberId,memberDto);
    }

    @PutMapping("/modifyMemberInfo/{memberId}")
    public MemberDto modifyMemberInfo(MemberDto memberDto, @PathVariable(name = "memberId") String memberId) {
        return memberService.modifyMemberInfo(memberId, memberDto);
    }

    @PatchMapping("/modifyAuth/{memberId}")
    public MemberDto modifyAuth(@RequestParam(name = "auth") String auth,@PathVariable(name = "memberId")String memberId) {
        return memberService.modifyMemberAuth(memberId, auth);
    }

    @DeleteMapping("/delete/{memberId}")
    public String deleteMember(@PathVariable(name = "memberId")String memberId,@AuthenticationPrincipal Member member) {
        return memberService.deleteMember(memberId,member);
    }

}
