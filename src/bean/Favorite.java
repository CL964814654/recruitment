package bean;

public class Favorite {//职位收藏
    private long cid;//求职者编号，外键
    private long jid;//职位编号，外键

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
}
