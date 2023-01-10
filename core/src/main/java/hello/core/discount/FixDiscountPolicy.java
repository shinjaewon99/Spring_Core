package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/*
@Qualifier를 통해 추가 구분자를 해줌으로써 호출하여 사용할때 구분해준다.
@Qualifier("fixDiscountPolicy")
*/
@Component
public class FixDiscountPolicy implements DiscountPolicy{
    private int discountFixAmount = 1000; // 고정으로 천원 할인
    
    @Override
    public int discount(Member member, int price) {
        
        // enum 타입은 == 사용이 맞다 , "String 데이터 타입은 .equals()사용"
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else{
            return 0;
        }

    }
}
