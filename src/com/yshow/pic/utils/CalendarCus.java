package com.yshow.pic.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarCus {

	private SimpleDateFormat simpleDateFormat;
	private Calendar calendar;
	private int currentYear;
	private int currentMonth;
	private int currentDay;
	private int sumDayOfMonth;

	// year:年�?month:月[1,12]。day:阳历月中的天号[1,]
	public CalendarCus(String date, String format) {// format = "yyyy-MM-dd"
		simpleDateFormat = new SimpleDateFormat(format);
		this.calendar = initialCalendar(date);
	}
	public CalendarCus(String format)
	{
		simpleDateFormat = new SimpleDateFormat(format);
		initialCalendar(simpleDateFormat.format(Calendar.getInstance().getTime()));
	}
	private Calendar initialCalendar(String date) {
		String[] buf = date.split("-");
		this.currentYear = Integer.parseInt(buf[0]);
		this.currentMonth = Integer.parseInt(buf[1]);
		this.currentDay = Integer.parseInt(buf[2]);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, this.currentYear);// 设置哪一�?
		calendar.set(Calendar.MONTH, this.currentMonth - 1); // 设置1�?
		calendar.set(Calendar.DAY_OF_MONTH, this.currentDay); // 设置1�?
		this.sumDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return calendar;
	}

	// 输出格式为format,日期包括设置的当�?
	public String getNextDate() {
		calendar.set(Calendar.DAY_OF_MONTH, currentDay++);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		String currentDate = simpleDateFormat.format(calendar.getTime());
		if (dayOfMonth >= this.sumDayOfMonth) {
			currentDay = 1;
			this.currentMonth++;
			if (this.currentMonth == 13) {
				calendar.set(Calendar.YEAR, ++this.currentYear);
				this.currentMonth = 1;
			}

			calendar.set(Calendar.DAY_OF_MONTH, currentDay);
			calendar.set(Calendar.MONTH, this.currentMonth - 1);
			this.sumDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		return currentDate;
	}

	// 两个日期相差天数,yyyy-MM-dd
	public static long getSubDays(String date1, String date2) {
		Calendar cal1 = Calendar.getInstance(), cal2 = Calendar.getInstance();
		String[] max = date1.split("-");
		String[] min = date2.split("-");
		if (Integer.parseInt(max[0]) < Integer.parseInt(min[0])) {
			String[] buf = max;
			max = min;
			min = buf;
		} else if (Integer.parseInt(max[1]) < Integer.parseInt(min[1])) {
			String[] buf = max;
			max = min;
			min = buf;
		} else if (Integer.parseInt(max[2]) < Integer.parseInt(min[2])) {
			String[] buf = max;
			max = min;
			min = buf;
		}
		cal1.set(Integer.parseInt(max[0]), Integer.parseInt(max[1]), Integer.parseInt(max[2]));
		cal2.set(Integer.parseInt(min[0]), Integer.parseInt(min[1]), Integer.parseInt(min[2]));
		return (cal1.getTimeInMillis() - cal2.getTimeInMillis()) / (1000 * 60 * 60 * 24);
	}
	// 两个日期相差天数,yyyy-MM-dd
	public static long getSubDays(long date1, long date2) {
		if(date1 > date2)
			return (date1 - date2) / (1000 * 60 * 60 * 24) + 1;
		return (date2 - date1) / (1000 * 60 * 60 * 24) + 1;
	}

	// 获取距离今天的偏移日期，offsetday�?，表示获取今天日�?
	public String getDateCurrentOffset(Calendar cal, int offsetday) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(cal.getTimeInMillis());
		int currentday = calendar.get(Calendar.DAY_OF_MONTH);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int sum = (currentday + offsetday);
		if (sum > maxDay) {
			int month = calendar.get(Calendar.MONTH);
			if (month == 11) {
				calendar.set(Calendar.MONTH, 0);
				calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
			} else
				calendar.set(Calendar.MONTH, month + 1);
			calendar.set(Calendar.DAY_OF_MONTH, sum - maxDay);
		} else
			calendar.set(Calendar.DAY_OF_MONTH, sum);
		return simpleDateFormat.format(calendar.getTime());
	}
}
