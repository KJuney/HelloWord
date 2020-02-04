package com.helloword.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDay {
	public static void main(String[] args) throws ParseException {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
		String month_time = "";
		if ((cal.get(Calendar.MONTH) + 1) > 9) {
			month_time = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1);
		} else {
			month_time = cal.get(Calendar.YEAR) + "-0" + (cal.get(Calendar.MONTH) + 1);
		}
		System.out.println(month_time);
		


		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = sdf.parse(month_time);
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		// ����Ϊ1��,��ǰ���ڼ�Ϊ���µ�һ��
		c.set(Calendar.DAY_OF_MONTH, 1);
		// ��Сʱ��0
		c.set(Calendar.HOUR_OF_DAY, 0);
		// ��������0
		c.set(Calendar.MINUTE, 0);
		// ������0
		c.set(Calendar.SECOND, 0);
		// ��������0
		c.set(Calendar.MILLISECOND, 0);
	
		System.out.println(sdf.format(c.getTime()));
	
	
	}
}
