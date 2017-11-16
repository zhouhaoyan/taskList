package person.zhy.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import person.zhy.utils.base.TokenException;
import person.zhy.utils.jwt.Jwt;
import person.zhy.utils.jwt.TokenState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
public class TokenInterceptor implements HandlerInterceptor  {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    public static final String KEY_USER = "user";
    public static final String TOKEN = "token";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //  System.out.println(">>>LogInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        String token = request.getParameter(TOKEN);
        String id = request.getParameter("id");
        if(token==null||token==""){
            throw new TokenException("token未送");

        }
        Map<String, Object> resultMap=Jwt.validToken(token);
        logger.info(resultMap.toString());

        //如果是有效的TOKEN
        if(TokenState.VALID==TokenState.getTokenState((String) resultMap.get("state"))){
            MDC.put(KEY_USER,(String) resultMap.get("account"));
        }else{
            logger.error("无效的TOKEN={},内容={}",token,resultMap.toString());
            throw new TokenException("token无效");

        }
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        //   System.out.println(">>>LogInterceptor>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // System.out.println(">>>LogInterceptor>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
