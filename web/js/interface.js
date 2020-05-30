/**
 * 后台访问接口
 *
 */

//职位相关接口
var InterfaceJob = function () {
    var url = "/recruitment/job";//servlet的url地址

    /**
     * 获取职位列表（先返回所有的职位，筛选条件以后再说）
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.getList = function(reqType,success,fail){
        var para = {op: "getList"};
        access(url,para,reqType,success,fail);
    };
    /**
     * 添加职位
     * @param job 职位信息
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.insertJob = function (job,reqType,success,fail) {
        var para = {op: "insertJob",job:JSON.stringify(job)};
        access(url,para,reqType,success,fail);
    };
    /**
     * 刷新职位
     * @param jid 职位编号
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.freshJob = function (jid,reqType,success,fail) {
        var para = {op:"freshJob",jid:jid};
        access(url,para,reqType,success,fail);
    };
    /**
     * 置顶职位
     * @param roof 职位置顶
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.roof = function (roof,reqType,success,fail) {
        var para = {op:"roof",roof:JSON.stringify(roof)};
        access(url,para,reqType,success,fail);
    };
    /**
     * 上/ 下架职位
     * @param jid 职位编号
     * @param direction 上/下架指令，true-上架；false-下架
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.puton = function (jid,direction,reqType,success,fail) {
        var para = {op:"puton",jid:jid,direction:direction};
        access(url,para,reqType,success,fail);
    };
    /**
     * 审核职位
     * @param jid 职位编号
     * @param direction 上/下架指令，true-上架；false-下架
     * @param reason 不通过原因
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.checkJob = function (jid,direction,reason,reqType,success,fail) {
        var para = {op:"checkJob",jid:jid,direction:direction,reason:reason};
        access(url,para,reqType,success,fail);
    };
    /**
     * 获取职位信息
     * @param jid 职位编号
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.get = function (jid,reqType,success,fail) {
        var para = {op:"get",jid:jid};
        access(url,para,reqType,success,fail);
    };
    /**
     * 收藏/取消收藏职位
     * @param jid 职位编号
     * @param cid 求职者编号
     * @param direction true-收藏；false-取消收藏
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.favoriteJob = function (jid,cid,direction,reqType,success,fail) {
        var para = {op:"favoriteJob",jid:jid,cid:cid,direction:direction};
        access(url,para,reqType,success,fail);
    };
    /**
     * 修改职位
     * @param job  职位信息
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.updateJob = function (job,reqType,success,fail) {
        var para = {op:"updateJob",job:JSON.stringify(job)};
        access(url,para,reqType,success,fail);
    };
    /**
     * 删除职位
     * @param jid  职位编号
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.deleteJob = function (jid,reqType,success,fail) {
        var para = {op:"deleteJob",jid:jid};
        access(url,para,reqType,success,fail);
    };
};

//简历相关接口
var InterfaceCurriculum = function () {
    var url = "/recruitment/curriculum";//servlet的url地址

    /**
     * 获取简历列表（先返回所有的简历，筛选条件以后再说）
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.getList = function(reqType,success,fail){
        var para = {op: "getList"};
        access(url,para,reqType,success,fail);
    };
    /**
     * 审核简历
     * @param cid 求职者编号
     * @param direction true-审核通过
     * @param reason 审核不通过的原因
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.check = function (cid,direction,reason,reqType,success,fail) {
        var para = {op: "check",direction:direction,reason:reason};
        access(url,para,reqType,success,fail);
    };
    /**
     * 解封简历
     * @param cid 求职者编号
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.unseal = function (cid,reqType,success,fail) {
        var para = {op:"unseal",cid:cid};
        access(url,para,reqType,success,fail);
    };
    /**
     * 浏览简历
     * @param cid 求职者编号
     * @param eid 企业编号
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.getCurriculum = function (cid,eid,reqType,success,fail) {
        var para = {op:"getCurriculum",cid:cid,eid:eid};
        access(url,para,reqType,success,fail);
    };
    /**
     * 刷新简历
     * @param cid 求职者编号
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.fresh = function (cid,reqType,success,fail) {
        var para = {op:"fresh",cid:cid};
        access(url,para,reqType,success,fail);
    };
    /**
     * 简历打开/关闭
     * @param cid 求职者编号
     * @param direction true-打开；false-关闭
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.start = function (cid,direction,reqType,success,fail) {
        var para = {op:"start",cid:cid,direction:direction};
        access(url,para,reqType,success,fail);
    };
    /**
     * 添加工作经历
     * @param work 工作经历信息
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.insertWork = function (work,reqType,success,fail) {
        var para = {op:"insertWork",work:JSON.stringify(work)};
        access(url,para,reqType,success,fail);
    };
    /**
     * 获取教育经历
     * @param cid 求职者编号
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.getEducation = function (cid,reqType,success,fail) {
        var para = {op:"getEducation",cid:cid};
        access(url,para,reqType,success,fail);
    };
    /**
     * 获取工作经历
     * @param cid 求职者编号
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.getWork = function (cid,reqType,success,fail) {
        var para = {op:"getWork",cid:cid};
        access(url,para,reqType,success,fail);
    };
    /**
     * 修改教育经历
     * @param education 教育经历信息
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.updateEducation = function (education,reqType,success,fail) {
        var para = {op:"updateEducation",education:JSON.stringify(education)};
        access(url,para,reqType,success,fail);
    };
    /**
     * 修改工作经历
     * @param work 工作经历信息
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.updateWork = function (work,reqType,success,fail) {
        var para = {op:"updateWork",work:JSON.stringify(work)};
        access(url,para,reqType,success,fail);
    };
    /**
     * 修改技能
     * @param skill 技能信息
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.updateSkill = function (skill,reqType,success,fail) {
        var para = {op:"updateSkill",skill:JSON.stringify(skill)};
        access(url,para,reqType,success,fail);
    };
    /**
     * 删除教育经历
     * @param eid 教育经历编号
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.deleteEducation = function (eid,reqType,success,fail) {
        var para = {op:"deleteEducation",eid:eid};
        access(url,para,reqType,success,fail);
    };
    /**
     * 删除工作经历
     * @param wid 工作经历编号
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.deleteWork = function (wid,reqType,success,fail) {
        var para = {op:"deleteWork",wid:wid};
        access(url,para,reqType,success,fail);
    };
    /**
     * 添加教育经历
     * @param education 教育经历信息
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.insertEducation = function (education,reqType,success,fail) {
        var para = {op:"insertEducation",education:JSON.stringify(education)};
        access(url,para,reqType,success,fail);
    };
    /**
     * 获取求职意向
     * @param cid 求职者编号
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.getIntention = function (cid,reqType,success,fail) {
        var para = {op:"getIntention",cid:cid};
        access(url,para,reqType,success,fail);
    };
    /**
     * 修改求职意向
     * @param intention 求职意向信息
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.updateIntention = function (intention,reqType,success,fail) {
        var para = {op:"updateIntention",intention:JSON.stringify(intention)};
        access(url,para,reqType,success,fail);
    };
    /**
     * 删除指定求职意向
     * @param id 求职意向编号
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.deleteIntention = function (id,reqType,success,fail) {
        var para = {op:"deleteIntention",id:id};
        access(url,para,reqType,success,fail);
    };
    /**
     * 添加求职意向
     * @param intention 求职意向信息
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.insertIntention = function (intention,reqType,success,fail) {
        var para = {op:"insertIntention",intention:JSON.stringify(intention)};
        access(url,para,reqType,success,fail);
    };
    /**
     * 修改求职者信息
     * @param candidate 求职者信息
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.updateCandidate = function (candidate,reqType,success,fail) {
        var para = {op:"updateCandidate",candidate:JSON.stringify(candidate)};
        access(url,para,reqType,success,fail);
    };
    /**
     * 获取求职者信息
     * @param cid 求职者编号
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.getCandidate = function (cid,reqType,success,fail) {
        var para = {op:"getCandidate",cid:cid};
        access(url,para,reqType,success,fail);
    };
    /**
     * 取消收藏简历
     * @param cid 简历编号
     * @param direction
     * @param reqType
     * @param success
     * @param fail
     */
    this.concern = function (cid,direction,reqType,success,fail) {
        var para = {op:"concern",cid:cid,direction:direction};
        access(url,para,reqType,success,fail);
    };
};

