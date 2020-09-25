package Springhello.hello.repository;

import Springhello.hello.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);//Optional은 null값이 있을 경우를 대비하여 사용
    Optional<Member> findByName(String name);
    List<Member> findAll();

    //void clearStore();
}
