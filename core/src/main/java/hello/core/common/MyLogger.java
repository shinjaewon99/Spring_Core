package hello.core.common;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
// 가짜 프록시 객체를 생성해 진짜 빈을 요청하는 위임 로직이 들어있다.
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestUrl;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" + requestUrl + "]" + message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create :" + this);
    }

    @PreDestroy
    public void close(){

        System.out.println("[" + uuid + "] request scope bean close :" + this);

    }
}
