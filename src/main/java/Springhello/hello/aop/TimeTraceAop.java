package Springhello.hello.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //aop를 사용하기 위한 어노테이션
@Component //bean으로 등록
public class TimeTraceAop {

    @Around("execution(* Springhello.hello..*(..))")//이 공통관심사항인 시간 찍는 것을 어느 객체에서 사용할 것인가를
    //@Around를 이용하여 사용할 수 있다. 즉 Springhello.hello 밑의 폴더의 모든 파일들에 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("START : "+joinPoint.toString());
        try {
           return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("END : "+joinPoint.toString()+" "+timeMs+" ms");
        }



    }
}
