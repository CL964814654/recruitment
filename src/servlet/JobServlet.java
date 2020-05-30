package servlet;

import bean.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.*;
import database.DaoExistResult;
import database.DaoQueryResult;
import database.DaoUpdateResult;
import database.RecruitConnUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;


@WebServlet(name="JobServlet",urlPatterns = "/job")//浏览器发出请求的地址
public class JobServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String result;
        Connection conn = RecruitConnUtil.getConnection();

        String op = request.getParameter("op");
        switch (op) {
            case "updateJob"://编辑职位
                result = updateJob(conn,request);
                break;
            case "insertJob"://创建职位
                result = insertJob(conn,request);
                break;
            case "freshJob"://刷新职位
                result = freshJob(conn,request);
                break;
            case "deleteJob"://删除职位
                result = deleteJob(conn,request);
                break;
            case "checkJob"://审核职位
                result = checkJob(conn,request);
                break;
            case "searchJob"://检索职位
                result = searchJob(conn,request);
                break;
            case "get"://浏览职位
                result = get(conn,request);
                break;
            case "favoriteJob"://收藏职位
                result = favoriteJob(conn,request);
                break;
            case "roof"://置顶职位
                result = roof(conn,request);
                break;
            case "puton"://上架/下架职位
                result = puton(conn,request);
                break;
            default:
                result = "test";
        }
        RecruitConnUtil.closeConnection(conn);

        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
    //创建职位
    private String insertJob(Connection conn, HttpServletRequest request) {
        Job job = JSON.parseObject(request.getParameter("job"), Job.class);
        DaoUpdateResult res = JobDAO.insert(conn,job);

        return JSONObject.toJSONString(res);
    }

    //删除职位
    private String deleteJob(Connection conn, HttpServletRequest request) {
        long jid = Long.parseLong(request.getParameter("jid"));
        DaoUpdateResult res = JobDAO.updateStatus(conn,jid,Job.STATUS_DELETED);

        return JSONObject.toJSONString(res);
    }

    //编辑职位
    private String updateJob(Connection conn, HttpServletRequest request) {
        Job job = JSON.parseObject(request.getParameter("job"), Job.class);
        DaoUpdateResult res = JobDAO.update(conn,job);

        return JSONObject.toJSONString(res);
    }

    //刷新职位
    private String freshJob(Connection conn, HttpServletRequest request) {
        long jid = Long.parseLong(request.getParameter("jid") );

        DaoQueryResult res1 = JobDAO.get(conn,jid);
        long eid = ((Job)res1.data).getEid();
        //积分规则暂时写死
        int value = -5;

        //生成积分明细
        Score score = new Score();
        score.setId(eid);//账号编号
        score.setType(Score.TYPE_PAY);//支出
        score.setQuantity(value);
        score.setComment("职位刷新");//备注

        RecruitConnUtil.closeAutoCommit(conn);//关闭自动提交

        //刷新、更新企业积分、增加一条积分明细，这三条是一起执行的
        DaoUpdateResult res2 = JobDAO.fresh(conn,jid);
        DaoUpdateResult res3 = EnterpriseDAO.updateScore(conn, eid,value);
        DaoUpdateResult res4 = ScoreDAO.insert(conn,score);

        //事务处理（一旦有其中任何一条不满足条件，就进行撤销操作）
        if(res2.success && res3.success && res4.success){
            RecruitConnUtil.commit(conn);
            return JSONObject.toJSONString(res2);
        }else{
            RecruitConnUtil.rollback(conn);
            JSONObject json = new JSONObject();
            json.put("success",false);
            json.put("msg","刷新失败，请确定是否有足够积分");
            return json.toJSONString();
        }
    }

    //审核职位
    private String checkJob(Connection conn, HttpServletRequest request) {
        long jid = Long.parseLong(request.getParameter("jid") );
        boolean direction = Boolean.parseBoolean(request.getParameter("direction"));
        String reason = request.getParameter("reason");

        DaoQueryResult res1 = JobDAO.get(conn,jid);
        long receiver = ((Job) res1.data).getEid();

        //生成消息明细
        Message message = new Message();
        message.setReceiver(receiver);//接收者账号
        message.setTitle("xxx");//标题
        message.setContent("xxx");//内容

        RecruitConnUtil.closeAutoCommit(conn);//关闭自动提交

        DaoUpdateResult res2 = JobDAO.check(conn,jid,direction,reason);
        DaoUpdateResult res3 = MessageDAO.insert(conn, message);

        //事务处理
        if(res2.success && res3.success){
            RecruitConnUtil.commit(conn);
            return   JSONObject.toJSONString(res2);
        }else{
            RecruitConnUtil.rollback(conn);

            JSONObject json = new JSONObject();
            json.put("success",false);
            json.put("msg","审核失败");
            return json.toJSONString();
        }
    }

    //检索职位
    private String searchJob(Connection conn, HttpServletRequest request) {
        return "";
    }

    //浏览职位
    private String get(Connection conn, HttpServletRequest request) {
        long jid = Long.parseLong(request.getParameter("jid"));
        DaoQueryResult res = JobDAO.get(conn,jid);
        return JSONObject.toJSONString(res);
    }

    //收藏职位
    private String favoriteJob(Connection conn, HttpServletRequest request) {
        boolean direction = Boolean.parseBoolean(request.getParameter("direction"));
        long cid = Long.parseLong(request.getParameter("cid"));
        long jid = Long.parseLong(request.getParameter("jid"));

        if(direction) {
            DaoExistResult res1 = FavoriteDAO.exist(conn, cid, jid);
            if (!res1.exist) {//如果该职位不存在，可以进行收藏
                DaoUpdateResult res2 = FavoriteDAO.insert(conn, cid, jid);
                return JSONObject.toJSONString(res2);
            }else{
                JSONObject json = new JSONObject();
                json.put("success",false);
                json.put("msg","不能重复收藏");
                return  json.toJSONString();
            }
        }else{
            DaoUpdateResult res1 = FavoriteDAO.delete(conn,jid,cid);
            return JSONObject.toJSONString(res1);
        }
    }

    //置顶职位
    private String roof(Connection conn, HttpServletRequest request) {
        Roof roof = JSON.parseObject(request.getParameter("roof"), Roof.class);

        //判断置顶位置相应时段是否被占用，占用则不能完成此次置顶
        DaoExistResult res1 = RoofDAO.exist(conn, roof.getPos(), roof.getStart(), roof.getEnd());
        if(res1.exist){
            JSONObject json = new JSONObject();
            json.put("success", false);
            json.put("msg", "置顶失败，请重新选择时间段");
            return json.toJSONString();
        }

        //通过职位编号得到企业编号
        DaoQueryResult res2 = JobDAO.get(conn, roof.getJid());
        long eid = ((Job) res2.data).getEid();
        //积分规则暂时写死
        int value = -5;
        //生成积分明细
        Score score = new Score();
        score.setId(eid);
        score.setType(Score.TYPE_PAY);
        score.setQuantity(value);
        score.setComment("置顶");//备注

        RecruitConnUtil.closeAutoCommit(conn);//关闭自动提交

        DaoUpdateResult res3 = RoofDAO.insert(conn,roof);
        DaoUpdateResult res4 = EnterpriseDAO.updateScore(conn, eid, value);
        DaoUpdateResult res5 = ScoreDAO.insert(conn, score);

        //事务处理
        if (res3.success && res4.success && res5.success && res4.effects>0) {
            RecruitConnUtil.commit(conn);
            return JSONObject.toJSONString(res3);
        } else {
            RecruitConnUtil.rollback(conn);
            JSONObject json = new JSONObject();
            json.put("success", false);
            json.put("msg", "置顶失败，请确定是否有足够积分");
            return json.toJSONString();
        }
    }

    //上架/下架职位
    private String puton(Connection conn, HttpServletRequest request) {
        long jid = Long.parseLong(request.getParameter("jid") );
        boolean direction = Boolean.parseBoolean(request.getParameter("direction"));
        byte status = direction?Job.STATUS_USED:Job.STATUS_REMOVED;
        DaoUpdateResult res = JobDAO.updateStatus(conn,jid,status);
        return JSONObject.toJSONString(res);
    }

}
