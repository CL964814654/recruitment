package comm;

import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 签名工具包
 */
public class SignatureUtil {

    /**
     * 审核签名，即判断请求是否来自微信
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean CheckSignature(String signature, String timestamp, String nonce){
        //第一步中填写的token一致
        String token="train";

        String[] strs = new String[] {token,timestamp,nonce};
        Arrays.sort(strs);
        StringBuffer content=new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            content.append(strs[i]);
        }
        String checksignature = DigestUtils.sha1Hex(strs[0]+strs[1]+strs[2]);

        return signature.equals(checksignature);
    }

    /**
     * 生成签名，参数进行字典排序，最后和秘钥一起进行md5加密
     * @param data 参数（除秘钥外）
     * @param key 秘钥
     * @return 生成的签名
     */
    public static String signature(final Map<String, String> data,String key){
        String str = sort(data);
        str += String.format("&key=%s",key);
        return DigestUtils.md5Hex(str).toUpperCase();
    }

    public static String signature(final Map<String, String> data){
        String str = sort(data);
        return DigestUtils.sha1Hex(str);
    }

    /**
     * 字典排序
     * @param data 待排序的键值对集合
     * @return 排序后的字符串，形如"v1=123&v2=abc";
     */
    private static String sort(final Map<String, String> data){
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.deleteCharAt(sb.length()-1);//去掉最后一个&
        return sb.toString();
    }

       public static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public static String getNonceStr() {
        String currTime = getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = buildRandom(4) + "";
        return strTime + strRandom;
    }

    private static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    private static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(now);
    }
}
