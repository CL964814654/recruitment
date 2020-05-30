package dao;

import bean.Account;
import bean.Enterprise;
import database.*;

import java.sql.Connection;

public class AccountDAO {
    public static void main(String[] args) {
        Connection conn = RecruitConnUtil.getConnection();

        //准备账号
        Account a = new Account();
        a.setName("test");
        a.setPassword("123");
        a.setRole(Account.ROLE_ENTERPRISE);
        a.setOpenId("fiu934324");

        DaoUpdateResult res1 = AccountDAO.insert(conn,a);
        if(res1.success){
            long id = (long) res1.extra;
            Enterprise e = new Enterprise();
            e.setId(id);
            //DaoUpdateResult res2 = EnterpriseDAO.insert(conn,e);
            System.out.println("添加账号成功");
        }else{
            System.out.println("添加账号错误");
        }
        RecruitConnUtil.closeConnection(conn);
    }

    /**
     * 添加账号
     * @param conn 数据库连接
     * @param a 账号信息
     * @return 更新结果，格式："{success:true,msg:"",extra:刚插入的主键}"
     */
    public static DaoUpdateResult insert(Connection conn, Account a){
        String sql = "insert into account(name,password,role,openId)values(?,?,?,?)";
        Object []params = {a.getName(), a.getPassword(), a.getRole(),a.getOpenId()};
        return DbUtil.insert(conn,sql,params);
    }
}
