package dao;

import database.DaoExistResult;
import database.DaoUpdateResult;
import database.DbUtil;
import database.QueryConditions;

import java.sql.Connection;

/**
 * Created by 陈磊 on 2020/5/18.
 */
public class FavoriteDAO {

    /**
     * 添加一条职位收藏记录
     * @param conn 连接数据库
     * @param cid 求职者编号
     * @param jid 职位编号
     * @return
     */
    public static DaoUpdateResult insert(Connection conn, long cid, long jid){
        String sql = "insert into favorite (cid, jid) values(?,?)" ;
        Object []params = {cid, jid};
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 判断求职者是否收藏某个职位
     * @param conn 连接数据库
     * @param cid 求职者编号
     * @param jid 职位编号
     * @return 查询结果，格式："{success:true,msg:"",exist:true}"
     */
    public static DaoExistResult exist(Connection conn, long cid, long jid){
        QueryConditions conditions = new QueryConditions();
        conditions.add("cid","=",cid);
        conditions.add("jid","=",jid);
        return DbUtil.exist(conn, "favorite", conditions);
    }

    /**
     * 根据职位编号删除指定职位收藏
     * @param conn 连接数据库
     * @param jid 职位编号
     * @param cid 求职者编号
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult delete(Connection conn, long jid, long cid){
        String sql = "delete from favorite where jid=? and cid=?";
        Object []params = {jid,cid};
        return DbUtil.update(conn,sql,params);
    }
}
