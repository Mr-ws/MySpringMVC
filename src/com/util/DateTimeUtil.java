package com.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateTimeUtil {
	public static final String[] months = { "一月", "二月", "三月", "四月", "五月", "六月",
			"七月", "八月", "九月", "十月", "十一月", "十二月", };

	public static final String[] quarters = { "一季度", "二季度", "三季度", "四季度" };

	/**
	 * 获取本日日期格式化 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getStringDateOne() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date());
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @param timestamp
	 * @return
	 * @author WeiShuai
	 * @date 2017年11月23日
	 */
	public static String getStringDateTwo(Timestamp timestamp) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(timestamp);
	}

	/**
	 * yyyy-MM-dd
	 * 
	 * @param timestamp
	 * @return
	 * @author WeiShuai
	 * @date 2017年11月23日
	 */
	public static String getStringDateThree(Timestamp timestamp) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(timestamp);
	}

	/**
	 * "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param date
	 * @return
	 * @author WeiShuai
	 * @date 2017年11月23日
	 */
	public static String getStringDateThour(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	/**
	 * yyyy-MM-dd
	 */
	public static String getStringDateFive() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date());
	}

	/**
	 * HH:mm:ss
	 * 
	 * @param date
	 * @return
	 * @author WeiShuai
	 * @date 2017年11月23日
	 */
	public static String getStringDateSix(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		return formatter.format(date);
	}

	/**
	 * 根据生日计算年龄 几岁几个月几天
	 * 
	 * @param birthday
	 * @return
	 */
	public static String getAgeStringByBirthday(String birthdaydate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ageStr = "";
		try {
			Date birthdayDate = sdf.parse(birthdaydate);
			Calendar cal = Calendar.getInstance();

			if (cal.before(birthdaydate)) {
				throw new IllegalArgumentException(
						"The birthDay is before Now.It's unbelievable!");
			}

			cal.setTime(birthdayDate);
			int yearBirth = cal.get(Calendar.YEAR);
			int monthBirth = cal.get(Calendar.MONTH);// + 1是真正的月份，month从0开始
														// 10月的话应该是9
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

			Calendar birthday = new GregorianCalendar(yearBirth, monthBirth,
					dayOfMonthBirth);// 2010年10月12日，month从0开始
										// 10月的话应该是9

			Calendar now = Calendar.getInstance();
			int day = now.get(Calendar.DAY_OF_MONTH)
					- birthday.get(Calendar.DAY_OF_MONTH);
			int month = now.get(Calendar.MONTH) - birthday.get(Calendar.MONTH);
			int year = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
			// 按照减法原理，先day相减，不够向month借；然后month相减，不够向year借；最后year相减。

			if (day < 0) {
				month -= 1;
				now.add(Calendar.MONTH, -1);// 得到上一个月，用来得到上个月的天数。

				day = day + now.getActualMaximum(Calendar.DAY_OF_MONTH);
			}
			if (month < 0) {
				month = (month + 12) % 12;
				year--;
			}
			ageStr = year + "年" + month + "月" + day + "天";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ageStr;
	}

	/**
	 * 获得指定时间的时分秒（格式：HH:mm:ss）
	 * 
	 * @param dateStr
	 * @return
	 * @author HOUSHZH
	 * @date 2014-5-22
	 * @version 0.1
	 */
	public static String getTimeStr(String dateStr) {
		Date date = getDate(dateStr);
		String str = null;
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		str = df.format(date);
		return str;
	}

	/**
	 * HH:mm:ss
	 * 
	 * @param time
	 * @return
	 * @author WeiShuai
	 * @date 2017年11月23日
	 */
	public static String getTimeStr(Time time) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		String str = df.format(time);
		return str;
	}

	/**
	 * HH:mm
	 * 
	 * @param time
	 * @return
	 * @author WeiShuai
	 * @date 2017年11月23日
	 */
	public static String getTimeStrTwo(Time time) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		String str = df.format(time);
		return str;
	}

	/**
	 * 获得当前时间的时分秒（格式：HH:mm:ss）
	 * 
	 * @return
	 * @author handtrust
	 * @date 2014-5-22
	 * @version 0.1
	 */
	public static String getTimeStr() {
		String str = "";
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		str = df.format(new Date());
		return str;
	}

	public static Date getDateByFormat(String dateStr, String format) {
		if (dateStr == null || "".equals(dateStr))
			return null;
		// 日期型
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			java.util.Date date = df.parse(dateStr);
			return date;
		} catch (Exception ex) {
			// LOGGER.error("日期格式不符合或者日期为空！请检查！");
			ex.printStackTrace();
			return null;
		}
	}

	/*********************************************************************************************/

	/**
	 * 获取指定毫秒数对应的日期时间。
	 *
	 * @param millis
	 *            毫秒数
	 * @param format
	 *            转换的格式（例如：‘yyyy-MM-dd’）
	 * @return 毫秒数对应的日期时间。
	 */
	public static String getDateTimeFromMillis(long millis, String format) {
		String rtnLongDate = "";
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(millis);
			DateFormat formatter = new SimpleDateFormat(format);
			rtnLongDate = formatter.format(calendar.getTime());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnLongDate;
	}

	/**
	 * 获取指定日期时间的毫秒数。
	 *
	 * @param dateString
	 *            要转换的日期字符串
	 * @param format
	 *            转换的格式（例如：‘yyyy-MM-dd’）
	 * @return 日期时间的毫秒数。
	 */
	public static long getTimeInMillis(String dateString, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		long rtnLongDate = 0L;
		try {
			rtnLongDate = formatter.parse(dateString).getTime();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnLongDate;
	}

	/**
	 * 获取当前年度字符串。
	 * 
	 * <pre>
	 *  日期字符串格式： yyyy
	 *  其中：
	 *      yyyy   表示4位年。
	 * </pre>
	 *
	 * @return String "yyyy"格式的当前年度字符串。
	 */
	public static String getNowYear() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(new Date());
	}

	/**
	 * 获取当前月度字符串。
	 * 
	 * <pre>
	 *  日期字符串格式： MM
	 *  其中：
	 *      MM   表示4位年。
	 * </pre>
	 *
	 * @return String "yyyy"格式的当前月度字符串。
	 */
	public static String getNowMonth() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		return formatter.format(new Date());
	}

	/**
	 * 获取当前月度字符串。
	 *
	 * <pre>
	 *  日期字符串格式： dd
	 *  其中：
	 *      dd   表示4位年。
	 * </pre>
	 *
	 * @return String "yyyy"格式的当前月度字符串。
	 */
	public static String getNowDay() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		return formatter.format(new Date());
	}

	/**
	 * 获取日期字符串。
	 *
	 * <pre>
	 *  日期字符串格式： yyyyMMdd
	 *  其中：
	 *      yyyy   表示4位年。
	 *      MM     表示2位月。
	 *      dd     表示2位日。
	 * </pre>
	 *
	 * @param date
	 *            需要转化的日期。
	 * @return String "yyyyMMdd"格式的日期字符串。
	 */
	public static String getDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	/**
	 * 获取日期字符串。
	 *
	 * <pre>
	 *  日期字符串格式： yyyyMMdd
	 *  其中：
	 *      yyyy   表示4位年。
	 *      MM     表示2位月。
	 *      dd     表示2位日。
	 * </pre>
	 *
	 * @param date
	 *            需要转化的日期。
	 * @return String "yyyyMMdd"格式的日期字符串。
	 */
	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(new Date());
	}

	/**
	 * 将指定的日期字符串转化为日期对象
	 *
	 * @param dateStr
	 *            日期字符串
	 * @return java.util.Date
	 */
	public static Date getDate(String dateStr) {
		if (isDate(dateStr, "yyyyMMdd")) {
			// 日期型
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			try {
				java.util.Date date = df.parse(dateStr);
				return date;
			} catch (Exception ex) {
				// LOGGER.error("日期格式不符合或者日期为空！请检查！");
				return null;
			}
		} else if (isDateTime(dateStr, "yyyy-MM-dd")) {
			// 日期时间型
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date date = df.parse(dateStr);
				return date;
			} catch (Exception ex) {
				return null;
			}
		} else if (isDateTime(dateStr, "yyyy-MM-dd HH:mm")) {
			// 日期时间型
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				java.util.Date date = df.parse(dateStr);
				return date;
			} catch (Exception ex) {
				return null;
			}
		} else if (isDateTime(dateStr, "yyyy-MM-dd HH:mm:ss")) {
			// 日期时间型
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				java.util.Date date = df.parse(dateStr);
				return date;
			} catch (Exception ex) {
				return null;
			}
		} else if (isDateTime(dateStr, "yyyy-MM-dd HH:mm:ss.SSS")) {
			// 日期时间型
			SimpleDateFormat df = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss.SSS");
			try {
				java.util.Date date = df.parse(dateStr);
				return date;
			} catch (Exception ex) {
				return null;
			} // end try - catch
		} // end if
		return null;
	}

	/**
	 * 获取日期字符串。
	 *
	 * <pre>
	 *  日期字符串格式： yyyy-MM-dd
	 *  其中：
	 *      yyyy   表示4位年。
	 *      MM     表示2位月。
	 *      dd     表示2位日。
	 * </pre>
	 *
	 * @return String "yyyy-MM-dd"格式的日期字符串。
	 */
	public static String getHyphenDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date());
	}

	/**
	 * 获取日期数字字符串。
	 *
	 * <pre>
	 *  日期字符串格式： yyyyMMddHHmss
	 *  其中：
	 *      yyyy   表示4位年。
	 *      MM     表示2位月。
	 *      dd     表示2位日。
	 *      HH     表示2位时。
	 *      mm     表示2位分。
	 *      ss     表示2位秒。
	 * </pre>
	 *
	 * @return String "yyyyMMddHHmmss"格式的日期字符串。
	 */
	public static String getNumStringDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		return formatter.format(new Date());
	}

	/**
	 * 获取日期字符串。
	 *
	 * <pre>
	 *  日期字符串格式： yyyy-MM-dd
	 *  其中：
	 *      yyyy   表示4位年。
	 *      MM     表示2位月。
	 *      dd     表示2位日。
	 * </pre>
	 *
	 * @param date
	 *            需要转化的日期。
	 * @return String "yyyy-MM-dd"格式的日期字符串。
	 */
	public static String getHyphenDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	/**
	 * 将"yyyyMMdd"格式的日期字符串转换为日期对象。
	 *
	 * @param source
	 *            日期字符串。
	 * @return Date 日期对象。
	 */
	public static Date parsePlainDate(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.parse(source, new ParsePosition(0));
	}

	/**
	 * 将“yyyy-MM-dd”格式的日期字符串转换为日期对象。
	 *
	 * @param source
	 *            日期字符串。
	 * @return Date 日期对象。
	 */
	public static Date parseHyphenDate(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(source, new ParsePosition(0));
	}

	/**
	 * 将指定格式的日期字符串转换为日期对象。
	 *
	 * @param source
	 *            日期字符串。
	 * @param pattern
	 *            模式。
	 * @return Date 日期对象。
	 */
	public static Date parseDate(String source, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(source, new ParsePosition(0));
	}

	/**
	 * 将“yyyy-MM-dd”格式的日期字符串转换为“yyyyMMdd”格式的日期字符串。
	 *
	 * @param source
	 *            日期字符串。
	 * @return String "yyyymmdd"格式的日期字符串。
	 */
	public static String toPlainDate(String source) {
		Date date = parseHyphenDate(source);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(date);
	}

	/**
	 * 将“yyyyMMdd”格式的日期字符串转换为“yyyy-MM-dd”格式的日期字符串。
	 *
	 * @param source
	 *            日期字符串。
	 * @return String "yyyy-MM-dd"格式的日期字符串。
	 */
	public static String toHyphenDate(String source) {
		Date date = parsePlainDate(source);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	/**
	 * 获取时间戳，将日期对象转换为时间戳类型。
	 *
	 * @param date
	 *            日期对象
	 * @return Timestamp 时间戳
	 */
	public static Timestamp getTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 获取时间戳，将当前日期转换为时间戳类型。
	 *
	 * @return Timestamp 时间戳
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 将“yyyyMMdd”格式的日期字符串转换为Timestamp类型的对象。
	 * 
	 * @param source
	 *            日期字符串
	 * @return Timestamp 时间戳
	 */
	public static Timestamp parseTimestamp(String source) {
		Date date = parsePlainDate(source);
		return getTimestamp(date);
	}

	/**
	 * 获得年度周期 <br>
	 * Example:<br>
	 * XJPDateUtil.getPeriodYear("20040229" , -1);<br>
	 * XJPDateUtil.getPeriodYear("20040228" , -1);<br>
	 * XJPDateUtil.getPeriodYear("20020230" , 2);<br>
	 *
	 * @param source
	 *            时间串
	 * @param yearPeriod
	 *            年度周期 -1代表本时间的上一年度,以次类推。
	 * @return String 时间。
	 */
	public static String getPeriodYear(String source, int yearPeriod) {
		int p = Integer.parseInt(source.substring(0, 4)) + yearPeriod;
		String newYear = String.valueOf(p)
				+ source.substring(4, source.length());
		Date date = parsePlainDate(newYear);
		String s = getDate(date);
		int ny = Integer.parseInt(s);
		int sy = Integer.parseInt(newYear);

		while (ny > sy) {
			sy--;
			ny = Integer.parseInt(getDate(parsePlainDate(String.valueOf(sy))));
		}

		return String.valueOf(sy);
	}

	/**
	 * 获取当前日期和时间
	 *
	 * @return String
	 */
	public static String getCurrentDateStr() {
		Date date = new Date();
		String str = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		str = df.format(date);
		return str;
	}

	/**
	 * 日期相加
	 *
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static String addDate(int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis() + ((long) day) * 24 * 3600
				* 1000);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		return df.format(c.getTime());
	}

	/**
	 * 日期相加
	 *
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static String addDate2(int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis() + ((long) day) * 24 * 3600
				* 1000);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(c.getTime());
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 获取当前日期和时间
	 * 
	 * @param format
	 *            日期格式 例：yyyy-MM-dd hh:mm
	 * @return String
	 */
	public static String getNowDate(String format) {
		Date date = new Date();
		String str = null;
		SimpleDateFormat df = new SimpleDateFormat(format);
		str = df.format(date);
		return str;
	}

	/**
	 * 将strmon的日期减小一个月
	 * 
	 * @param mon
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getReduceMonDate(String strmon) {
		if (strmon != null && !strmon.equals("")) {
			Date mon = parseHyphenDate(strmon);
			mon.setMonth(mon.getMonth() - 1);
			return getHyphenDate(mon);
		} else {
			return "";
		}
	}

	/**
	 * 判断是否为合法的日期字符串
	 * 
	 * @param str_input
	 *            要检查的字符串
	 * @param rDateFormat
	 *            要检查的日期格式(例如：yyyyMMdd)
	 * @return boolean;符合为true,不符合为false
	 */
	public static boolean isDate(String str_input, String rDateFormat) {
		if (!isNull(str_input)) {
			SimpleDateFormat formatter = new SimpleDateFormat(rDateFormat);
			formatter.setLenient(false);
			try {
				formatter.format(formatter.parse(str_input));
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为合法的日期时间字符串
	 * 
	 * @param str_input
	 *            要检查的字符串
	 * @param rDateFormat
	 *            要检查的日期时间格式(例如：yyyyMMdd HH:mm:ss)
	 * @return boolean;符合为true,不符合为false
	 */
	public static boolean isDateTime(String str_input, String rDateTimeFormat) {
		if (!isNull(str_input)) {
			SimpleDateFormat formatter = new SimpleDateFormat(rDateTimeFormat);
			formatter.setLenient(false);
			try {
				formatter.format(formatter.parse(str_input));
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为null
	 * 
	 * @param str
	 * @return
	 * @author HOUSHZH
	 * @date 2014-5-22
	 * @version 0.1
	 */
	public static boolean isNull(String str) {
		if (str == null) {
			return true;
		} else {
			return false;
		}
	}


	public static String getLeaveLength(long time) {
		String str = "";
		time = time / 1000;
		int s = (int) (time % 60);
		int m = (int) (time / 60 % 60);
		int h = (int) (time / 3600);
		str = h + "小时" + m + "分" + s + "秒";
		return str;
	}

	public static Date getDateByFormat(Date date, String format) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatDate = new SimpleDateFormat(format);
		String date1 = formatDate.format(date);
		return getDateByFormat(date1, format);
	}

	/**
	 * 计算 两个时间之间的 天数差 返回 bdate - smdate 的天数
	 * 
	 * @param smdate
	 *            "yyyy-mm-dd"
	 * @param bdate
	 *            "yyyy-mm-dd"
	 * @return
	 * @throws ParseException
	 * @author WeiShuai
	 * @date 2018年1月3日
	 */
	public static Integer daysBetween(String smdate, String bdate) {
		try {
			long between_days;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
