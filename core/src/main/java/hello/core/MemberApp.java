package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {


//        외부에서 주입을 받는다.
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

//        외부에서 주입없이 구현객체를 받는다.
//        MemberService memberService = new MemberServiceImpl();

//      스프링 컨테이너에 등록된 것들을 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "MemberA", Grade.VIP);

        // 구현 객체를 MemberServiceImpl을 해줌으로써 -> MemberServiceImple의 Join메소드 -> MemberRepository의 save메소드 호출
        memberService.join(member);
        
        Member findMember = memberService.findMember(1L);
        System.out.println(member.getName());
        System.out.println(findMember.getName());

    }
}
