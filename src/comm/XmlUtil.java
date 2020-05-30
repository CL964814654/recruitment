package comm;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import wx.TextMsg;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * xml工具包
 */
public class XmlUtil {

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param map Map类型数据
     * @return XML格式的字符串
     * @throws Exception
     */
    public static String map2Xml(Map<String, String> map){
        Document d = DocumentHelper.createDocument();
        Element root = d.addElement("xml");
        Set<String> keys = map.keySet();
        for(String key:keys) {
            root.addElement(key).addText(map.get(key));
        }
        StringWriter sw = new StringWriter();
        XMLWriter xw = new XMLWriter(sw);
        xw.setEscapeText(false);
        try {
            xw.write(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    /**
     * 将XML转换成Map集合
     */
    public static Map<String, String> xml2Map(String str){
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader(); // 使用dom4j解析xml
        try {
            Document doc = reader.read(new ByteArrayInputStream(str.getBytes()));
            Element root = doc.getRootElement();         // 获取根元素
            List<Element> list = root.elements();        // 获取所有节点

            for (Element e : list) {
                map.put(e.getName(), e.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }

        return map;
    }

    /**
     * 将文本消息对象转换成XML
     */
    public static String toXML(TextMsg msg){
        XStream xstream = new XStream();              // 使用XStream将实体类的实例转换成xml格式
        xstream.alias("xml", msg.getClass()); // 将xml的默认根节点替换成“xml”
        return xstream.toXML(msg);
    }
}
