package dao;

import bean.Skill;
import database.DaoQueryResult;
import database.DaoUpdateResult;
import database.DbUtil;
import database.QueryConditions;

import java.sql.Connection;

/**
 * Created by 陈磊 on 2020/5/18.
 */
public class SkillDAO {

    /**
     * 根据求职者编号修改求职者技能信息
     * @param conn 连接数据库
     * @param s 求职者技能信息
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult update(Connection conn, Skill s){
        String sql = "update skill set certs=?, extra=? where cid=?";
        Object []params = {s.getCerts(), s.getExtra(),s.getCid()};
        return DbUtil.update(conn, sql, params);
    }

    /**
     * 根据求职者编号获取指定技能信息
     * @param conn 连接数据库
     * @param cid 求职者编号
     * @return 查询结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoQueryResult get(Connection conn, long cid){
        QueryConditions conditions = new QueryConditions();
        conditions.add("cid","=",cid);
        return DbUtil.get(conn, "skill", conditions, Skill.class);
    }

    /**
     * 添加一条技能信息
     * @param conn 连接数据库
     * @param s 技能详情
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult insert(Connection conn, Skill s){
        String sql = "insert into skill (cid, certs, extra) values(?,?,?)" ;
        Object []params = {s.getCid(), s.getCerts(), s.getExtra()};
        return DbUtil.update(conn,sql,params);
    }
}
