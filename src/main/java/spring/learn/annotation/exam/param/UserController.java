package spring.learn.annotation.exam.param;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    /**
     * http://xxx/users/add?userid=100&dept={"id":1,"name":"部门1"}
     *
     * 请求参数 dept={"id":1,"name":"部门1"} 将被JSON解析器转换为相应的对象并绑定到
     * @JsonParam 修饰的 userDept 参数上。
     *
     * @param userid 自动绑定了请求参数中的 userid
     * @param userDept 自动绑定了请求参数中的 dept(自动将json格式值转换为JavaBean对象)
     */
    @RequestMapping("/users/add")
    @ResponseBody
    public Map<String, Object> addUser(Integer userid, @JsonParam("dept") Dept userDept) {
        Map<String, Object> data = new HashMap<>(2);
        data.put("userId", userid);
        data.put("userDept", userDept);

        return data;
    }

}
