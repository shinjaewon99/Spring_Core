package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

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
