package bean;

import java.util.Date;

public class Score {//积分记录表
    public static byte TYPE_INCOME = 0;//收入
    public static byte TYPE_PAY = 1;//支出

    private long id;//外键，账号编号
    private int quantity;//积分变化
    private byte type;//0-收入；1-支出
    private Date date;//产生日期
    private String comment;//备注

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
