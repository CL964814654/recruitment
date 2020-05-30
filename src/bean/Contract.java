package bean;

import java.util.Date;

public class Contract {//合同表
    private long id;//合同编号，自增长
    private String name;//合同签订方名称
    private Date date;//签订日期
    private Date expire;//到期日期
    private String accessory;//附件url地址
    private byte noticed;//是否已发送到期提醒
    private String comment;//备注

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public int getNoticed() {
        return noticed;
    }

    public void setNoticed(byte noticed) {
        this.noticed = noticed;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
