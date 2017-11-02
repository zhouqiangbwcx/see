
package com.see.core.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	/**
	 * 判断是否为空,实体类是否new
	 * 
	 * @param obj
	 * @return false为空或“”或没new,true不为空或“”或已new
	 * @Description: 不能判断实体类是否有值
	 */
	public static boolean isNoNullOrEmpty(Object obj) {
		boolean bl = false;
		if (null != obj) {
			if (!"".equals(obj.toString())) {
				bl = true;
			}
		}
		return bl;
	}

	/**
	 * 判断是否为空,实体类是否new
	 * 
	 * @param obj
	 * @return true为空或“”或没new,false不为空或“”或已new
	 * @Description: 不能判断实体类是否有值
	 */
	public static boolean isNullOrEmpty(Object obj) {
		boolean bl = true;
		if (null != obj) {
			if (!"".equals(obj.toString())) {
				bl = false;
			}
		}
		return bl;
	}

	/**
	 * 
	 * @Title: isNumeric
	 * @Description: TODO 判断字符串是不是数字 是返回true
	 * @param str
	 * @return
	 * @return: boolean
	 */
	public static boolean isNumeric(String str) {
		String par= "[0-9]*";
		Pattern pattern = Pattern.compile(par);
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @Title: cleanXSS @Description: TODO防止 XSS 攻击的常用方法 @param: @param
	 *         value @param: @return @return: String @throws
	 */
	public static String cleanXSS(String value) {
		if (isNoNullOrEmpty(value)) {

			value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
			value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
			value = value.replaceAll("'", "& #39;");
			value = value.replaceAll("eval\\((.*)\\)", "");
			value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
			value = value.replaceAll("script", "");
		}
		return value;
	}

}
