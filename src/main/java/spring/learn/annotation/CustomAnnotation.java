package spring.learn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Target(ElementType.TYPE)//注解用在类上
@Retention(RetentionPolicy.RUNTIME)//JVM将在运行期也保留注释，因此可以通过反射机制读取注释
@Component
public @interface CustomAnnotation {
	String value() default "";
}
