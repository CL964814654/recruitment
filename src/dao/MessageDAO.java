package dao;

import bean.Message;
import database.DaoUpdateResult;
import database.DbUtil;

import java.sql.Connection;

/**
 * Created by 陈磊 on 2020/5/18.
 */
public class MessageDAO {

    /**
     * 添加一条消息记录
     * @param conn 连接数据库
     * @param m 消息详情
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult insert(Connection conn, Message m){
        String sql = "insert into message(receiver, title, content, time, readed) values (?,?,?,now(),0)";
        Object[] params = {m.getReceiver(), m.getTitle(), m.getContent()};
        return DbUtil.update(conn, sql, params);
    }
}
