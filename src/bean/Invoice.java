package bean;

public class Invoice {//开票信息表
    private String id;//主键
    private String company;//公司全称
    private String taxNO;//纳税人识别号
    private String address;//公司地址
    private String phone;//联系电话
    private String contact;//联系人
    private String bank;//开户行
    private String bankaccount;//银行账户

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTaxNO() {
        return taxNO;
    }

    public void setTaxNO(String taxNO) {
        this.taxNO = taxNO;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getBankaccount() {
        return bankaccount;
    }
}
