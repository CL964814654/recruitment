package bean;

public class Franchisee {//加盟商表
    public static byte STATUS_PAUSE = 0;//暂停
    public static byte STATUS_NORMAL = 1;//正常

    private long id;//账号编号
    private String name;
    private String city;
    private byte exclusive;
    private byte status;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getExclusive() {
        return exclusive;
    }

    public void setExclusive(byte exclusive) {
        this.exclusive = exclusive;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
