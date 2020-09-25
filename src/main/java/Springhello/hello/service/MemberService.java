package Springhello.hello.service;

import Springhello.hello.domain.Member;
import Springhello.hello.repository.MemberRepository;
import Springhello.hello.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

//실질적인 비지니스 로직이다.
//@Service //순수한 자바 클래스를 @Service를 이용하여 container가 bean으로 등록하여 관리하게끔 한다.
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    //회원가입
    public Long join(Member member){

        validation(member);
        memberRepository.save(member);
        return member.getId();
    }

    //회원 이름이 중복되는지 확인하는 함수.
    private void validation(Member member) {

       memberRepository.findByName(member.getName())
        .ifPresent(m->
        {//optional이기에 ifPresent를 사용할 수 있다.
            //repository에 존재하는지에 대해 확인할 수 있다.
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 id로 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
