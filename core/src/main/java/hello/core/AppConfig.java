package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// @Configuration : 설정(구성) 정보
@Configuration
// 실제 구현 객체를 AppConfig가 new 연산자를 통해 생성한다.
// 구현 객체 전부를 알아야된다.
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()


    // 1.call AppConfig.memberService
    // 2.call AppConfig.memberRepository
    // 3.call AppConfig.memberRepository
    // 4.call AppConfig.orderService
    // 5.call AppConfig.memberRepository

    // "실제로는 객체 하나씩만 호출된다."


    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        // MemoryMemberRepository가 MemberServiceImpl의 생성자에 의해 할당된다.
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    // 리펙토링 함으로써 역할이 명확해진다.
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        // OrderServiceImpl은 변경을 하나도 안해도 되고 구현객체만 변경해주면 된다.
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
