package comm;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Http Client 工具类
 */
public class HttpUtil {


	/**
	 * 发送get请求
	 * @param url 请求地址（包含参数）
	 * @param header 请求头部,null表示无头部设置
	 * @return
	 */
	public static String getForString(String url, Map<String, String> header){
			InputStream is = get(url,header);
			return inputStream2String(is);
		}

	private static InputStream get(String url, Map<String, String> header) {
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			// 设置 header
			if (null != header) {
				for (String key : header.keySet()) {
					http.setRequestProperty(key, header.get(key));
				}
			}
			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();
			return http.getInputStream();
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发送get请求
	 * @param url 请求地址（包含参数）
	 * @param header 请求头部,null表示无头部设置
	 * @return
	 */
	public static byte[] getForBytes(String url, Map<String, String> header){
		InputStream is = get(url,header);
		return inputStream2Bytes(is);
	}

	/**
	 * 发送post请求
	 * 
	 * @param url 请求地址
	 * @param header 请求头部
	 * @param body 参数
	 * @return
	 */
	public static String postForString(String url, Map<String, String> header, String body) {
			InputStream is = post(url,header,body);
			return inputStream2String(is);
	}
	/**
	 * 发送post请求
	 *
	 * @param url 请求地址
	 * @param header 请求头部
	 * @param body 参数
	 * @return
	 */
	public static byte[] postForBytes(String url, Map<String, String> header, String body) {
		InputStream is = post(url,header,body);
		return inputStream2Bytes(is);
	}

	private static InputStream post(String url, Map<String, String> header, String body) {
		try {
			// 设置 url
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
			// 设置 header
			if(null != header) {
				for (String key : header.keySet()) {
					//System.out.println(key + ":" + header.getLast(key));
					httpURLConnection.setRequestProperty(key, header.get(key));
				}
			}
			// 设置请求 body
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			PrintWriter out = new PrintWriter(httpURLConnection.getOutputStream());
			// 保存body
			out.print(body);
			// 发送body
			out.flush();
			if (HttpURLConnection.HTTP_OK != httpURLConnection.getResponseCode()) {
				System.out.println("Http 请求失败，状态码：" + httpURLConnection.getResponseCode());
				return null;
			}

			// 获取响应
			return httpURLConnection.getInputStream();
		} catch (Exception e) {
			return null;
		}
	}

	public static byte[] inputStream2Bytes(InputStream is) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024 * 4];
			int n = 0;
			while ((n = is.read(buffer)) != -1) {
				baos.write(buffer, 0, n);
			}
			byte[] bytes = baos.toByteArray();
			baos.close();
			return bytes;
		}catch (Exception e){
			return null;
		}

	}

	public static String inputStream2String(InputStream is){
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String result = "";
		String line;
		try {
			while ((line = in.readLine()) != null) {
                result += line;
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String parseIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}
		if(ip.indexOf(",")!=-1){
			String[] ips = ip.split(",");
			ip = ips[0].trim();
		}

		return ip;
	}
}
