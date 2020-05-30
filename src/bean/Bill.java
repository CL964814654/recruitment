package bean;

import java.util.Date;

public class Bill {//保险单表
    public static byte STATUS_TOBEMODIFIED = 0;//待修改
    public static byte STATUS_TOBEREVIEWED = 1;//待审核
    public static byte STATUS_REVIEWED = 2;//已审核
    public static byte STATUS_INSURED = 3;//已投保

    private long id;//保险单编号，自增长
    private long did;//渠道商编号
    private Date month;//月份
    private int paid;//支付费用人数
    private byte status;//状态
    private String comment;//备注

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
