package hello.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// 컴포넌트 스캔으로 자동으로 스프링빈 등록
@ComponentScan(
        /*
           해당 패키지만 컴포넌트 스캔의 대상이된다
           basePackages = "hello.core.member",
           */
        // 컴포넌트 스캔으로 자동으로 스프링빈 등록 그중에서 필터를 하여 뺄것들 ex) Configuration이라는 어노테이션을 뺀다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
