package Springhello.hello.service;

import Springhello.hello.domain.Member;
import Springhello.hello.repository.MemberRepository;
import Springhello.hello.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
//@SpringBootTest의 역할을 test코드에서 test를 진행함과 동시에 container에 빈으로 등록시켜준다.
@Transactional //Transactional은 test코드에서 자주 쓰이는데
//그 이유는 스프링이 test코드를 돌리면서 데이터를 commit한 뒤 테스트가 끝나면 rollback을 해줘서
//이 전의 데이터가 사라지게 하여 데이터를 직접 안지워도 반복적으로 test를 할 수 있다.
public class MemberServiceIntegrationTest {

    //생성자에 @Autowired대신 필드에 하는 이유 - TEst코드이므로 꼭 생성자에 할 필요는 없다.
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;


    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("hello");
        //When
        Long saveId = memberService.join(member);

        Member findMember = memberRepository.findById(saveId).get();
        Assertions.assertEquals(member.getName(), findMember.getName());
    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //When
        memberService.join(member1);
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }


}
