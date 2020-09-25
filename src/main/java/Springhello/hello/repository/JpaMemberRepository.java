package Springhello.hello.repository;

import Springhello.hello.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    //JPA는 EntityManager을 사용하여 움직인다.
    //JPA를 사용하려면 꼭 EntityManager를 사용해야 한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em=em;
    }


    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    //PK기반이므로 간단하게 찾을 수 있지만 PK가 아닌 경우 아래와 같이 객체를 가지고 찾아야 한다.
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    public List<Member> findAll() {
        //객체지향프로그램을 사용하여 찾아 준다.
        //원래는 id,name 을 써야 하지만 m이라는 객체를 사용하여 찾아 준다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public Optional<Member> findByName(String name) {

        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public void clearStore() {

    }
}
