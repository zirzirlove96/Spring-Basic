package Springhello.hello.controller;


import Springhello.hello.domain.Member;
import Springhello.hello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //@controller를 등록함으로써 spring container에서 controller를 빈으로 등록해 준다.
public class MemberController {

    //private final MemeberService memberService = new MemberService();
    //인스턴스를 생성하지 않는 이유는 다른 controller에서도 사용하기 때문에 하나만 생성하여 공용으로 사용하기 위하여

    private final MemberService memberService;

    //@Autowired를 통해서 MemberControler와 MemeberService를
    //인스턴스 생성을 하지 않고 spring container에서 bean으로 자동 등록해주고 연결해 주기 때문에 사용한다.
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService=memberService;
        System.out.println(memberService.getClass());//프록시 확인

    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){

        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        //입력된 모든 멤버 불러오기.
        List<Member> list = memberService.findMembers();
        model.addAttribute("members",list);
        return "members/memberList";
    }
}
