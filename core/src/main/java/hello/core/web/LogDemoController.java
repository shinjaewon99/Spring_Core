package hello.core.web;


import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;

    // MyLogger를 찾을수 있는 LookUp이 주입된다.
    private final ObjectProvider<MyLogger> myLoggerProvider;


    @RequestMapping("log-demo")
    // view 템플릿에 렌더링 되지 않고 문자 그대로 웹에 띄워준다.
    @ResponseBody
    // http 요청 정보를 받을수있다.
    public String logDemo(HttpServletRequest request) {
        MyLogger myLogger = myLoggerProvider.getObject();
        String requestUrl = request.getRequestURL().toString();
        myLogger.setRequestUrl(requestUrl);

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }
}
