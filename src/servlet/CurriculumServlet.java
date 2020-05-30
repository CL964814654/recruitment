package servlet;

import bean.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.HttpRequest;
import dao.*;
import database.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.annotation.WebServlet;

@WebServlet(name="CurriculumServlet",urlPatterns = "/curriculum")//浏览器发出请求的地址
public class CurriculumServlet extends HttpServlet {
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
            case "updateCandidate"://修改求职者信息
                result = updateCandidate(conn,request);
                break;
            case "getCandidate"://修改求职者信息
                result = getCandidate(conn,request);
                break;
            case "insertIntention"://添加求职意向信息
                result = insertIntention(conn,request);
                break;
            case "deleteIntention"://删除求职意向信息
                result = deleteIntention(conn,request);
                break;
            case "updateIntention"://修改求职意向信息
                result = updateIntention(conn,request);
                break;
            case "getIntention"://获取求职意向信息
                result = getIntention(conn,request);
                break;
            case "insertEducation"://添加教育经历信息
                result = insertEducation(conn,request);
                break;
            case "deleteEducation"://删除教育经历信息
                result = deleteEducation(conn,request);
                break;
            case "updateEducation"://修改教育经历信息
                result = updateEducation(conn,request);
                break;
            case "getEducation"://获取教育经历信息
                result = getEducation(conn,request);
                break;
            case "insertWork"://添加工作经历信息
                result = insertWork(conn,request);
                break;
            case "deleteWork"://删除工作经历信息
                result = deleteWork(conn,request);
                break;
            case "updateWork"://修改工作经历信息
                result = updateWork(conn,request);
                break;
            case "getWork"://获取工作经历信息
                result = getWork(conn,request);
                break;
            case "updateSkill"://修改技能
                result = updateSkill(conn,request);
                break;
            case "start"://开启/关闭简历
                result = start(conn,request);
                break;
            case "fresh"://刷新简历
                result = fresh(conn,request);
                break;
            case "getCurriculum"://浏览简历
                result = getCurriculum(conn,request);
                break;
            case "check"://审核简历
                result = check(conn,request);
                break;
            case "unseal"://解封简历
                result = unseal(conn,request);
                break;
            case "search"://检索简历
                result = search(conn,request);
                break;
            case "getList"://获取列表
                result = getList(conn);
                break;
            case "concern"://关注简历
                result = concern(conn,request);
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

    //关注简历/取消关注
    private String concern(Connection conn, HttpServletRequest request) {
        boolean direction = Boolean.parseBoolean(request.getParameter("direction"));
        long cid = Long.parseLong(request.getParameter("cid"));
        //eid暂时写死
//        long eid = Long.parseLong(request.getParameter("eid"));
        if(direction) {
            DaoExistResult res1 = ConcernDAO.exist(conn, cid, 3);
            if (!res1.exist) {//如果该简历不存在，可以进行关注
                DaoUpdateResult res2 = ConcernDAO.insert(conn, cid, 3);
                return JSONObject.toJSONString(res2);
            }else{
                JSONObject json = new JSONObject();
                json.put("success",false);
                json.put("msg","不能重复关注");
                return  json.toJSONString();
            }
        }else{
            DaoUpdateResult res1 = ConcernDAO.delete(conn, cid, 3);
            return JSONObject.toJSONString(res1);
        }
    }

    //获取求职者列表
    private String getList(Connection conn) {
        QueryParameter parameter = new QueryParameter();
        DaoQueryListResult res = CandidateDAO.gets(conn, parameter);
        return JSONObject.toJSONString(res);
    }

    //修改求职者信息
    private String updateCandidate(Connection conn,HttpServletRequest request) {
        Candidate candidate = JSON.parseObject(request.getParameter("candidate"), Candidate.class);
        DaoUpdateResult res = CandidateDAO.update(conn, candidate);

        return JSONObject.toJSONString(res);
    }

    //获取求职者基本信息
    private String getCandidate(Connection conn,HttpServletRequest request){
        long cid = Long.parseLong(request.getParameter("cid"));
        DaoQueryResult res = CandidateDAO.get(conn,cid);
        return  JSONObject.toJSONString(res);
    }

