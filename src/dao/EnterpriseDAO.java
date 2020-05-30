package dao;

import bean.Enterprise;
import database.*;

import java.sql.Connection;

/**
 * Created by 陈磊 on 2020/5/18.
 */
public class EnterpriseDAO {
    /**
     * 根据指定企业编号修改企业积分
     * @param conn 连接数据库
     * @param eid 企业编号
     * @param value 积分改变数值（充值为正值，消耗为负值）
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    //score为剩余积分不能为负,增加一个判断条件，剩余积分要大于value的绝对值（因为value的值可正可负）
    public static DaoUpdateResult updateScore(Connection conn, long eid, int value){
        String sql = "update enterprise set score = score+? where id = ? and score > abs(?)";
        Object []params = {value, eid,value};
        return DbUtil.update(conn, sql,params);
    }
    /**
     * 根据指定编号获取企业信息
     * @param conn 连接数据库
     * @param eid 企业编号
     * @return 查询结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoQueryResult get(Connection conn, long eid){
        QueryConditions conditions = new QueryConditions();
        conditions.add("id","=",eid);
        return DbUtil.get(conn, "enterprise", conditions, Enterprise.class);
    }

}
