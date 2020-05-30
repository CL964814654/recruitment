package dao;

import bean.Job;
import database.DaoQueryResult;
import database.DaoUpdateResult;
import database.DbUtil;
import database.QueryConditions;

import java.sql.Connection;

/**
 * Created by 陈磊 on 2020/5/18.
 */
public class JobDAO {
    /**
     * 创建一条职位信息
     * @param conn 连接数据库
     * @param j 职位信息
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult insert(Connection conn, Job j){
        String sql = "insert into job (eid, title, category, salary1, salary2, amount, city, degree, years, age, welfare, extra, modtime, status) values(?,?,?,?,?,?,?,?,?,?,?,?,now(),1)";
        Object []params = {j.getEid(), j.getTitle(), j.getCategory(), j.getSalary1(), j.getSalary2(), j.getAmount(), j.getCity(), j.getDegree(), j.getYears(), j.getAge(), j.getWelfare(), j.getExtra()};
        return DbUtil.update(conn, sql, params);
    }

    /**
     * 根据编号修改职位信息
     * @param conn 连接数据库
     * @param j 职位信息
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult update(Connection conn, Job j){
        String sql = "update job set title=?, category=?, salary1=?, salary2=?, amount=?, city=?, degree=?, years=?, age=?, welfare=?, extra=? where id=?";
        Object []params = {j.getTitle(), j.getCategory(), j.getSalary1(), j.getSalary2(), j.getAmount(), j.getCity(), j.getDegree(), j.getYears(), j.getAge(), j.getWelfare(), j.getExtra(), j.getId()};
        return DbUtil.update(conn, sql, params);
    }

    /**
     * 根据指定职位编号修改职位状态
     * @param conn 连接数据库
     * @param jid 职位编号
     * @param status 需要更改的职位状态
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult updateStatus(Connection conn, long jid, int status){
        String sql = "update job set status = ? where id = ?";
        Object []params = {status, jid};
        return DbUtil.update(conn, sql, params);
    }

    /**
     * 根据职位编号刷新职位时间
     * @param conn 连接数据库
     * @param id 职位编号
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult fresh(Connection conn, long id){
        String sql = "update job set modtime = now() where id = ?";
        Object []params = {id};
        return DbUtil.update(conn, sql, params);
    }

    /**
     * 审核简历
     * @param conn 连接数据库
     * @param jid 简历编号
     * @param direction 是否通过审核
     * @param reason 不通过原因
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult check(Connection conn, long jid, boolean direction,String reason){
        String sql =direction ? "update job set status=? where id=?" : "update job set status=?,reason=? where id=?";
        Object []params;
        if(direction){
            params= new Object[]{Job.STATUS_USED, jid};
        }else{
            params= new Object[]{Job.STATUS_MODIFING, reason, jid};
        }
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 根据职位编号获取指定职位信息
     * @param conn 连接数据库
     * @param jid 职位编号
     * @return 查询结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoQueryResult get(Connection conn, long jid){
        QueryConditions conditions = new QueryConditions();
        conditions.add("id","=",jid);
        return DbUtil.get(conn, "job", conditions, Job.class);
    }

}