    //添加求职意向信息
    private String insertIntention(Connection conn,HttpServletRequest request) {
        Intention intention = JSON.parseObject(request.getParameter("intention"), Intention.class);
        DaoUpdateResult res = IntentionDAO.insert(conn, intention);
        return JSONObject.toJSONString(res);
    }

    //删除求职意向信息
    private String deleteIntention(Connection conn,HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        DaoUpdateResult res = IntentionDAO.delete(conn, id);

        return JSONObject.toJSONString(res);
    }

    //修改求职意向信息
    private String updateIntention(Connection conn,HttpServletRequest request) {
        Intention intention = JSON.parseObject(request.getParameter("intention"), Intention.class);
        DaoUpdateResult res = IntentionDAO.update(conn, intention);

        return JSONObject.toJSONString(res);
    }

    //获取求职意向信息
    private String getIntention(Connection conn,HttpServletRequest request) {
        long cid = Long.parseLong(request.getParameter("cid"));
        DaoQueryResult res = IntentionDAO.get(conn, cid);

        return JSONObject.toJSONString(res);
    }

    //添加教育信息
    private String insertEducation(Connection conn,HttpServletRequest request) {
        Education education = JSON.parseObject(request.getParameter("education"), Education.class);
        DaoUpdateResult res = EducationDAO.insert(conn, education);
        return JSONObject.toJSONString(res);
    }

    //删除教育信息
    private String deleteEducation(Connection conn,HttpServletRequest request) {
        long eid = Long.parseLong(request.getParameter("eid"));
        DaoUpdateResult res = EducationDAO.delete(conn, eid);
        return JSONObject.toJSONString(res);
    }

    //修改教育信息
    private String updateEducation(Connection conn,HttpServletRequest request) {
        Education education = JSON.parseObject(request.getParameter("education"), Education.class);
        DaoUpdateResult res = EducationDAO.update(conn, education);
        RecruitConnUtil.closeConnection(conn);
        return JSONObject.toJSONString(res);
    }

    //获取教育信息
    private String getEducation(Connection conn,HttpServletRequest request) {
        long cid = Long.parseLong(request.getParameter("cid"));
        DaoQueryResult res = EducationDAO.get(conn, cid);
        return JSONObject.toJSONString(res);
    }

    //添加工作经历信息
    private String insertWork(Connection conn,HttpServletRequest request) {
        Work work = JSON.parseObject(request.getParameter("work"), Work.class);
        DaoUpdateResult res = WorkDAO.insert(conn, work);
        return JSONObject.toJSONString(res);
    }

    //删除工作经历信息
    private String deleteWork(Connection conn,HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        DaoUpdateResult res = WorkDAO.delete(conn, id);
        return JSONObject.toJSONString(res);
    }

    //修改工作经历信息
    private String updateWork(Connection conn,HttpServletRequest request) {
        Work work = JSON.parseObject(request.getParameter("work"), Work.class);
        DaoUpdateResult res = WorkDAO.update(conn, work);
        return JSONObject.toJSONString(res);
    }

    //获取工作经历信息
    private String getWork(Connection conn,HttpServletRequest request) {
        long cid = Long.parseLong(request.getParameter("cid"));
        DaoQueryResult res = WorkDAO.get(conn,cid);
        return JSONObject.toJSONString(res);

    }

    //修改技能
    private String updateSkill(Connection conn,HttpServletRequest request) {
        Skill skill = JSON.parseObject(request.getParameter("skill"), Skill.class);
        DaoUpdateResult res = SkillDAO.update(conn, skill);

        return JSONObject.toJSONString(res);
    }

    //简历打开/关闭
    private String start(Connection conn,HttpServletRequest request) {
        long cid = Long.parseLong(request.getParameter("cid"));
        boolean direction = Boolean.parseBoolean(request.getParameter("direction"));

        DaoUpdateResult res = CandidateDAO.open(conn, cid,direction);
        return  JSONObject.toJSONString(res);

    }

