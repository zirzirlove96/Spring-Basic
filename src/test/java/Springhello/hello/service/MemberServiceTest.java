package Springhello.hello.service;

import Springhello.hello.domain.Member;
import Springhello.hello.repository.MemberRepository;
import Springhello.hello.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //memberService에서 MemberService를 이용했기 때문에
    //인스턴스를 생성하지 않는다.
    private MemoryMemberRepository memberRepository;//clearStore를 하기 위해
    private MemberService memberService;


    @BeforeEach//동작하기 전에 수행
    public void beforeEach() {
        //MemoryMemberRepository에서 수행한 static HashMap이 고정되어 있으므로 각각의 repository,service의 인스턴스가 필요없고 공유하면
        //되므로 beforeEach를 수행하여 compile전에 수행되게 한다.
        //이런것을 DI(Dependency Injection이라고 한다.)
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach//@AfterEach를 이용하여 각 메서드가 수행되고 나서 repository에 저장된 값이 지워진다.
    public void afterEach() {
        memberRepository.clearStore();
    }
    
    @Test
    void 회원가입() {

        //Given
        Member member = new Member();
        member.setName("spring");

        //when
        Long savedId = memberService.join(member);

        //Then
        Member findMember = memberRepository.findById(savedId).get();
        Assertions.assertEquals(member.getName(),findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {

        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                ()->memberService.join(member2));
        //member1과 member2가 같은 경우 예외 발생

        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}