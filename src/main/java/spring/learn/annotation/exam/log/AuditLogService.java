package spring.learn.annotation.exam.log;

public class AuditLogService {

    public void saveLog(AuditLog log) {
        // 这里采用 System.out.print 来模拟数据库记录操作
        System.out.println(">>>> 记录操作日志："+ String.valueOf(log));
    }
}
