package wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import comm.HttpUtil;
import comm.SignatureUtil;
import comm.XmlUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WxAPI {
    //测试账号
    /*public static final String appID = "wxa9eeb3b96562e512";
    private static final String appsecret = "5a3f75fa23143e017a1d33d220c85a2c";*/

    //正式公众号账号
    public static final String appID = "wxccc6cb9620d9e75b";
    private static final String appsecret = "f6ae9df39fd3b775dab2df891e0550b6";

    public static final String mchID = "1537356571";//商户号
    public static final String API_KEY = "51quzhengbaoquzhengbaoquzhengbao";//支付API秘钥
    //public static String SANDBOX_API_KEY = "662d8bf7622a61fcfffd2533c4f36065";//沙箱支付API秘钥，测试用



    public static String token = "";

    //获取网页授权access_token的接口
    private static final String INTERFACE_OAUTH_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    //获取token的接口
    private static final String INTERFACE_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    //获取ticket的接口
    private static final String INTERFACE_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
    //获取素材的接口
    private static final String INTERFACE_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
    //支付接口
    private static final String INTERFACE_PAY = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //订单查询接口
    private static final String INTERFACE_ORDER_PAY = "https://api.mch.weixin.qq.com/pay/orderquery";
    //下载对账单
    private static final String INTERFACE_DOWNLOAD_BILL = "https://api.mch.weixin.qq.com/pay/downloadbill";
    //验收仿真测试系统的API验签密钥获取接口
    private static final String GET_SANDBOX_KEY = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
    //发送模板消息
    public static String INTERFACE_SEND_MSG = "https://api.weixin.qq.com/cgi-bin/message/template/send";
    //生成二维码
    public static String INTERFACE_CREATE_QR = "https://api.weixin.qq.com/cgi-bin/qrcode/create";

   /* static {
        //获取沙箱验签秘钥
        try {
            SANDBOX_API_KEY = getSandboxSignKey();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 刷新access_token，由WxApiServlet定时调用
     */
    public static void freshToken() {
        String  url = String.format(INTERFACE_ACCESS_TOKEN,appID,appsecret);
        String message = HttpUtil.getForString(url,null);
        if(null == message){
            System.out.println("刷新access_token出错");
            return;
        }

        JSONObject jsonObject = (JSONObject) JSONObject.parse(message);
        token = jsonObject.getString("access_token");
        System.out.println("刷新"+token);
    }

    /**
     * 获取微信用户openid
     * @param code 授权通过的code
     * @return openid
     * @throws IOException
     */
    public static String getOpenId(String code) {
        String  url = String.format(INTERFACE_OAUTH_ACCESS_TOKEN,appID,appsecret,code);
        String message = HttpUtil.getForString(url,null);
        if(null == message){
            System.out.println("获取openId出错");
            return null;
        }

        JSONObject jsonObject = (JSONObject) JSONObject.parse(message);
        String openid = jsonObject.getString("openid");
        return openid;
    }

    public static String getTicket() {
        String  url = String.format(INTERFACE_TICKET,token);
        String message = HttpUtil.getForString(url,null);
        if(null == message){
            System.out.println("获取ticket出错");
            return null;
        }
        JSONObject jsonObject = (JSONObject) JSONObject.parse(message);
        String ticket = jsonObject.getString("ticket");
        return ticket;
    }

    /**
     * 发送统一支付请求
     * @param ip 请求客户端IP地址
     * @param openId 请求用户openID
     * @param attach 支付附加参数
     * @param fee 支付金额（字符串，以分为单位）
     * @param callback 支付成功回调地址
     * @return 预支付订单号prepay_id
     * @throws IOException
     */
    public static String unifiedOrder(String ip,String openId, String attach, String fee, String callback)  {
        //拼接统一下单地址参数
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("appid", appID);
        map1.put("body", "滁州易培易证咨询服务有限公司-费用支付");
        map1.put("mch_id", mchID);
        map1.put("nonce_str", SignatureUtil.getNonceStr());
        map1.put("openid", openId);
        map1.put("out_trade_no", String.format("%d",new Date().getTime()));//订单号
        map1.put("attach", attach);//附加参数：费用编号
        map1.put("spbill_create_ip", ip);
        map1.put("total_fee",fee);
        map1.put("trade_type", "JSAPI");
        map1.put("notify_url",callback);
        map1.put("sign", SignatureUtil.signature(map1,API_KEY));

        String xml = XmlUtil.map2Xml(map1);//将所有参数(map)转xml格式
        System.out.println("下单请求："+xml);
        String str = HttpUtil.postForString(INTERFACE_PAY,null, xml);//发送post请求"统一下单接口"返回预支付id:prepay_id
        System.out.println("微信服务器返回："+str);

        Map<String, String> map2 = XmlUtil.xml2Map(str);
        if(map2.get("return_code").equals("SUCCESS")) {
            return map2.get("prepay_id");
        }

        return null;
    }

    public static String orderQuery(String out_trade_no){
        //封装订单查询参数
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("appid", appID);
        map1.put("mch_id", mchID);
        map1.put("nonce_str", SignatureUtil.getNonceStr());
        map1.put("out_trade_no", out_trade_no);//订单号
        map1.put("sign", SignatureUtil.signature(map1,API_KEY));

        //转换成xml格式
        String xml = XmlUtil.map2Xml(map1);
        System.out.println("查询请求："+xml);

        //发送订单查询
        String str = HttpUtil.postForString(INTERFACE_ORDER_PAY,null, xml);
        System.out.println("微信服务器返回："+str);

        //解析查询结果
        Map<String, String> map2 = XmlUtil.xml2Map(str);
        JSONObject json = new JSONObject();
        if(map2.get("return_code").equals("SUCCESS")) {
            if(map2.get("result_code").equals("SUCCESS")) {
                if(map2.get("trade_state").equals("SUCCESS")) {
                    json.put("success",true);
                    json.put("mch_id",map2.get("mch_id"));
                    json.put("sign",map2.get("sign"));
                    json.put("total_fee",map2.get("total_fee"));
                    json.put("out_trade_no",map2.get("out_trade_no"));
                }else{
                    json.put("success","false");
                    json.put("msg",map2.get("trade_state_desc"));
                }
            }else{
                json.put("success",false);
                json.put("msg",map2.get("err_code_des"));
            }
        }else{
            json.put("success","false");
            json.put("msg",map2.get("return_msg"));
        }
        return json.toJSONString();
    }

    public static String downloadBill(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(date);
        //封装订单查询参数
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("appid", appID);
        map1.put("mch_id", mchID);
        map1.put("nonce_str", SignatureUtil.getNonceStr());
        map1.put("bill_date", dateStr);
        map1.put("bill_type", "ALL");
        map1.put("sign", SignatureUtil.signature(map1,API_KEY));

        //转换成xml格式
        String xml = XmlUtil.map2Xml(map1);
        System.out.println("查询请求："+xml);

        //发送对账请求
        String str = HttpUtil.postForString(INTERFACE_DOWNLOAD_BILL,null, xml);
        System.out.println("微信服务器返回："+str);

        //解析结果
        /*Map<String, String> map2 = comm.XmlUtil.xml2Map(str);
        JSONObject json = new JSONObject();
        if(map2.getLast("return_code").equals("SUCCESS")) {
            if(map2.getLast("result_code").equals("SUCCESS")) {
                if(map2.getLast("trade_state").equals("SUCCESS")) {
                    json.put("success",true);
                    json.put("mch_id",map2.getLast("mch_id"));
                    json.put("sign",map2.getLast("sign"));
                    json.put("total_fee",map2.getLast("total_fee"));
                    json.put("out_trade_no",map2.getLast("out_trade_no"));
                }else{
                    json.put("success","false");
                    json.put("msg",map2.getLast("trade_state_desc"));
                }
            }else{
                json.put("success",false);
                json.put("msg",map2.getLast("err_code_des"));
            }
        }else{
            json.put("success","false");
            json.put("msg",map2.getLast("return_msg"));
        }
        return json.toJSONString();*/

        return str;
    }

    public static boolean sendTemplateMsg(String openId, String title, String content, String link){
        String url = INTERFACE_SEND_MSG +"?access_token="+token;

        JSONObject first = new JSONObject();
        JSONObject keyword1 = new JSONObject();
        JSONObject keyword2 = new JSONObject();
        JSONObject remark = new JSONObject();
        JSONObject data = new JSONObject();
        first.put("value",title);
        keyword1.put("value","见详情");
        keyword2.put("value","见详情");
        remark.put("value",content);
        data.put("first",first);
        data.put("keyword1",keyword1);
        data.put("keyword2",keyword2);
        data.put("remark",remark);


        JSONObject json = new JSONObject();
        json.put("template_id","QCigv4HAqpm0wmV1SZyi4xIi5yMM_nALckCX9-q5R5w");
        json.put("touser",openId);
        json.put("url",link);
        json.put("data",data);

        String body = json.toJSONString();
        //System.out.println("发送模板消息："+body);
        String echo = HttpUtil.postForString(url,null,body);
        //System.out.println(echo);

        JSONObject json_echo = JSON.parseObject(echo);
        int errcode = json_echo.getInteger("errcode");
        return  (errcode == 0);
    }

    /**
     * 创建带参数的二维码
     * @param param JSON格式参数字符串
     * @return 获取二维码的url地址,null表示失败
     */
    public static JSONObject getQR(String param){
        String url = INTERFACE_CREATE_QR +"?access_token="+token;
        JSONObject json_action = new JSONObject();
        JSONObject json_info = new JSONObject();
        JSONObject json_scene = new JSONObject();
        json_scene.put("scene_str",param);
        json_info.put("scene",json_scene);
        json_action.put("action_name","QR_LIMIT_STR_SCENE");
        json_action.put("action_info",json_info);
        String body = json_action.toJSONString();
        String echo = HttpUtil.postForString(url,null,body);
        JSONObject json_echo = JSON.parseObject(echo);
        String ticket = json_echo.getString("ticket");

        JSONObject json = new JSONObject();
        if(ticket == null){
            json.put("success",false);
            json.put("msg",json_echo.getString("errmsg"));
        }else{
            json.put("success",true);
            json.put("ticket",ticket);
            json.put("url","https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket);
        }
        return json;
    }

    /**
     * 获取沙箱验签秘钥（支付测试时需要用）
     * @return 秘钥
     * @throws IOException
     */
    private static String getSandboxSignKey() {
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("appid", appID);
        paraMap.put("mch_id", mchID);
        paraMap.put("nonce_str", SignatureUtil.getNonceStr());
        paraMap.put("sign", SignatureUtil.signature(paraMap,API_KEY));

        String xml = XmlUtil.map2Xml(paraMap);//将所有参数(map)转xml格式
        String str = HttpUtil.postForString(GET_SANDBOX_KEY,null, xml);//发送post请求"统一下单接口"返回预支付id:prepay_id
        System.out.println("获取沙箱验签秘钥返回："+str);

        Map<String, String> map = XmlUtil.xml2Map(str);
        String key = "";
        if(map.get("return_code").equals("SUCCESS")) {
            key = map.get("sandbox_signkey");
        }
        return key;
    }
    /**
     * 获取微信服务器媒体资源的字节数据
     * @param mediaId 资源id
     * @return 资源输入流
     * @throws IOException
     */
    public static byte[] getMediaData(String mediaId){
        String  url = String.format(INTERFACE_MEDIA,token,mediaId);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/x-www-form-urlencoded");
        return HttpUtil.getForBytes(url,header);
    }
}
