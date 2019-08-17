package spring.learn.annotation.exam.log;

import java.lang.annotation.*;

/**
 * 请完成此注解功能。
 */
@Target(ElementType.METHOD)//注解用在方法上
@Retention(RetentionPolicy.RUNTIME)//JVM将在运行期也保留注释，因此可以通过反射机制读取注释
@Documented
public @interface UserAuditLog {
    String content() default "";
}
