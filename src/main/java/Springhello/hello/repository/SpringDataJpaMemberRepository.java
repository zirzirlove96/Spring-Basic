package Springhello.hello.repository;

import Springhello.hello.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//springDataJpa는 구현할 필요없이 spring Data JPa이 자동적으로 구현해서 만들어 준다.
//그러면 우리는 구현할 필요없이 사용해도 된다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> , MemberRepository{

    @Override
    Optional<Member> findByName(String name);

}
