package bean;

public class Account {
    public static byte ROLE_CANDIDATE = 0;//求职者
    public static byte ROLE_ENTERPRISE = 1;//企业
    public static byte ROLE_DISTRIBUTOR = 2;//渠道商
    public static byte ROLE_FRANCHISEE = 3;//加盟商
    public static byte ROLE_ADMINISTRATOR = 4;//系统管理员

    private long id;//账号编号,主键
    private String name;//账号名
    private String password;//密码
    private byte role;//角色
    private  String openId;//微信号

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
