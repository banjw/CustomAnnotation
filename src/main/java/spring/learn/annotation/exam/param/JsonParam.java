package spring.learn.annotation.exam.param;

import java.lang.annotation.*;

/**
 * 用于标记Controller层方法参数绑定数据类型为json格式数据。
 *
 * 示例：/users/add?userid=100&dept={"id":1,"name":"部门1"}
 * <pre>
 * @RequestMapping("/users/add")
 * public void addUser(Integer userid, @JsonParam("dept") Dept userDept) {
 *
 * }
 * </pre>
 */
@Target(ElementType.PARAMETER)//注解用在方法上
@Retention(RetentionPolicy.RUNTIME)//JVM将在运行期也保留注释，因此可以通过反射机制读取注释
@Documented
public @interface JsonParam {
    String value() default "";
}
