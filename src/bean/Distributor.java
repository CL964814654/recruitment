package bean;

public class Distributor {//渠道商
    public static byte STATUS_TOBEMODIFIED = 0;//待修改
    public static byte STATUS_TOBEREVIEWED = 1;//待审核
    public static byte STATUS_REVIEWED = 2;//已审核

    private long id;//账号编号
    private String name;
    private String phone;
    private String address;
    private byte status;//审核状态

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