//求职相关接口
var InterfaceApp = function () {
    var url = "/recruitment/app";//servlet的url地址

    /**
     * 申请投递
     * @param cid 求职者编号
     * @param jid 职位编号
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.delivery = function (cid,jid,reqType,success,fail) {
        var para = {op:"delivery",cid:cid,jid:jid};
        access(url,para,reqType,success,fail);
    };
    /**
     * 邀请面试
     * @param cid 求职者编号
     * @param jid 职位编号
     * @param phone 面试联系电话
     * @param address 面试地址
     * @param time 面试时间
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.invitation = function (cid,jid,phone,address,time,reqType,success,fail) {
        var para = {op:"invitation",cid:cid,jid:jid,phone:phone,address:address,time:time};
        access(url,para,reqType,success,fail);
    };
    /**
     * 接受/拒绝面试
     * @param id 面试编号
     * @param direction 接受-true;拒绝-false
     * @param reason 拒绝原因
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.accept = function (id,direction,reason,reqType,success,fail) {
        var para = {op:"accept",id:id,direction:direction,reason:reason};
        access(url,para,reqType,success,fail);
    };
    /**
     * 录用/不录用
     * @param id 面试编号
     * @param direction 录用-true;不录用-false
     * @param reason 拒绝或不录用原因
     * @param reqType 请求方式，0-get方式；非0表示post方式
     * @param success 成功后的回调函数
     * @param fail 失败的回调函数
     */
    this.employ = function (id,direction,reason,reqType,success,fail) {
        var para = {op:"employ",id:id,direction:direction,reason:reason};
        access(url,para,reqType,success,fail);
    };
};

var iJob = new InterfaceJob();
var iCurriculum = new InterfaceCurriculum();
var iApp = new InterfaceApp();

/**
 * 内部访问函数
 * @param url 接口地址
 * @param para 接口参数
 * @param reqType 请求方式，0-get方式；非0表示post方式
 * @param success 成功回调函数
 * @param fail 失败回调函数
 */
function access(url,para,reqType,success,fail) {
    $.ajax({
        url: url,
        data:para,
        type: reqType==0?"get":"post",
        dataType:"json",
        success:function(data,status) {
            if(!data.success) {
                if(fail) {
                    fail(data);
                }else{
                    $.alert(data.msg);
                }
            }else{
                if(success) {
                    success(data);
                }
            }
        },
        error:function (data) {
            if(data.status==403){
                alert("会话已终止，请重新登录");
                window.location.href = data.responseJSON.url;
            }
        }
    });
}
