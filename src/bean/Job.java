package bean;

import java.util.Date;

public class Job {//职位表
    public static byte STATUS_MODIFING = 0;//待修改
    public static byte STATUS_CHECKING = 1;//待审核
    public static byte STATUS_USED = 2;//招聘中
    public static byte STATUS_REMOVED = 3;//已下架
    public static byte STATUS_DELETED = 4;//已删除

    private long id;//主键，职位编号
    private long eid;//发布职位的企业编号，外键
    private String title;//职位标题
    private String category;//职位类别
    private int salary1;//薪资最低值，-1表示面议
    private int salary2;//薪资最大值
    private int amount;//招聘人数
    private String city;//工作城市
    private byte degree;//学历要求
    private int years;//工作年限
    private int age;//年龄限制
    private int welfare;//福利
    private String extra;//自定义福利
    private Date modtime;//最后更新时间
    private byte status;//状态
    private String reason;//未过审原因

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEid() {
        return eid;
    }

    public void setEid(long eid) {
        this.eid = eid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSalary1() {
        return salary1;
    }

    public void setSalary1(int salary1) {
        this.salary1 = salary1;
    }

    public int getSalary2() {
        return salary2;
    }

    public void setSalary2(int salary2) {
        this.salary2 = salary2;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(byte degree) {
        this.degree = degree;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWelfare() {
        return welfare;
    }

    public void setWelfare(int welfare) {
        this.welfare = welfare;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Date getModtime() {
        return modtime;
    }

    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
