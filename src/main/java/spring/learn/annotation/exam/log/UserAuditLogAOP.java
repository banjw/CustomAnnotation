package spring.learn.annotation.exam.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 请完成此类来实现 @UserAuditLog 的拦截和日志记录。
 *（调用 AuditLogService.saveLog(AuditLog log) 方法进行日志记录）。
 */
@Component
@Aspect
public class UserAuditLogAOP{

    private AuditLogService auditLogService = new AuditLogService();
    // 请完成这个AOP类的功能
    private AuditLog log = new AuditLog();
    @Before(value="@annotation(spring.learn.annotation.exam.log.UserAuditLog)")
    public void dofore(JoinPoint jp) {
        try {
            //通过获取UserAuditLog注解
            Method proxyMethod = ((MethodSignature) jp.getSignature()).getMethod();
            Method targetMethod = jp.getTarget().getClass().getMethod(proxyMethod.getName(), proxyMethod.getParameterTypes());
            UserAuditLog userAuditLog = targetMethod.getAnnotation(UserAuditLog.class);
            //获取request
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            //处理注解逻辑
            String contentStr = userAuditLog.content();
            String ipAddress = getIPAddress(request);
            log.setLogip(ipAddress);
            log.setLogtime(System.currentTimeMillis()/1000L);
            Object[] args = jp.getArgs();
            String content = readExpr(contentStr, (User) args[0]);
            log.setContent(content);
            //
            auditLogService.saveLog(log);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String readExpr(String expr, User user) {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        //在上下文中设置变量，变量名为user，内容为user对象
        context.setVariable("user", user);
        Expression exp = parser.parseExpression(expr);
        String value = exp.getValue(context,String.class);
        return value;
    }


}

