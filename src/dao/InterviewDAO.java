package dao;

import bean.Interview;
import database.DaoQueryResult;
import database.DaoUpdateResult;
import database.DbUtil;
import database.QueryConditions;

import java.sql.Connection;

/**
 * Created by 陈磊 on 2020/5/18.
 */
public class InterviewDAO {

    /**
     * 添加一条面试记录
     * @param conn 连接数据库
     * @param i 面试详情
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult insert(Connection conn, Interview i){
        String sql = "insert into interview (cid, jid, time, address, phone, status) values (?,?,?,?,?,?)";
        Object[] params = {i.getCid(),i.getJid(), i.getTime(), i.getAddress(), i.getPhone(), Interview.STATUS_INVITING};
        return DbUtil.update(conn, sql, params);
    }

    /**
     * 根据指定面试编号修改面试状态
     * @param conn 连接数据库
     * @param id   面试编号
     * @param direction  是否接受面试邀请
     * @param reason 拒绝面试原因
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult accept(Connection conn, long id, boolean direction,String reason){
        String sql =direction ? "update interview set status=? where id=?" : "update interview set status=?,reason=? where id=?";
        Object[] params;
        if(direction){
            params= new Object[]{Interview.STATUS_INTERVIEWING, id};//面试中
        }else{
            params= new Object[]{Interview.STATUS_REFUSED, reason,id};//已拒绝
        }
        return DbUtil.update(conn, sql, params);
    }

    /**
     * 根据指定面试编号修改面试状态
     * @param conn 连接数据库
     * @param id   面试编号
     * @param direction  是否录用
     * @param reason 拒绝面试原因
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult employee(Connection conn, long id, boolean direction,String reason){
        String sql =direction ? "update interview set status=? where id=?" : "update interview set status=?,reason=? where id=?";
        Object[] params;
        if(direction){
            params= new Object[]{Interview.STATUS_EMPLOYED, id};//录用
        }else{
            params= new Object[]{Interview.STATUS_NOHIRING, reason,id};//不录用
        }
        return DbUtil.update(conn, sql, params);
    }

   /**
     * 根据职位编号获取指定职位信息
     * @param conn 连接数据库
     * @param iid  面试编号
     * @return 查询结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoQueryResult get(Connection conn, long iid){
        QueryConditions conditions = new QueryConditions();
        conditions.add("id","=",iid);
        return DbUtil.get(conn, "interview", conditions, Interview.class);
    }

}
