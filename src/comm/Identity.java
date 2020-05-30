package comm;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Identity {

	public static boolean isValid(String id) {
		return meetRegex(id) && checkSum(id) && checkDate(id);
	}

	// 是否符合正则表达式
	private static boolean meetRegex(String id) {
		Pattern pattern = Pattern.compile("\\d{17}(\\d|x|X)$");
		Matcher isNum = pattern.matcher(id);
		if (!isNum.matches())
			return false;
		else
			return true;
	}

	// 校验位是否正确
	private static boolean checkSum(String id) {
		String checkCode = "10X98765432";
		int wi[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };

		int sum = 0;
		// 进行加权求和
		for (int i = 0; i < 17; i++) {
			sum += (id.charAt(i) - '0') * wi[i];
		}
		char checkChar = checkCode.charAt(sum % 11);

		return id.charAt(17) == checkChar;
	}

	// 判断时间合法性
	private static boolean checkDate(String id) {
		String dateStr = id.substring(6, 10) + "-" + id.substring(10, 12) + "-"+ id.substring(12, 14);
		DateFormat df = DateFormat.getDateInstance();
		df.setLenient(false);
		try {
			Date date = df.parse(dateStr);
			return (date != null);
		} catch (ParseException e) {
			return false;
		}
	}
}
