package com.helloword.test;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class DateUtil {
	public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String PATTERN_YYYY_MM = "yyyy-MM";

	/**
	 * 获取某个月份的起始时间
	 *
	 * @param month_time
	 * @return
	 * @throws ParseException
	 */
	public static String getBeginTime(String month_time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = sdf.parse(month_time);
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		// 设置为1号,当前日期既为本月第一天
		c.set(Calendar.DAY_OF_MONTH, 1);
		// 将小时至0
		c.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		c.set(Calendar.MINUTE, 0);
		// 将秒至0
		c.set(Calendar.SECOND, 0);
		// 将毫秒至0
		c.set(Calendar.MILLISECOND, 0);
		return sdf.format(c.getTime());
	}

	/**
	 * 获取某个月份的结束时间
	 *
	 * @param month_time
	 * @return
	 * @throws ParseException
	 */
	public static String getEndTime(String month_time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = sdf.parse(month_time);
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		// 设置为当月最后一天
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		// 将小时至23
		c.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至59
		c.set(Calendar.MINUTE, 59);
		// 将秒至59
		c.set(Calendar.SECOND, 59);
		return sdf.format(c.getTime());
	}
	
	/**
	 * 获取某个月份的起始时间
	 *
	 * @param month_time
	 * @return
	 * @throws ParseException
	 */
	public static String getBeginTimeToDay(String month_time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date time = sdf.parse(month_time);
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		// 设置为1号,当前日期既为本月第一天
		c.set(Calendar.DAY_OF_MONTH, 1);
		
		return sdf.format(c.getTime());
	}

	/**
	 * 获取某个月份的结束时间
	 *
	 * @param month_time
	 * @return
	 * @throws ParseException
	 */
	public static String getEndTimeToDay(String month_time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date time = sdf.parse(month_time);
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		// 设置为当月最后一天
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return sdf.format(c.getTime());
	}

	/**
	 * 获取6个月统计时间开始日期
	 *
	 * @return
	 */
	public static String getSixMonthBeginTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 5);
		// 设置为1号,当前日期既为本月第一天
		c.set(Calendar.DAY_OF_MONTH, 1);
		// 将小时至0
		c.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		c.set(Calendar.MINUTE, 0);
		// 将秒至0
		c.set(Calendar.SECOND, 0);
		return sdf.format(c.getTime());
	}

	/**
	 * 获取6个月统计时间结束日期
	 *
	 * @return
	 */
	public static String getSixMonthEndTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		// 设置为1号,当前日期既为本月第一天
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		// 将小时至0
		c.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至0
		c.set(Calendar.MINUTE, 59);
		// 将秒至0
		c.set(Calendar.SECOND, 59);
		return sdf.format(c.getTime());
	}

	/**
	 * 获取当月第一天日期
	 *
	 * @return
	 */
	public static String getCurrentMonthFirstDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.set(Calendar.DAY_OF_MONTH, 1);

		return sdf.format(c.getTime());
	}

	public static String getDatePoor(long beginTime, long endTime) {

		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// 获得两个时间的毫秒时间差异
		long diff = endTime - beginTime;
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		String str = "";
		if (day > 0) {
			str = day + "天";
		} else if (day == 0 && hour > 0) {
			str = hour + "小时";
		} else if (day == 0 && hour == 0 && min > 0) {
			str = min + "分钟";
		}
		return str;
	}

	public static String getOverDatePoor(long beginTime, long endTime) {

		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// 获得两个时间的毫秒时间差异
		long diff = endTime - beginTime;
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		String str = "";
		if (day > 0) {
			str = day + "天";
		} else if (hour > 0) {
			str = str + hour + "小时";
		} else if (min > 0) {
			str = str + min + "分钟";
		}
		return str;
	}

	/**
	 * 获取当天日期时间
	 *
	 * @return
	 */
	public static String getCurrentDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(time);

		return sdf.format(c.getTime());
	}
	
	/**
	 * 获取昨天日期时间
	 *
	 * @return
	 */
	public static String getLastDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.DAY_OF_MONTH, -1);

		return sdf.format(c.getTime());
	}
	
	/**
	 * 获取当前时间
	 *
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(time);

		return sdf.format(c.getTime());
	}

	/**
	 * 获取当月统计开始时间
	 *
	 * @return
	 */
	public static String getCurrentMonthEndTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.DAY_OF_MONTH, -1);
		// 将小时至0
		c.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至0
		c.set(Calendar.MINUTE, 59);
		// 将秒至0
		c.set(Calendar.SECOND, 59);
		return sdf.format(c.getTime());
	}

	/**
	 * 获取当月统计结束时间
	 *
	 * @return
	 */
	public static String getCurrentMonthBeginTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.set(Calendar.DAY_OF_MONTH, 1);
		// 将小时至0
		c.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		c.set(Calendar.MINUTE, 0);
		// 将秒至0
		c.set(Calendar.SECOND, 0);
		return sdf.format(c.getTime());
	}

	/**
	 * 获取当月统计结束时间
	 *
	 * @return
	 */
	public static int getCurrentMonthDayIndex() {
		Date time = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.DAY_OF_MONTH, -1);
		// 将小时至0
		c.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至0
		c.set(Calendar.MINUTE, 59);
		// 将秒至0
		c.set(Calendar.SECOND, 59);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取当月索引
	 *
	 * @return
	 */
	public static int getCurrentMonthIndex() {
		Date time = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		return c.get(Calendar.MONTH);
	}

	/**
	 * 获取当月统计开始时间
	 *
	 * @return
	 * @throws ParseException
	 */
	public static String getLastMonthEndTime(String dateTime, int day) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = sdf.parse(dateTime);
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
		if (day > c.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		} else {
			c.set(Calendar.DAY_OF_MONTH, day);
		}
		// 将小时至0
		c.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至0
		c.set(Calendar.MINUTE, 59);
		// 将秒至0
		c.set(Calendar.SECOND, 59);
		return sdf.format(c.getTime());
	}

	/**
	 * 获取当月统计结束时间
	 *
	 * @return
	 */
	public static String getLastMonthBeginTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		// 将小时至0
		c.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		c.set(Calendar.MINUTE, 0);
		// 将秒至0
		c.set(Calendar.SECOND, 0);
		return sdf.format(c.getTime());
	}

	/**
	 * 获取前3月份的月份数据信息
	 *
	 * @return
	 */
	public static List<String> getMonthTimeData() {
		List<String> lastMonths = new ArrayList<String>();

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		for (int i = 0; i < 3; i++) {
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); // 逐次往前推1个月
			String month_time = "";
			if ((cal.get(Calendar.MONTH) + 1) > 9) {
				month_time = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1);
			} else {
				month_time = cal.get(Calendar.YEAR) + "-0" + (cal.get(Calendar.MONTH) + 1);
			}
			lastMonths.add(month_time);
		}

		return lastMonths;
	}

	/**
	 * 获取上月最后一天
	 *
	 * @return
	 * @throws ParseException
	 */
	public static String getLastMonthLastDay(String month_time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = sdf.parse(month_time);
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
		// 设置为当月最后一天
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		// 将小时至23
		c.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至59
		c.set(Calendar.MINUTE, 59);
		// 将秒至59
		c.set(Calendar.SECOND, 59);
		return sdf.format(c.getTime());
	}

	/**
	 * 获取上月的第一天
	 *
	 * @return
	 * @throws ParseException
	 */
	public static String getLastMonthFirstDay(String month_time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = sdf.parse(month_time);
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
		// 设置为1号,当前日期既为本月第一天
		c.set(Calendar.DAY_OF_MONTH, 1);
		// 将小时至0
		c.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		c.set(Calendar.MINUTE, 0);
		// 将秒至0
		c.set(Calendar.SECOND, 0);
		return sdf.format(c.getTime());
	}

	/**
	 * 获取当月月份
	 *
	 * @return
	 */
	public static String getCurrentMonthTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		String month_time = "";
		if ((cal.get(Calendar.MONTH) + 1) > 9) {
			month_time = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1);
		} else {
			month_time = cal.get(Calendar.YEAR) + "-0" + (cal.get(Calendar.MONTH) + 1);
		}
		return month_time;
	}

	/**
	 * 获取上月月份
	 *
	 * @return
	 */
	public static String getLastMonthTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
		String month_time = "";
		if ((cal.get(Calendar.MONTH) + 1) > 9) {
			month_time = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1);
		} else {
			month_time = cal.get(Calendar.YEAR) + "-0" + (cal.get(Calendar.MONTH) + 1);
		}
		return month_time;
	}

	/**
	 * 计算数据
	 *
	 * @param thisMonthCount
	 * @param lastMonthCount
	 * @return
	 */
	public static double getResultBigDecimal(BigDecimal thisMonthCount, BigDecimal lastMonthCount, int num) {
		BigDecimal result = thisMonthCount.divide(lastMonthCount, num, BigDecimal.ROUND_HALF_UP);
		return result.doubleValue();
	}

	/**
	 * 格式化时间差为时分秒
	 *
	 * @return
	 */
	public static String formatLongToStr(long diff) {
		long nh = 60 * 60;
		long nm = 60;
		// 获得两个时间的毫秒时间差异
		// 计算差多少小时
		long hour = diff / nh;
		// 计算差多少分钟
		long min = diff % nh / nm;
		// 计算差多少秒
		long s = diff % nh % nm;
		String str = "";
		if (hour > 0) {
			str = str + hour + ":";
		}
		if (min > 0) {
			str = str + min + ":";
		}
		if (s > 0) {
			str = str + s;
		}
		if (StringUtil.isEmpty(str)) {
			str = "0";
		}
		return str;
	}

	// 获取每周的开始时间
	public static String getFirstDayOfWeek(String weekTime) throws ParseException {
		String f = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(f);
		Date date = format.parse(weekTime);
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setTime(date);
		c.set(Calendar.WEEK_OF_YEAR, c.get(Calendar.WEEK_OF_YEAR) - 1);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		return format.format(c.getTime());
	}

	// 获取每周的结束时间
	public static String getLastDayOfWeek(String weekTime) throws ParseException {
		String f = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(f);
		Date date = format.parse(weekTime);
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SATURDAY);
		c.setTime(date);
		c.set(Calendar.WEEK_OF_YEAR, c.get(Calendar.WEEK_OF_YEAR) - 1);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		return format.format(c.getTime());
	}

	// 获取去年的开始时间
	public static String getFirstDayOfYear(String beginTime) throws ParseException {
		String f = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(f);
		Date date = format.parse(beginTime);
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 1);
		c.set(Calendar.DAY_OF_YEAR, 1);
		// 将小时至0
		c.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		c.set(Calendar.MINUTE, 0);
		// 将秒至0
		c.set(Calendar.SECOND, 0);
		return format.format(c.getTime());
	}

	// 获取去年的结束时间
	public static String getLastDayOfYear(String endTime) throws ParseException {
		String f = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(f);
		Date date = format.parse(endTime);
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 1);
		c.set(Calendar.DAY_OF_YEAR, c.getActualMaximum(Calendar.DAY_OF_YEAR));
		// 将小时至0
		c.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至0
		c.set(Calendar.MINUTE, 59);
		// 将秒至0
		c.set(Calendar.SECOND, 59);
		return format.format(c.getTime());
	}
	
	/**
	 * 获取昨天统计开始时间
	 * @return
	 */
	public static String getYesterdayCurrentBeginTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(time);
		c.add(Calendar.DAY_OF_MONTH, -1);
		//将小时至0  
		c.set(Calendar.HOUR_OF_DAY, 0);
		//将分钟至0  
		c.set(Calendar.MINUTE, 0);  
		//将秒至0  
		c.set(Calendar.SECOND,0);  
		return sdf.format(c.getTime());
	}
	/**
	 * 获取昨天统计结束时间
	 * @return
	 */
	public static String getYesterdayCurrentEndTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(time);
		c.add(Calendar.DAY_OF_MONTH, -1);
		//将小时至0  
		c.set(Calendar.HOUR_OF_DAY, 23);
		//将分钟至0  
		c.set(Calendar.MINUTE, 59);  
		//将秒至0  
		c.set(Calendar.SECOND,59);  
		return sdf.format(c.getTime());
	}
	
	/**
	 * 获取前天统计开始时间
	 * @return
	 * @throws ParseException 
	 */
	public static String getLastDayCurrentBeginTime(String beginTime) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = sdf.parse(beginTime);
		Calendar c = Calendar.getInstance(); 
		c.setTime(time);
		c.add(Calendar.DAY_OF_MONTH, -1);
		//将小时至0  
		c.set(Calendar.HOUR_OF_DAY, 0);
		//将分钟至0  
		c.set(Calendar.MINUTE, 0);  
		//将秒至0  
		c.set(Calendar.SECOND,0);  
		return sdf.format(c.getTime());
	}
	/**
	 * 获取前天统计结束时间
	 * @return
	 * @throws ParseException 
	 */
	public static String getLastDayCurrentEndTime(String endTime) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = sdf.parse(endTime);
		Calendar c = Calendar.getInstance(); 
		c.setTime(time);
		c.add(Calendar.DAY_OF_MONTH, -1);
		//将小时至0  
		c.set(Calendar.HOUR_OF_DAY, 23);
		//将分钟至0  
		c.set(Calendar.MINUTE, 59);  
		//将秒至0  
		c.set(Calendar.SECOND,59);  
		return sdf.format(c.getTime());
	}

	/**
	 * 获取上周六的日期
	 * @return String
	 */
	public static String getLastWeekStartTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		// 周的最后一天
		calendar.set(Calendar.DAY_OF_WEEK, 7);
		// 设置为上周
		calendar.add(Calendar.WEEK_OF_YEAR, -1);
		// 将小时至0
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		calendar.set(Calendar.MINUTE, 0);
		// 将秒至0
		calendar.set(Calendar.SECOND,0);
		return sdf.format(calendar.getTime());
	}

	public static void main(String[] args) throws ParseException {
		System.out.println("getCurrentMonthTime==" + DateUtil.getCurrentMonthTime());
		System.out.println("getCurrentMonthDayIndex444==" + DateUtil.getCurrentMonthDayIndex());
		System.out.println("getCurrentMonthIndex444==" + DateUtil.getCurrentMonthIndex());
		System.out.println("11111111==" + DateUtil.getLastMonthBeginTime());
		String beginTime = "2019-05-01 00:00:00";
		System.out.println("22222222==" + DateUtil.getLastMonthEndTime(beginTime, 12));
		String dateTime = "2019-02-01 00:00:00";
		System.out.println("getLastMonthEndTime==" + DateUtil.getLastMonthEndTime(dateTime, 8));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String month_time = "2019-03-31 00:00:00";
		Date time = sdf.parse(month_time);
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		System.out.println("c.get(Calendar.DAY_OF_MONTH)==" + c.get(Calendar.DAY_OF_MONTH));
		System.out.println("2222222==" + c.get(Calendar.MONTH));
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
		System.out.println("c.getActualMaximum(Calendar.DAY_OF_MONTH)==" + c.getActualMaximum(Calendar.DAY_OF_MONTH));
		System.out.println("c.get(Calendar.MONTH)==" + c.get(Calendar.MONTH));
		if (c.get(Calendar.DAY_OF_MONTH) >= c.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		} else {
			c.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 将小时至0
		c.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至0
		c.set(Calendar.MINUTE, 59);
		// 将秒至0
		c.set(Calendar.SECOND, 59);
		// test
		System.out.println("1111111111111==" + sdf.format(c.getTime()));
		System.out.println("1111111111111==" + sdf.format(c.getTime()));
		System.out.println("7777777777777==" + DateUtil.getYesterdayCurrentBeginTime());
		System.out.println("8888888888888==" + DateUtil.getYesterdayCurrentEndTime());
		System.out.println("aaaaaaaaaaaaa==" + DateUtil.getFirstDayOfWeek("2019-05-19 00:00:00"));
		System.out.println("sssssssssssss==" + DateUtil.getLastDayOfWeek("2019-05-25 00:00:00"));
		System.out.println("3333333333333==" + DateUtil.getFirstDayOfWeek(0));
		System.out.println("6666666666666==" + DateUtil.getLastDayOfWeek(0));
		System.out.println("7777777777777==" + DateUtil.getCurrentMonthBeginTime());
		System.out.println("8888888888888==" + DateUtil.getCurrentMonthEndTime());
		
		
		System.out.println("wwwwwwwwwwwww==" + DateUtil.getBeginTimeToDay("2019-08-01"));
		System.out.println("sssssssssssss==" + DateUtil.getEndTimeToDay("2019-08-01"));
		
		int day = DateUtil.getCurrentMonthDayIndex();
		System.out.println("天的索引==="+day);
		String endLastMonthTime = DateUtil.getLastMonthEndTime("2019-09-26 00:00:00", day);
		System.out.println("上月endLastMonthTime===="+endLastMonthTime);
		// BigDecimal taskNumClose1 = new BigDecimal(19);
		// BigDecimal taskNum1 = new BigDecimal(2*100);
		// double d = getResultBigDecimal(taskNum1,taskNumClose1,2);
		// List<String> list = getMonthTimeData();
		// System.out.println("dddddd=="+list.toString());
	}

	/**
	 * 保留double小说点后n位小数
	 *
	 * @param d
	 * @param n
	 *            保留几位小数
	 * @return
	 */
	public static String enventFormatDoubleToStr(double d, int n) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		nf.setMaximumFractionDigits(n);
		return nf.format(d);
	}

	/**
	 * java.sql.date 转换为字符串格式'yyyy-mm-dd '
	 */
	public static String parseDateToStr(java.sql.Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date resDate = new Date(date.getTime());
		return sdf.format(resDate);
	}
	/**
	 * 拼接select语句in查询串
	 * @param sub_dimen
	 * @return
	 */
	public static String parseSelectInStr(String sub_dimen)
	{
		String[] subs = sub_dimen.split(",");
		String result = "";
		for (String id : subs)
		{
			result = result+"'"+id+"',";
		}
		if (!StringUtil.isEmpty(result))
		{
			result = result.substring(0, result.trim().length()-1);
		}
		return result;
	}
	//获取每周的开始时间
	public static String getFirstDayOfWeek(int weekIndex) throws ParseException
	{ 
		String f = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(f);
		Date date = new Date();
		Calendar c = new GregorianCalendar(); 
		c.setFirstDayOfWeek(Calendar.SUNDAY); 
		c.setTime(date); 
		c.set(Calendar.WEEK_OF_YEAR, c.get(Calendar.WEEK_OF_YEAR)- weekIndex);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());  
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)-1);
		//将小时至0  
		c.set(Calendar.HOUR_OF_DAY, 0);
		//将分钟至0  
		c.set(Calendar.MINUTE, 0);  
		//将秒至0  
		c.set(Calendar.SECOND,0);  
		return format.format(c.getTime ()); 
	}
	//获取每周的开始时间
	public static String getLastDayOfWeek(int weekIndex) throws ParseException
	{ 
		String f = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(f);
		Date date = new Date();
		Calendar c = new GregorianCalendar(); 
		c.setFirstDayOfWeek(Calendar.SUNDAY); 
		c.setTime(date); 
		c.set(Calendar.WEEK_OF_YEAR, c.get(Calendar.WEEK_OF_YEAR)- weekIndex);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()+6);  
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)-1);
		//将小时至0  
		c.set(Calendar.HOUR_OF_DAY, 23);
		//将分钟至0  
		c.set(Calendar.MINUTE, 59);  
		//将秒至0  
		c.set(Calendar.SECOND, 59); 
		return format.format(c.getTime ()); 
	}
	
	//获取某天的年月份
	public static String getDayOfMonth(String dayTime) throws ParseException {
		Date date = new SimpleDateFormat(PATTERN_YYYY_MM_DD).parse(dayTime);
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
		String monthTime = "";
		if ((cal.get(Calendar.MONTH) + 1) > 9) {
			monthTime = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1);
		} else {
			monthTime = cal.get(Calendar.YEAR) + "-0" + (cal.get(Calendar.MONTH) + 1);
		}
		return monthTime;
	}
	
}
