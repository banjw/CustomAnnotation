package spring.learn.annotation.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.learn.annotation.hello.MessagePrinter;
import spring.learn.annotation.hello.MessageService;

@Configuration
@ComponentScan
public class App {
	@Bean
	MessageService mockMessageService() {
		return new MessageService() {
			public String getMessage() {
				return "Hello World!";
			}
		};
	}

	public static void main(String[] args) {
//		ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		MessagePrinter printer = context.getBean(MessagePrinter.class);
		printer.printMessage();
	}
}
