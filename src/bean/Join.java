package bean;

import java.util.Date;

public class Join {//参保表
    public static byte CATEGORY_CONTRACTWORKER = 0;//合同工
    public static byte CATEGORY_TEMPORARYWORKER  = 1;//临时工
    public static byte CATEGORY_OTHER = 2;//其它

    private long iid;
    private long bid;
    private byte category;//用工形式
    private String depart;//工作单位
    private String city;//外派地
    private Date worktime;//参加工作时间
    private int salary;//月缴费工资
    private Date start;//参保开始月份
    private byte reason;//变更原因
    private byte remedy;//是否补收

    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(byte category) {
        this.category = category;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getWorktime() {
        return worktime;
    }

    public void setWorktime(Date worktime) {
        this.worktime = worktime;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
    public int getReason() {
        return reason;
    }

    public void setReason(byte reason) {
        this.reason = reason;
    }

    public int getRemedy() {
        return remedy;
    }

    public void setRemedy(byte remedy) {
        this.remedy = remedy;
    }


}
