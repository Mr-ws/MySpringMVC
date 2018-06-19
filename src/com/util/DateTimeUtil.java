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
	public static final String[] months = { "һ��", "����", "����", "����", "����", "����",
			"����", "����", "����", "ʮ��", "ʮһ��", "ʮ����", };

	public static final String[] quarters = { "һ����", "������", "������", "�ļ���" };

	/**
	 * ��ȡ�������ڸ�ʽ�� yyyy-MM-dd HH:mm:ss
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
	 * @date 2017��11��23��
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
	 * @date 2017��11��23��
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
	 * @date 2017��11��23��
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
	 * @date 2017��11��23��
	 */
	public static String getStringDateSix(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		return formatter.format(date);
	}

	/**
	 * �������ռ������� ���꼸���¼���
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
			int monthBirth = cal.get(Calendar.MONTH);// + 1���������·ݣ�month��0��ʼ
														// 10�µĻ�Ӧ����9
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

			Calendar birthday = new GregorianCalendar(yearBirth, monthBirth,
					dayOfMonthBirth);// 2010��10��12�գ�month��0��ʼ
										// 10�µĻ�Ӧ����9

			Calendar now = Calendar.getInstance();
			int day = now.get(Calendar.DAY_OF_MONTH)
					- birthday.get(Calendar.DAY_OF_MONTH);
			int month = now.get(Calendar.MONTH) - birthday.get(Calendar.MONTH);
			int year = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
			// ���ռ���ԭ����day�����������month�裻Ȼ��month�����������year�裻���year�����

			if (day < 0) {
				month -= 1;
				now.add(Calendar.MONTH, -1);// �õ���һ���£������õ��ϸ��µ�������

				day = day + now.getActualMaximum(Calendar.DAY_OF_MONTH);
			}
			if (month < 0) {
				month = (month + 12) % 12;
				year--;
			}
			ageStr = year + "��" + month + "��" + day + "��";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ageStr;
	}

	/**
	 * ���ָ��ʱ���ʱ���루��ʽ��HH:mm:ss��
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
	 * @date 2017��11��23��
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
	 * @date 2017��11��23��
	 */
	public static String getTimeStrTwo(Time time) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		String str = df.format(time);
		return str;
	}

	/**
	 * ��õ�ǰʱ���ʱ���루��ʽ��HH:mm:ss��
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
		// ������
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			java.util.Date date = df.parse(dateStr);
			return date;
		} catch (Exception ex) {
			// LOGGER.error("���ڸ�ʽ�����ϻ�������Ϊ�գ����飡");
			ex.printStackTrace();
			return null;
		}
	}

	/*********************************************************************************************/

	/**
	 * ��ȡָ����������Ӧ������ʱ�䡣
	 *
	 * @param millis
	 *            ������
	 * @param format
	 *            ת���ĸ�ʽ�����磺��yyyy-MM-dd����
	 * @return ��������Ӧ������ʱ�䡣
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
	 * ��ȡָ������ʱ��ĺ�������
	 *
	 * @param dateString
	 *            Ҫת���������ַ���
	 * @param format
	 *            ת���ĸ�ʽ�����磺��yyyy-MM-dd����
	 * @return ����ʱ��ĺ�������
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
	 * ��ȡ��ǰ����ַ�����
	 * 
	 * <pre>
	 *  �����ַ�����ʽ�� yyyy
	 *  ���У�
	 *      yyyy   ��ʾ4λ�ꡣ
	 * </pre>
	 *
	 * @return String "yyyy"��ʽ�ĵ�ǰ����ַ�����
	 */
	public static String getNowYear() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(new Date());
	}

	/**
	 * ��ȡ��ǰ�¶��ַ�����
	 * 
	 * <pre>
	 *  �����ַ�����ʽ�� MM
	 *  ���У�
	 *      MM   ��ʾ4λ�ꡣ
	 * </pre>
	 *
	 * @return String "yyyy"��ʽ�ĵ�ǰ�¶��ַ�����
	 */
	public static String getNowMonth() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		return formatter.format(new Date());
	}

	/**
	 * ��ȡ��ǰ�¶��ַ�����
	 *
	 * <pre>
	 *  �����ַ�����ʽ�� dd
	 *  ���У�
	 *      dd   ��ʾ4λ�ꡣ
	 * </pre>
	 *
	 * @return String "yyyy"��ʽ�ĵ�ǰ�¶��ַ�����
	 */
	public static String getNowDay() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		return formatter.format(new Date());
	}

	/**
	 * ��ȡ�����ַ�����
	 *
	 * <pre>
	 *  �����ַ�����ʽ�� yyyyMMdd
	 *  ���У�
	 *      yyyy   ��ʾ4λ�ꡣ
	 *      MM     ��ʾ2λ�¡�
	 *      dd     ��ʾ2λ�ա�
	 * </pre>
	 *
	 * @param date
	 *            ��Ҫת�������ڡ�
	 * @return String "yyyyMMdd"��ʽ�������ַ�����
	 */
	public static String getDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	/**
	 * ��ȡ�����ַ�����
	 *
	 * <pre>
	 *  �����ַ�����ʽ�� yyyyMMdd
	 *  ���У�
	 *      yyyy   ��ʾ4λ�ꡣ
	 *      MM     ��ʾ2λ�¡�
	 *      dd     ��ʾ2λ�ա�
	 * </pre>
	 *
	 * @param date
	 *            ��Ҫת�������ڡ�
	 * @return String "yyyyMMdd"��ʽ�������ַ�����
	 */
	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(new Date());
	}

	/**
	 * ��ָ���������ַ���ת��Ϊ���ڶ���
	 *
	 * @param dateStr
	 *            �����ַ���
	 * @return java.util.Date
	 */
	public static Date getDate(String dateStr) {
		if (isDate(dateStr, "yyyyMMdd")) {
			// ������
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			try {
				java.util.Date date = df.parse(dateStr);
				return date;
			} catch (Exception ex) {
				// LOGGER.error("���ڸ�ʽ�����ϻ�������Ϊ�գ����飡");
				return null;
			}
		} else if (isDateTime(dateStr, "yyyy-MM-dd")) {
			// ����ʱ����
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date date = df.parse(dateStr);
				return date;
			} catch (Exception ex) {
				return null;
			}
		} else if (isDateTime(dateStr, "yyyy-MM-dd HH:mm")) {
			// ����ʱ����
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				java.util.Date date = df.parse(dateStr);
				return date;
			} catch (Exception ex) {
				return null;
			}
		} else if (isDateTime(dateStr, "yyyy-MM-dd HH:mm:ss")) {
			// ����ʱ����
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				java.util.Date date = df.parse(dateStr);
				return date;
			} catch (Exception ex) {
				return null;
			}
		} else if (isDateTime(dateStr, "yyyy-MM-dd HH:mm:ss.SSS")) {
			// ����ʱ����
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
	 * ��ȡ�����ַ�����
	 *
	 * <pre>
	 *  �����ַ�����ʽ�� yyyy-MM-dd
	 *  ���У�
	 *      yyyy   ��ʾ4λ�ꡣ
	 *      MM     ��ʾ2λ�¡�
	 *      dd     ��ʾ2λ�ա�
	 * </pre>
	 *
	 * @return String "yyyy-MM-dd"��ʽ�������ַ�����
	 */
	public static String getHyphenDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date());
	}

	/**
	 * ��ȡ���������ַ�����
	 *
	 * <pre>
	 *  �����ַ�����ʽ�� yyyyMMddHHmss
	 *  ���У�
	 *      yyyy   ��ʾ4λ�ꡣ
	 *      MM     ��ʾ2λ�¡�
	 *      dd     ��ʾ2λ�ա�
	 *      HH     ��ʾ2λʱ��
	 *      mm     ��ʾ2λ�֡�
	 *      ss     ��ʾ2λ�롣
	 * </pre>
	 *
	 * @return String "yyyyMMddHHmmss"��ʽ�������ַ�����
	 */
	public static String getNumStringDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		return formatter.format(new Date());
	}

	/**
	 * ��ȡ�����ַ�����
	 *
	 * <pre>
	 *  �����ַ�����ʽ�� yyyy-MM-dd
	 *  ���У�
	 *      yyyy   ��ʾ4λ�ꡣ
	 *      MM     ��ʾ2λ�¡�
	 *      dd     ��ʾ2λ�ա�
	 * </pre>
	 *
	 * @param date
	 *            ��Ҫת�������ڡ�
	 * @return String "yyyy-MM-dd"��ʽ�������ַ�����
	 */
	public static String getHyphenDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	/**
	 * ��"yyyyMMdd"��ʽ�������ַ���ת��Ϊ���ڶ���
	 *
	 * @param source
	 *            �����ַ�����
	 * @return Date ���ڶ���
	 */
	public static Date parsePlainDate(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.parse(source, new ParsePosition(0));
	}

	/**
	 * ����yyyy-MM-dd����ʽ�������ַ���ת��Ϊ���ڶ���
	 *
	 * @param source
	 *            �����ַ�����
	 * @return Date ���ڶ���
	 */
	public static Date parseHyphenDate(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(source, new ParsePosition(0));
	}

	/**
	 * ��ָ����ʽ�������ַ���ת��Ϊ���ڶ���
	 *
	 * @param source
	 *            �����ַ�����
	 * @param pattern
	 *            ģʽ��
	 * @return Date ���ڶ���
	 */
	public static Date parseDate(String source, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(source, new ParsePosition(0));
	}

	/**
	 * ����yyyy-MM-dd����ʽ�������ַ���ת��Ϊ��yyyyMMdd����ʽ�������ַ�����
	 *
	 * @param source
	 *            �����ַ�����
	 * @return String "yyyymmdd"��ʽ�������ַ�����
	 */
	public static String toPlainDate(String source) {
		Date date = parseHyphenDate(source);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(date);
	}

	/**
	 * ����yyyyMMdd����ʽ�������ַ���ת��Ϊ��yyyy-MM-dd����ʽ�������ַ�����
	 *
	 * @param source
	 *            �����ַ�����
	 * @return String "yyyy-MM-dd"��ʽ�������ַ�����
	 */
	public static String toHyphenDate(String source) {
		Date date = parsePlainDate(source);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	/**
	 * ��ȡʱ����������ڶ���ת��Ϊʱ������͡�
	 *
	 * @param date
	 *            ���ڶ���
	 * @return Timestamp ʱ���
	 */
	public static Timestamp getTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * ��ȡʱ���������ǰ����ת��Ϊʱ������͡�
	 *
	 * @return Timestamp ʱ���
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * ����yyyyMMdd����ʽ�������ַ���ת��ΪTimestamp���͵Ķ���
	 * 
	 * @param source
	 *            �����ַ���
	 * @return Timestamp ʱ���
	 */
	public static Timestamp parseTimestamp(String source) {
		Date date = parsePlainDate(source);
		return getTimestamp(date);
	}

	/**
	 * ���������� <br>
	 * Example:<br>
	 * XJPDateUtil.getPeriodYear("20040229" , -1);<br>
	 * XJPDateUtil.getPeriodYear("20040228" , -1);<br>
	 * XJPDateUtil.getPeriodYear("20020230" , 2);<br>
	 *
	 * @param source
	 *            ʱ�䴮
	 * @param yearPeriod
	 *            ������� -1����ʱ�����һ���,�Դ����ơ�
	 * @return String ʱ�䡣
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
	 * ��ȡ��ǰ���ں�ʱ��
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
	 * �������
	 *
	 * @param day
	 *            ����
	 * @return ������Ӻ������
	 */
	public static String addDate(int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis() + ((long) day) * 24 * 3600
				* 1000);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		return df.format(c.getTime());
	}

	/**
	 * �������
	 *
	 * @param day
	 *            ����
	 * @return ������Ӻ������
	 */
	public static String addDate2(int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis() + ((long) day) * 24 * 3600
				* 1000);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(c.getTime());
	}

	/**
	 * ���غ���
	 * 
	 * @param date
	 *            ����
	 * @return ���غ���
	 */
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * ��ȡ��ǰ���ں�ʱ��
	 * 
	 * @param format
	 *            ���ڸ�ʽ ����yyyy-MM-dd hh:mm
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
	 * ��strmon�����ڼ�Сһ����
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
	 * �ж��Ƿ�Ϊ�Ϸ��������ַ���
	 * 
	 * @param str_input
	 *            Ҫ�����ַ���
	 * @param rDateFormat
	 *            Ҫ�������ڸ�ʽ(���磺yyyyMMdd)
	 * @return boolean;����Ϊtrue,������Ϊfalse
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
	 * �ж��Ƿ�Ϊ�Ϸ�������ʱ���ַ���
	 * 
	 * @param str_input
	 *            Ҫ�����ַ���
	 * @param rDateFormat
	 *            Ҫ��������ʱ���ʽ(���磺yyyyMMdd HH:mm:ss)
	 * @return boolean;����Ϊtrue,������Ϊfalse
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
	 * �ж��ַ����Ƿ�Ϊnull
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
		str = h + "Сʱ" + m + "��" + s + "��";
		return str;
	}

	public static Date getDateByFormat(Date date, String format) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatDate = new SimpleDateFormat(format);
		String date1 = formatDate.format(date);
		return getDateByFormat(date1, format);
	}

	/**
	 * ���� ����ʱ��֮��� ������ ���� bdate - smdate ������
	 * 
	 * @param smdate
	 *            "yyyy-mm-dd"
	 * @param bdate
	 *            "yyyy-mm-dd"
	 * @return
	 * @throws ParseException
	 * @author WeiShuai
	 * @date 2018��1��3��
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
