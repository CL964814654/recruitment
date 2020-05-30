package bean;

import java.util.Date;

public class Interview {
    public static byte STATUS_INVITING = 0;//邀请中
    public static byte STATUS_INTERVIEWING = 1;//面试中
    public static byte STATUS_REFUSED = 2;//已拒绝
    public static byte STATUS_EMPLOYED = 3;//已录用
    public static byte STATUS_NOHIRING = 4;//不录用

    private long id;//主键，面试编号
    private long cid;//求职者编号
    private long jid;//职位编号
    private Date time;//面试时间
    private String address;//面试地址
    private String phone;//联系电话（用人单位）
    private String reason;//拒绝原因
    private byte status;//状态

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

}
