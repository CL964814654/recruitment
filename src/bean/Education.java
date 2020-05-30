package bean;

import java.util.Date;

public class Education {
    public static byte DEGREE_UNDERHIGHSCHOOL = 0;//高中以下
    public static byte DEGREE_HINHSCHOOL = 1;//高中
    public static byte DEGREE_SPECIALSCHOOL = 2;//中专
    public static byte DEGREE_JUNIORSCHOOL = 3;//大专
    public static byte DEGREE_UNDERGRADUATE = 4;//本科
    public static byte DEGREE_MASTER = 5;//硕士
    public static byte DEGREE_DOCTOR = 6;//博士

    private long id;//主键，经历编号
    private long cid;//求职者编号，与账号表中的id对应
    private String school;
    private String major;
    private  byte degree;
    private Date start;
    private Date end;

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(byte degree) {
        this.degree = degree;
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
}
