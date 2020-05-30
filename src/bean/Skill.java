package bean;

public class Skill {
    private long cid;//求职者编号
    private String certs;//存储证书编码序列，最多10个证书
    private String extra;//关于技能的其他描述

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getCerts() {
        return certs;
    }

    public void setCerts(String certs) {
        this.certs = certs;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
