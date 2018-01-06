package spring.learn.annotation;

@CustomAnnotation(value="RpcServer")
public class HelloServerImpl implements HelloServer{
	public String hello(String name){
		return "Hello "+name;
	}
	public void test(){
		System.out.println("test");
	}
}
