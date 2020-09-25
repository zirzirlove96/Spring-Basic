package Springhello.hello.repository;

import Springhello.hello.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static HashMap<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);//id값을 저장
        store.put(member.getId(), member);//DB대신 HashMap에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));//id값을 가져온다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny();//map에 저장되어 있는 name과 member에 저장된 name이 같은지 확인하고
        //같은 것을 찾아낸다->findAny
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    //Map의 정보를 지우기 위해
    public void clearStore() {
        store.clear();
    }
}