    //刷新简历
    private String fresh(Connection conn,HttpServletRequest request) {
        long cid = Long.parseLong(request.getParameter("cid"));

        //积分规则暂时写死
        int value = -5;
        //生成积分明细
        Score score = new Score();
        score.setId(cid);
        score.setType(Score.TYPE_PAY);
        score.setQuantity(value);
        score.setComment("刷新");//备注

        RecruitConnUtil.closeAutoCommit(conn);//关闭自动提交

        DaoUpdateResult res1 = CandidateDAO.fresh(conn, cid);
        DaoUpdateResult res2 = CandidateDAO.updateScore(conn, cid, value);
        DaoUpdateResult res3 = ScoreDAO.insert(conn, score);

        //事务处理
        if (res1.success && res2.success && res3.success) {
            RecruitConnUtil.commit(conn);
            return JSONObject.toJSONString(res1);
        } else {
            RecruitConnUtil.rollback(conn);
            JSONObject json = new JSONObject();
            json.put("success", false);
            json.put("msg", "刷新失败，请确定是否有足够积分");
            return json.toJSONString();
        }

    }

    //浏览简历
    private String getCurriculum(Connection conn,HttpServletRequest request) {
        long eid = Long.parseLong(request.getParameter("eid"));
        long cid = Long.parseLong(request.getParameter("cid"));
        JSONObject json = new JSONObject();
        Candidate candidate = (Candidate) CandidateDAO.get(conn, cid).data;
        if (AppDAO.exist(conn, cid, eid) || ConcernDAO.exist(conn, cid, eid).exist){
            Object work = WorkDAO.get(conn, cid).data;
            Object education = EducationDAO.get(conn, cid).data;
            Object skill = SkillDAO.get(conn, cid).data;
            Object intention = IntentionDAO.get(conn, cid).data;
            json.put("candidate",candidate);
            json.put("education",education);
            json.put("skill",skill);
            json.put("intention",intention);
        }else {
            candidate.setPhone("******");
            candidate.setMail("******");
            json.put("candidate",candidate);
        }
        return json.toString();
    }

    //审核简历
    private String check(Connection conn,HttpServletRequest request) {
        long cid = Long.parseLong(request.getParameter("cid") );
        boolean direction = Boolean.parseBoolean(request.getParameter("direction"));
        String reason = request.getParameter("reason");

        //生成消息明细
        Message message = new Message();
        message.setReceiver(cid);//接收者账号
        message.setTitle("xxx");//标题
        message.setContent("xxx");//内容

        RecruitConnUtil.closeAutoCommit(conn);//关闭自动提交

        DaoUpdateResult res1 = CandidateDAO.check(conn,cid,direction,reason);
        DaoUpdateResult res2 = MessageDAO.insert(conn, message);

        //事务处理
        if(res1.success && res2.success){
            RecruitConnUtil.commit(conn);
            return   JSONObject.toJSONString(res1);
        }else{
            RecruitConnUtil.rollback(conn);

            JSONObject json = new JSONObject();
            json.put("success",false);
            json.put("msg","审核失败");
            return json.toJSONString();
        }

    }

    //解封简历
    private String unseal(Connection conn,HttpServletRequest request) {
        long cid = Long.parseLong(request.getParameter("cid"));
        //long eid = Long.parseLong(request.getParameter("eid"));eid暂时写死


        //生成消息明细
        Message message = new Message();
        message.setReceiver(cid);//接收者账号
        message.setTitle("xxx");//标题
        message.setContent("xxx");//内容

//        扣分规则日后完善
//        Score score = new Score();
//        score.setId(3L);//账号编号
//        score.setType(Score.TYPE_PAY);//支出
//        score.setQuantity(value);
//        score.setComment("职位刷新");//备注

        RecruitConnUtil.closeAutoCommit(conn);//关闭自动提交
        //后续还要添加一条积分消耗操作
        DaoUpdateResult res2 = ConcernDAO.insert(conn,cid,3L);
        DaoUpdateResult res3 = MessageDAO.insert(conn, message);

        //事务处理
        if(res2.success && res3.success){
            RecruitConnUtil.commit(conn);
            return JSONObject.toJSONString(res2);
        }else{
            RecruitConnUtil.rollback(conn);
            JSONObject json = new JSONObject();
            json.put("success",false);
            json.put("msg","解封失败，请确定是否有足够积分");
            return json.toJSONString();
        }

    }

    //检索简历
    private String search(Connection conn,HttpServletRequest request) {
       return "";
    }

}
