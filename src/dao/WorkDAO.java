package dao;

import bean.Work;
import database.DaoQueryResult;
import database.DaoUpdateResult;
import database.DbUtil;
import database.QueryConditions;

import java.sql.Connection;

/**
 * Created by 陈磊 on 2020/5/18.
 */
public class WorkDAO {

    /**
     * 添加一条工作经历
     * @param conn 连接数据库
     * @param w 工作经历对象
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult insert(Connection conn, Work w){
       String sql = "insert into work (cid, department, position, content, start, end) values(?,?,?,?,?,?)" ;
        Object []params = {w.getCid(), w.getDepartment(), w.getPosition(), w.getContent(), w.getStart(), w.getEnd()};
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 根据工作编号删除指定工作经历
     * @param conn 连接数据库
     * @param id 工作经历编号
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult delete(Connection conn, long id){
        String sql = "delete from work where id=?";
        Object []params = {id};
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 根据工作经历编号修改指定工作经历
     * @param conn 连接数据库
     * @param w 工作经历对象信息
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult update(Connection conn, Work w){
        String sql = "update work set department = ?, position = ?, content = ?, start = ?, end = ? where id = ?";
        Object []params = {w.getDepartment(), w.getPosition(), w.getContent(), w.getStart(), w.getEnd(), w.getId()};
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 根据指定编号获取工作经历
     * @param conn 连接数据库
     * @param cid 求职者编号
     * @return 查询结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoQueryResult get (Connection conn, long cid){
        QueryConditions conditions = new QueryConditions();
        conditions.add("cid","=",cid);
        return DbUtil.get(conn, "work", conditions, Work.class);
    }
}
