package spring.learn.annotation.exam.log;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    // 日志记录内容，要支持 Spring-EL表达式
    @UserAuditLog(content="'创建新用户：'.concat(#user.name)")
    public boolean createUser(User user) {
        return true;
    }
}
