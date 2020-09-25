package Springhello.hello;

import Springhello.hello.repository.JdbcMemberRepository;
import Springhello.hello.repository.MemberRepository;
import Springhello.hello.repository.MemoryMemberRepository;
import Springhello.hello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.swing.*;


//스프링에 직접 등록하기
//@Configuration이 있으면 스프링에 직접 등록하기 위해 에노테이션을 한 것
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    //의존성 주입
    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource=dataSource;
    }

    //각 클래스를 빈으로 등록해주기 위해 @Bean
    //@Service, @Repository, @Autowired를 수행한 것처럼 빈으로 등록되고 연결시켜 준다.
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
       // return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }


}
