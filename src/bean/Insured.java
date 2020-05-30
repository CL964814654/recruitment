package bean;

public class Insured {//参保人表
    public static byte STATE_NONLOCALTOWN = 0;//外地非农业户口（外地城镇）
    public static byte STATE_LOCALTOWN = 1;//本地非农业户口（本地城镇）
    public static byte STATE_RURALAREA = 2;//外地农业户口（外地农村）
    public static byte STATE_TOWN = 3;//非农业户口（城镇）
    public static byte STATE_COUNTRYSIDE = 4;//农业户口（农村）
    public static byte STATE_HONGKONG = 5;//港澳台
    public static byte STATE_FOREIGN = 6;//外籍

    private long id;//参保人编号
    private String cardId;//身份证号码
    private String name;//姓名
    private String phone;//电话
    private byte state;//户口性质
    private String address;//户籍地址

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
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

    public int getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
