package dao;

import bean.Score;
import database.DaoUpdateResult;
import database.DbUtil;
import java.sql.Connection;

/**
 * Created by 陈磊 on 2020/5/18.
 */
public class ScoreDAO {

    /**
     * 添加一条积分记录
     * @param conn 连接数据库
     * @param s 积分记录明细
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult insert(Connection conn, Score s){
        String sql = "insert into score (id, quantity, type, date, comment) values(?,?,?,now(),?)";
        Object[] params = {s.getId(), s.getQuantity(), s.getType(), s.getComment()};
        return DbUtil.update(conn, sql, params);
    }
}
