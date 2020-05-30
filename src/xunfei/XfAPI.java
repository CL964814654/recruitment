package xunfei;

import com.alibaba.fastjson.JSONObject;
import comm.HttpUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 科大讯飞身份证识别接口工具类
 */
public class XfAPI {
    // 身份证识别接口地址
    private static final String IDCARD_URL = "http://webapi.xfyun.cn/v1/service/v1/ocr/idcard";
    // 印刷文字识别接口地址
    private static final String GENERAL_URL = "http://webapi.xfyun.cn/v1/service/v1/ocr/general";
    // 应用ID
    private static final String APPID = "5c6ffb35";
    // 接口密钥
    private static final String API_KEY = "0486ee31ae9828f393e4e7019c701758";
    // 引擎类型
    private static final String ENGINE_TYPE = "idcard";
    // 是否返回头像图片
    private static final String HEAD_PORTRAIT = "0";
    // 是否返回切片图
    private static final String CROP_IMAGE = "1";

    public static void main(String[] args) throws IOException {

    }

    /**
     * 科大讯飞身份证识别接口
     * @param base64 身份证图片的base64编码字符串
     * @return 识别结果 成功返回{success:true,cardId:身份证号,name:姓名,address:地址,cropped_portrait:头像数据,cropped_idcard:身份证切片数据}，错误返回{success:false,msg:……}
     */
    public static JSONObject recognition(byte []base64) {
        JSONObject json = new JSONObject();
        try {
            Map<String, String> header = buildCardHeader();
            String body = "image=" + new String(Base64.encodeBase64(base64), "UTF-8");
            String result = HttpUtil.postForString(IDCARD_URL,header,body);

            JSONObject res = JSONObject.parseObject(result);
            if (res.getInteger("code") != 0) {
                json.put("success",false);
                json.put("msg","识别失败，原因:"+res.getString("desc"));
            }else {

                //提取身份证号、姓名、地址、相片(暂时不要了)和身份证切图
                JSONObject data = res.getJSONObject("data");
                json.put("cardId", data.getString("id_number"));
                json.put("name", data.getString("name"));
                json.put("address", data.getString("address"));
                /*json.put("cropped_portrait", data.getJSONObject("head_portrait").getString("image"));*/
                json.put("cropped_idcard", data.getString("cropped_image"));
                json.put("success", true);
            }
        } catch (Exception e) {
            json.put("success",false);
            json.put("msg","识别失败，原因:字符编码错误");
        }
        return json;
    }

    /**
     * 切图
     * @param base64 图片的base64编码字符串
     * @return 识别结果，成功返回{success:true,image:……}，错误返回{success:false,msg:……}
     */
    public static JSONObject crop(byte []base64) {
        JSONObject json = new JSONObject();
        try {
            Map<String, String> header = buildCardHeader();
            String body = "image=" + new String(Base64.encodeBase64(base64), "UTF-8");            ;
            String result = HttpUtil.postForString(IDCARD_URL,header,body);

            //切图也是调用身份证识别接口间接完成，识别操作肯定是失败的，只要有data，说明切图成功
            JSONObject res = JSONObject.parseObject(result);
            JSONObject data = res.getJSONObject("data");
            if(data == null){
                json.put("success",false);
                json.put("msg","切图失败，原因:"+res.getString("desc"));
            }else {
                //提取切图
                json.put("image", data.getString("cropped_image"));
                json.put("success", true);
            }
        } catch (UnsupportedEncodingException e) {
            json.put("success",false);
            json.put("msg","识别失败，原因:字符编码错误");
        }
        return json;
    }

    /**
     * 识别图片中的文字
     * @param base64 base64格式的图片数据
     * @return 识别结果，成功返回{success:true,data:……}，失败返回{success:false,msg:……}
     */
    public static JSONObject ocr(String base64){
        JSONObject json = new JSONObject();
        try {
            Map<String, String> header = buildOcrHeader();
            String body = "image=" + new String(base64);
            String result = HttpUtil.postForString(GENERAL_URL,header,body);

            JSONObject res = JSONObject.parseObject(result);
            if(res.getInteger("code") == 0){
                json.put("data",res.getString("data"));
                json.put("success",true);
            }else{
                json.put("msg","识别失败，原因:"+res.getString("desc"));
                json.put("success",false);
            }
        } catch (UnsupportedEncodingException e) {
            json.put("success",false);
            json.put("msg","识别失败，原因:字符编码错误");
        }
        return json;
    }

    /**
     * 组装身份证识别http请求头
     */
    private static Map<String, String> buildCardHeader() throws UnsupportedEncodingException {
        String curTime = System.currentTimeMillis() / 1000L + "";
        String param = "{\"engine_type\":\"" + ENGINE_TYPE + "\",\"head_portrait\":\"" + HEAD_PORTRAIT + "\",\"crop_image\":\"" + CROP_IMAGE  +"\"}";
        String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
        String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        header.put("X-Param", paramBase64);
        header.put("X-CurTime", curTime);
        header.put("X-CheckSum", checkSum);
        header.put("X-Appid", APPID);
        return header;
    }

    /**
     * 组装文字识别http请求头
     */
    private static Map<String, String> buildOcrHeader() throws UnsupportedEncodingException {
        String curTime = System.currentTimeMillis() / 1000L + "";
        String param = "{\"location\":\"false\",\"language\":\"cn|en\"}";
        String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
        String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);

        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        header.put("X-Param", paramBase64);
        header.put("X-CurTime", curTime);
        header.put("X-CheckSum", checkSum);
        header.put("X-Appid", APPID);
        return header;
    }
}
