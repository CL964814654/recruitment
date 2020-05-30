package dao;

import bean.Roof;
import database.DaoExistResult;
import database.DaoUpdateResult;
import database.DbUtil;
import database.QueryConditions;

import java.sql.Connection;
import java.sql.Date;

/**
 * Created by 陈磊 on 2020/5/18.
 */
public class RoofDAO {

    /**
     * 添加一条顶置记录
     * @param conn 连接数据库
     * @param r 顶置明细
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult insert(Connection conn, Roof r){
        String sql = "insert into roof (jid, pos, start, end, status) values (?,?,?,?,0)";
        Object[] params = {r.getJid(), r.getPos(), r.getStart(), r.getEnd()};
        return DbUtil.update(conn, sql, params);
    }

    /**
     * 判断指定顶置位置在指定时间段内是否被预定(被占用返回true没有返回false)
     * @param conn 连接数据库
     * @param pos 指定位置
     * @param start 开始时间
     * @param end 结束时间
     * @return "{success:true,msg:"",effects:1}"
     */
    public static DaoExistResult exist(Connection conn, int pos, Date start, Date end){
        QueryConditions conditions = new QueryConditions();
        conditions.add("pos","=",pos);
        conditions.add("start", "<=", end);
        conditions.add("end", ">=", start);
        return DbUtil.exist(conn, "roof", conditions);
    }
}
