package spring.learn.annotation.exam.log;

public class AuditLog {

    // 记录用户的访问IP地址
    private String logip;
    // 日志记录时间戳，秒数
    private long logtime;
    // 日志内容
    private String content;

    public String getLogip() {
        return logip;
    }

    public void setLogip(String logip) {
        this.logip = logip;
    }

    public long getLogtime() {
        return logtime;
    }

    public void setLogtime(long logtime) {
        this.logtime = logtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AuditLog [logip=" + logip + ", logtime=" + logtime + ", content=" + content + "]";
    }
}
