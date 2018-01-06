package spring.learn.annotation;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TestAnnotation implements ApplicationContextAware{
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, Object> map = applicationContext.getBeansWithAnnotation(CustomAnnotation.class);
		Set<Entry<String, Object>> entrySet = map.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			//获取自定义注解上的value
			String value = entry.getValue().getClass().getAnnotation(CustomAnnotation.class).value();
			System.out.println("The value of CustomAnnotation is "+value);
			
			//反射被注解的类，并调用指定方法
			try {
				Method hello = entry.getValue().getClass().getMethod("hello", String.class);
				Object obj = hello.invoke(entry.getValue(), "reflect method has been execute ");
				System.out.println(obj);
				
				Method test = entry.getValue().getClass().getMethod("test", new Class[]{});
				test.invoke(entry.getValue(),null);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
