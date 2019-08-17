package spring.learn.annotation.exam.log;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class TestEL {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        User user = new User();
        user.setName("测试");
        EvaluationContext context = new StandardEvaluationContext();
        //在上下文中设置变量，变量名为user，内容为user对象
        context.setVariable("user", user);
        String  userName = (String)parser.parseExpression("#user.name").getValue(context);
        Expression exp = parser.parseExpression("'创建新用户：'.concat(#user.name)");
        String value = exp.getValue(context,String.class);
        System.out.println(value);
    }
}

