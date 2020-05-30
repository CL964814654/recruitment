package dao;

import bean.*;
import database.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by 陈磊 on 2020/5/18.
 */
public class AppDAO {

    /**
     * 添加一条求职记录
     * @param conn 连接数据库
     * @param a 求职记录对象
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult insert(Connection conn, App a){
        String sql = "insert into app (cid, jid, time) values(?,?,now())" ;
        Object []params = {a.getCid(), a.getJid()};
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 判断求职记录是否存在对应企业与求职编号
     * @param conn 连接数据库
     * @param cid 求职编号
     * @param eid 企业编号
     * @return true/false
     */
    public static Boolean exist(Connection conn, long cid, long eid){
        String sql = "select job.eid ,app.cid from app left join job on app.jid=job.id where eid=? and cid=? limit 1";
        Object []params = {eid, cid};
        QueryRunner qr = new QueryRunner();
        Long id = null;
        try {
            id = qr.query(conn,sql, new ScalarHandler<Long>(),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id!=null;
    }
}
