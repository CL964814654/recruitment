package bean;

import java.util.Date;

public class Salary {//工资表
    private Date month;//月份
    private String cardId;//身份证号码
    private String name;//姓名
    private String depart;//部门
    private float payable;//应发工资
    private float paid;//实发工资
    private int pension;//养老保险
    private float health;//医疗保险
    private float disease;//大病保险
    private float unemployment;//失业保险
    private float social;//社保
    private float deduction;//专项扣除合计
    private float tax;//个税
    private float afterTax;//税后实发
    private String comment;//备注

    public int getPension() {
        return pension;
    }

    public void setPension(int pension) {
        this.pension = pension;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getDisease() {
        return disease;
    }

    public void setDisease(float disease) {
        this.disease = disease;
    }

    public float getUnemployment() {
        return unemployment;
    }

    public void setUnemployment(float unemployment) {
        this.unemployment = unemployment;
    }

    public float getSocial() {
        return social;
    }

    public void setSocial(float social) {
        this.social = social;
    }

    public float getDeduction() {
        return deduction;
    }

    public void setDeduction(float deduction) {
        this.deduction = deduction;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getAfterTax() {
        return afterTax;
    }

    public void setAfterTax(float afterTax) {
        this.afterTax = afterTax;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
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

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public float getPayable() {
        return payable;
    }

    public void setPayable(float payable) {
        this.payable = payable;
    }

    public float getPaid() {
        return paid;
    }

    public void setPaid(float paid) {
        this.paid = paid;
    }

}
