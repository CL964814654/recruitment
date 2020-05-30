package bean;

public class Agent {//代理业务表
    public static byte CATEGORY_PERCENT = 0;//按比例
    public static byte CATEGORY_MONEY = 1;//按金额

    private long distributorId;//渠道商编号
    private long businessId;//业务编号
    private byte category;//佣金计算方式
    private int value;//佣金值

    public long getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(long distributorId) {
        this.distributorId = distributorId;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(byte category) {
        this.category = category;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
