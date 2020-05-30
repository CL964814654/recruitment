package dao;

import bean.Intention;
import database.DaoQueryResult;
import database.DaoUpdateResult;
import database.DbUtil;
import database.QueryConditions;

import java.sql.Connection;

/**
 * Created by 陈磊 on 2020/5/18.
 */
public class IntentionDAO {

    /**
     * 添加一条求职意向
     * @param conn 连接数据库
     * @param i 工作经历对象
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult insert(Connection conn, Intention i){
        String sql = "insert into intention (cid, position, industry, city, salary1, salary2) values(?,?,?,?,?,?)" ;
        Object []params = {i.getCid(), i.getPosition(), i.getIndustry(), i.getCity(), i.getSalary1(), i.getSalary2()};
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 根据求职意向编号删除指定求职意向
     * @param conn 连接数据库
     * @param id 工作经历编号
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult delete(Connection conn, long id){
        String sql = "delete from intention where id=?";
        Object []params = {id};
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 根据求职意向编号修改指定求职意向
     * @param conn 连接数据库
     * @param i 求职意向对象信息
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult update(Connection conn, Intention i){
        String sql = "update intention set position = ?, industry = ?, city = ?, salary1 = ?, salary2 = ? where id = ?";
        Object []params = {i.getPosition(), i.getIndustry(), i.getCity(), i.getSalary1(), i.getSalary2(),i.getId()};
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 根据指定编号获取求职意向
     * @param conn 连接数据库
     * @param id 求职意向编号
     * @return 查询结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoQueryResult get(Connection conn, long id){
        QueryConditions conditions = new QueryConditions();
        conditions.add("id","=",id);
        return DbUtil.get(conn, "intention", conditions, Intention.class);
    }
}
