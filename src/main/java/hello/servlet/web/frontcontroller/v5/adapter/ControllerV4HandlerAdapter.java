package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// V4 핸들러 어댑터
public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        // ControllerV4를 처리할 수 있는 어댑터인지 확인 : true 가능, false 불가능
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        // 핸들러를 ControllerV4로 케스팅 하고, paramMap, model을 만들어서 해당 컨트롤러를 호출하면서 파라미터로 전달
        //그리고 viewName을 반환 받는다.
        String viewName = controller.process(paramMap, model);
        // 어댑터는 반환된 viewName을 이용해 ModelView 객체를 만들어 반환한다
        ModelView mv = new ModelView(viewName);
        // 컨트롤러에서 데이터를 넣은 모델을 ModelView 객체에 세팅한다
        mv.setModel(model);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
        return paramMap;
    }
}
