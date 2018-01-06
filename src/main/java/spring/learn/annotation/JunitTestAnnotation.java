package spring.learn.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring.xml")
@Component
public class JunitTestAnnotation {
	@Autowired
	private HelloServer helloServer;
	
	@Test
	public void test(){
		System.out.println(" ===========begin junit test===========");
		String str = helloServer.hello(" junit test !");
		System.out.println(str);
		System.out.println(" ===========end junit test===========");
	}
}
