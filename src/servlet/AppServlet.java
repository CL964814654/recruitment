package servlet;

import bean.App;
import bean.Interview;
import bean.Job;
import bean.Message;
import com.alibaba.fastjson.JSONObject;
import dao.AppDAO;
import dao.InterviewDAO;
import dao.JobDAO;
import dao.MessageDAO;
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
import java.sql.Date;
import java.sql.Timestamp;


@WebServlet(name="JobServlet",urlPatterns = "/app")//浏览器发出请求的地址
public class AppServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String result = "";
        Connection conn = RecruitConnUtil.getConnection();

        String op = request.getParameter("op");
        switch (op) {
            case "delivery"://申请投递
                result = delivery(conn,request);
                break;
            case "invitation"://邀请面试
                result = invitation(conn,request);
                break;
            case "accept"://接受/拒绝面试
                result = accept(conn,request);
                break;
            case "employ"://录用/不录用
                result = employ(conn,request);
                break;
        }
        RecruitConnUtil.closeConnection(conn);

        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
    //申请投递
    private String delivery(Connection conn,HttpServletRequest request) {
        long cid =Long.parseLong(request.getParameter("cid"));
        long jid = Long.parseLong(request.getParameter("jid"));
        App app = new App();
        app.setCid(cid);
        app.setJid(jid);
        DaoUpdateResult res= AppDAO.insert(conn,app);
        return JSONObject.toJSONString(res);
    }

    //邀请面试
    private String invitation(Connection conn,HttpServletRequest request) {
        long cid =Long.parseLong(request.getParameter("cid"));
        long jid = Long.parseLong(request.getParameter("jid"));
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        Timestamp time = Timestamp.valueOf(request.getParameter("time"));

        Interview interview = new Interview();
        interview.setCid(cid);
        interview.setJid(jid);
        interview.setPhone(phone);
        interview.setAddress(address);
        interview.setTime(time);

        DaoUpdateResult res= InterviewDAO.insert(conn,interview);
        return JSONObject.toJSONString(res);
    }

    //接受/拒绝面试
    private String accept(Connection conn,HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        boolean direction = Boolean.parseBoolean(request.getParameter("direction"));
        String reason = request.getParameter("reason");

        DaoQueryResult res1 = InterviewDAO.get(conn,id);
        long jid = ((Interview) res1.data).getJid();
        DaoQueryResult res2 = JobDAO.get(conn,jid);
        long eid = ((Job) res2.data).getEid();

        //生成消息明细
        Message message = new Message();
        message.setReceiver(eid);//接收者账号
        message.setTitle("xxx");//标题
        message.setContent("xxx");//内容

        RecruitConnUtil.closeAutoCommit(conn);//关闭自动提交

        DaoUpdateResult res3 = InterviewDAO.accept(conn,id ,direction,reason);
        DaoUpdateResult res4 = MessageDAO.insert(conn, message);

        //事务处理
        if(res3.success && res4.success){
            RecruitConnUtil.commit(conn);
            return   JSONObject.toJSONString(res3);
        }else{
            RecruitConnUtil.rollback(conn);
            JSONObject json = new JSONObject();
            json.put("success",false);
            json.put("msg","失败");
            return json.toJSONString();
        }
    }

    //录用/不录用
    private String employ(Connection conn,HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        boolean direction = Boolean.parseBoolean(request.getParameter("direction"));
        String reason = request.getParameter("reason");

        DaoQueryResult res1 = InterviewDAO.get(conn,id);
        long cid = ((Interview) res1.data).getCid();

        //生成消息明细
        Message message = new Message();
        message.setReceiver(cid);//接收者账号
        message.setTitle("xxx");//标题
        message.setContent("xxx");//内容

        RecruitConnUtil.closeAutoCommit(conn);//关闭自动提交

        DaoUpdateResult res2 = InterviewDAO.employee(conn,id ,direction,reason);
        DaoUpdateResult res3 = MessageDAO.insert(conn, message);

        //事务处理
        if(res2.success && res3.success){
            RecruitConnUtil.commit(conn);
            return   JSONObject.toJSONString(res2);
        }else{
            RecruitConnUtil.rollback(conn);
            JSONObject json = new JSONObject();
            json.put("success",false);
            json.put("msg","操作失败");
            return json.toJSONString();
        }
    }
}
