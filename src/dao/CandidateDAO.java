package dao;

import bean.Candidate;
import database.*;

import java.sql.Connection;
import java.sql.Date;

public class CandidateDAO {
//    public static void main(String[] args) {
//        Connection conn = RecruitConnUtil.getConnection();
//
//        Candidate c = new Candidate();
//        c.setId(2L);
//        c.setAddress("360402");
//        c.setBirthday(new Date(190,11,10));
//        c.setDegree(Candidate.DEGREE_HINHSCHOOL);
//        c.setMail("4444@qq.com");
//        c.setName("张三");
//        c.setNatives("九江");
//        c.setPhone("120");
//        c.setSex(Candidate.SEX_MALE);
//        c.setYears(12);
//        c.setPrepare(Candidate.PREPARE7);
//        c.setFlag_start(true);
//        DaoUpdateResult res = CandidateDAO.open(conn,2L,true);
//
//        if(res.success){
//            System.out.println("更新成功");
//        }else {
//            System.out.println("更新失败");
//        }
//
//        RecruitConnUtil.closeConnection(conn);
//    }
    /**
     * 添加求职者信息
     * @param conn 数据库连接
     * @param c 求职者信息
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult insert(Connection conn, Candidate c){
        String sql = "insert into candidate(id,modtime)values(?,now())";
        Object []params = {c.getId()};
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 修改求职者信息
     * @param conn 数据库连接
     * @param c 求职者信息
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult update(Connection conn, Candidate c){
        String sql = "update candidate set name=?,sex=?,birthday=?,native=?,address=?,degree=?,phone=?,years=?,mail=?,prepare=?,state=? where id=?";
        Object []params = {c.getName(),c.getSex(), c.getBirthday(), c.getNatives(),c.getAddress(),c.getDegree(),c.getPhone(),c.getYears(),c.getMail(),c.getPrepare(),c.getState(),c.getId()};
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 开启/关闭简历
     * @param conn 数据库连接
     * @param cid 求职者编号
     * @param direction true-开启；false-关闭
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult open(Connection conn, long cid, boolean direction){
//        String sql = "update candidate set flag=?(flag,0) where id=?";
//        通过将flag的第0位的状态置为1或0来表示简历的开启和关闭
        String sql = direction?"update candidate set flag=bit_set(flag,0) where id=?":"update candidate set flag=bit_reset(flag,0) where id=?";
        Object []params = {direction?"bit_set":"bit_reset",cid};
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 刷新简历
     * @param conn 数据库连接
     * @param cid 求职者编号
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult fresh(Connection conn, long cid){
        String sql ="update candidate set modtime=now() where id=?";
        Object []params = {cid};
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 审核简历
     * @param conn 数据库连接
     * @param cid 求职者编号
     * @param direction  是否通过
     * @param reason 审核不通过的原因
     * @return 审核结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult check(Connection conn, long cid, boolean direction,String reason){
        String sql ="update candidate set status=?,reason=? where id=?";
        Object []params = {direction?Candidate.STATUS_CHECKED:Candidate.STATUS_MODIFING,reason,cid};
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 修改求职者积分
     * @param conn 数据库连接
     * @param cid 求职者信息
     * @param value 积分改变数值（充值为正值，消耗为负值）
     * @return 修改结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult updateScore(Connection conn,long cid,int value){
        String sql="update candidate set score=score+? where id=? and score> abs(?)";
        Object []params = {value,cid,value};
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 查询简历
     * @param conn 数据库连接
     * @param cid 求职者编号
     * @return 查询结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoQueryResult get(Connection conn, long cid){
        QueryConditions conditions = new QueryConditions();
        conditions.add("id","=",cid);
        return  DbUtil.get(conn,"candidate",conditions,Candidate.class);
    }

    /**
    * 检索简历
    * @param conn 数据库连接
    * @return 检索结果，格式："{success:true,msg:"",effects:1}"
    */
    public static DaoQueryListResult gets(Connection conn, QueryParameter param){
        return DbUtil.getList(conn,"candidate",param,Candidate.class);
    }
}
