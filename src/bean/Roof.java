package bean;

import java.sql.Date;

public class Roof {
    public static byte STATUS_TOBEREVIEWED = 0;//待审核
    public static byte STATUS_ATTHETOP = 1;//置顶中
    public static byte STATUS_EXPIRED = 2;//已过期

    private long jid;//职位编号
    private byte pos;//置顶位置，不同的位置消耗的积分不同
    private Date start;//起始日期
    private Date end;//结束日期
    private byte status;//状态

    public long getJid() {
        return jid;
    }

    public void setJid(long jid) {
        this.jid = jid;
    }

    public byte getPos() {
        return pos;
    }

    public void setPos(byte pos) {
        this.pos = pos;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

}
