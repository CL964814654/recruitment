package bean;

import java.util.Date;

public class Recommend {//企业推荐
    public static byte STATUS_APPLYING = 0;//申请中
    public static byte STATUS_RECOMMENDED = 1;//推荐中
    public static byte STATUS_EXPIRED = 2;//已过期

    private long eid;//企业编号
    private Date start;//开始推荐日期
    private int days;//推荐天数
    private byte status;//状态

    public long getEid() {
        return eid;
    }

    public void setEid(long eid) {
        this.eid = eid;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
