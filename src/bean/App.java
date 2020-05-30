package bean;


import java.sql.Date;

public class App {//求职
    private long id;//求职编号
    private long cid;//求职者编号，外键
    private long jid;//职位编号，外键
    private Date time;//求职时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getJid() {
        return jid;
    }

    public void setJid(long jid) {
        this.jid = jid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
