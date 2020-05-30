package bean;

public class Business {//业务表
    private long id;//主键,业务编号
    private String name;//业务名称
    private String abbrevia;//缩写，用于功能菜单显示
    private String desc;//业务的详细描述

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

    public String getAbbrevia() {
        return abbrevia;
    }

    public void setAbbrevia(String abbrevia) {
        this.abbrevia = abbrevia;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
