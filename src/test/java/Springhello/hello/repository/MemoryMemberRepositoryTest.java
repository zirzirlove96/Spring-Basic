package Springhello.hello.repository;

import Springhello.hello.domain.Member;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    @Test //repository의 save를 테스트해 본다.
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        //임의로 저장

        //저장된 값을 가져온다.
        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);//member의 값과 result값이 같은지 확인

    }

    @Test//이름을 찾아와 Assertion을 이용해 test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member m1 = repository.findByName(member1.getName()).get();

        Assertions.assertThat(m1).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> list = repository.findAll();

        Assertions.assertThat(list.size()).isEqualTo(2);
    }

    @AfterEach//@AfterEach를 이용하여 각 메서드가 수행되고 나서 repository에 저장된 값이 지워진다.
    public void afterEach() {
        repository.clearStore();
    }
}
