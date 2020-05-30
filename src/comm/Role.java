package comm;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum Role {
    PLATFORM("平台"),
    INSTITUTION("机构"),
    AGENT2("团体"),
    AGENT1("区域"),
    STUDENT("学员"),
    REGULATOR("监管");

    private String text;//中文描述

    /**
     * 私有构造,防止被外部调用
     * @param text 枚举值描述
     */
    private Role(String text) {
        this.text = text;
    }

    private String getText() {
        return text;
    }

    public static String getText(Role value){
        for(Role role:Role.values()){
            if(role == value){
                return role.getText();
            }
        }
        return "";
    }

    public static String getJsonData(String valueFieldName,String textFieldName){
        List<HashMap<String,String>> roles = new ArrayList();
        for(Role role: Role.values()){
            HashMap<String,String> r = new HashMap();
            r.put(valueFieldName,role.toString());
            r.put(textFieldName,role.getText());
            roles.add(r);
        }
        JSONObject json = new JSONObject();
        json.put("success",true);
        json.put("roles",roles);
        return json.toJSONString();
    }
}
