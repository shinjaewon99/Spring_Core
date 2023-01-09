package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    private MemberRepository memberRepository;
    
    // 고정 할인
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

/*
     OCP , DIP의 원칙을 지킨것 같지만 아니다.
     이유 : new RateDiscountPolicy()가 구현 객체에도 의존하고 있음으로
*/

    // Grade 등급에 따라 할인이 다름
    private DiscountPolicy discountPolicy;

    @Autowired
    // FixDiscount인지 , RateDiscount인지 외부(AppConfig)에서 주입된다.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // new 연산자를 사용해 Order 클래스의 값을 넣어 반환한다.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
