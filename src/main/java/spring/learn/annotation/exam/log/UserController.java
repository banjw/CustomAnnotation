package spring.learn.annotation.exam.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //    @PostMapping("/users/add")
    @RequestMapping("/users/add")
    @ResponseBody
    public String createUser() {
        // 模拟新用户
        User user = new User();
        user.setName("张小军");

        boolean res = userService.createUser(user);
        return res ? "200" : "500";
    }

}
