package spring.learn.annotation.exam.param;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class JsonParamResolver implements HandlerMethodArgumentResolver {
    public boolean supportsParameter(MethodParameter methodParameter) {
        if(methodParameter.getParameterType().isAssignableFrom(Dept.class) && methodParameter.hasParameterAnnotation(JsonParam.class)){
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String dept = nativeWebRequest.getParameter("dept");
        if(StringUtils.isEmpty(dept)){
            return null;
        }
        dept = new String(dept.getBytes("ISO-8859-1"), "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(dept);
        String name = jsonObject.getString("name");
        Integer id = jsonObject.getInteger("id");
        return new Dept(id, name);
    }
}
