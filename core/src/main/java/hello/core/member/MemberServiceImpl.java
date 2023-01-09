package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @ComponentScan을 통해 @Component 어노테이션이 붙은 클래스를 빈으로 등록한다.
@Component
public class MemberServiceImpl implements MemberService {


    // 구현 객체를 선택해줘야된다 (구현 객체 선택 안할시 NullPoint 예외가 터진다.)
    // 추상화 와 구체와 둘다 의존 = DIP 위반
    private final MemberRepository memberRepository;

    // 자동 의존관계 주입
    @Autowired // 마치 ac.getBean(MemberRepository.class);
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 다형성에의해 MemberRepository가 아닌 MemoryMemberRepository의 save(오버라이드된)가 호출된다.
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
