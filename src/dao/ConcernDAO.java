package dao;

import bean.Concern;
import database.*;

import java.sql.Connection;

/**
 * Created by 陈磊 on 2020/5/18.
 */
public class ConcernDAO {

    /**
     * 添加一条简历关注信息
     * @param conn 连接数据库
     * @param cid 求职者编号
     * @param eid 企业编号
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult insert(Connection conn, long cid, long eid){
        String sql = "insert into concern (cid, eid) values(?,?)" ;
        Object []params = {cid, eid};
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 判断企业是否关注某一简历
     * @param conn 连接数据库
     * @param cid 简历编号
     * @param eid 企业编号
     * @return "{success:true,msg:"",effects:1}"
     */
    public static DaoExistResult exist(Connection conn, long cid, long eid){
        QueryConditions conditions = new QueryConditions();
        conditions.add("cid", "=", cid);
        conditions.add("eid","=",eid);
        return DbUtil.exist(conn, "concern", conditions);
    }

    /**
     * 删除一条简历关注信息
     * @param conn 连接数据库
     * @param cid 求职者编号
     * @param eid 企业编号
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult delete(Connection conn, long cid, long eid){
        String sql = "delete from concern where eid=? and cid=?" ;
        Object []params = {eid, cid};
        return DbUtil.update(conn,sql,params);
    }
}
