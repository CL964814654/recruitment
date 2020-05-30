package dao;

import bean.Education;
import database.*;

import java.sql.Connection;

/**
 * Created by 陈磊 on 2020/5/17.
 */
public class EducationDAO {
    public static void main(String[] args) {
        Connection conn = RecruitConnUtil.getConnection();

        RecruitConnUtil.closeConnection(conn);
    }

    /**
     *添加教育经历
     * @param conn 数据库连接
     * @param e 教育信息
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult insert(Connection conn , Education e){
        String sql = "insert into education(cid,school,major,degree,start,end)values(?,?,?,?,?,?)";
        Object []params = {e.getCid(), e.getSchool(), e.getMajor(),Education.DEGREE_DOCTOR, e.getStart(), e.getEnd()};
        return DbUtil.update(conn,sql,params);
    }

    /**
     *删除教育经历
     * @param conn 数据库连接
     * @param eid 教育经历编号
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult delete(Connection conn, long eid){
        String sql = "delete from education where id=?";
        Object []params = {eid};
        return DbUtil.update(conn,sql,params);
    }

    /**
     *修改教育经历
     * @param conn 连接数据库
     * @param e 更改的教育信息
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult update (Connection conn, Education e){
        String sql = "update education set school = ?, major = ?, degree = ?, start = ?, end = ? where id = ?";
        Object []params = {e.getSchool(), e.getMajor(), e.getDegree(), e.getStart(),e.getEnd(),e.getId()};
        return DbUtil.update(conn, sql, params);
    }

    /**
     * 根据编号查询指定教育经历
     * @param conn 连接数据库
     * @param cid 求职者编号
     * @return 查询结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoQueryResult get (Connection conn,long cid){
        QueryConditions conditions = new QueryConditions();
        conditions.add("cid","=",cid);
        return DbUtil.get(conn, "education", conditions, Education.class);    }

    /**
     * 检索教育经历
     * @param conn 数据库连接
     * @param parameters 查询条件
     * @return 检索结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoQueryListResult gets (Connection conn, QueryParameter parameters){
        return DbUtil.getList(conn, "education", parameters, Education.class);
    }
}
