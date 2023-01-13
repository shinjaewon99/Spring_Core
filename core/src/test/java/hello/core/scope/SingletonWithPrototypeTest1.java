package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);
/*
        프로토 타입임으로 새로운 타입이 생성된다.
         count 값이 그래서 1,2 둘다 1로 반환된다.
         */
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);

        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

         /*
         싱글톤임으로 같은 빈을 사용해서 1, 2가 출력되어야 되지만,
         ObjcetProvider을 사용해 지정한 빈을 찾아줬음으로 항상 새로운 프로토타입 빈에서 꺼내와 1, 1 출력
         */
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }

    // default값이 싱글톤임으로 생략 해줘도 된다.
    @Scope("singleton")
    static class ClientBean {


        // ObjectProvider을 사용해 지정한 빈을 찾아준다.
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")

    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;

        }

        public int getCount() {
            return count;

        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        // 프로토 타입이라 호출 X
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
