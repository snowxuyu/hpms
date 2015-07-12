package com.hpms.util;

import java.util.Calendar;

import com.cn.exception.SystemException;

/**
 * 
 * 类名：DateUtil <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月15日 <br>
 * 描述：日期工具类 <br>
 */
public class DateUtil {
	
	private static Calendar calendar = Calendar.getInstance();
	private static Calendar calendarChange = Calendar.getInstance();
	
	private static String[] weeks = {"","日", "一", "二", "三", "四", "五", "六"};
	
	/**
	 * 根据当前年月判断有多少天
	 * @param year
	 * @param month
	 * @return
	 */
	public static Integer getThisMonthDays(Integer year, Integer month) {
		Integer days = null;
		switch (month) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            days = 31;
            break;
        case 2:
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            	days = 29;
                break;
            } else {
            	days = 28;
                break;
            }
        case 4:
        case 6:
        case 9:
        case 11:
        	days = 30;
            break;
        default:
            new SystemException("输入不正确");
            break;
        }
		return days;
	}
	
	public static Integer getThisMonthDays(String year, String month) {
		Integer _year = Integer.parseInt(year);
		Integer _month = Integer.parseInt(month);
		return getThisMonthDays(_year, _month);
	}
	
	/**
	 * 获取当年
	 * @return
	 */
	public static Integer getYear() {
		return calendar.get(Calendar.YEAR);
	}
	
	/**
	 * 根据年、月、日获取星期
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public static String getWeek(Integer year, Integer month, Integer date) {
		calendarChange.set(year, month-1, date);
		int i = calendarChange.get(Calendar.DAY_OF_WEEK);
		return weeks[i];
		
	}
	
	/**
	 * 根据年、月、日获取星期
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public static String getWeek(String year, String month, String date) {
		return getWeek(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
	}
	
	/**
	 * 根据年月获取指定月的日期和星期
	 * @param year
	 * @param month
	 * @return
	 */
	public static String[][] getDateAndWeeks(Integer year, Integer month) {
		calendarChange.set(year, month-1, 1);
		int start = calendarChange.get(Calendar.DAY_OF_WEEK);
		Integer days = getThisMonthDays(year, month);
		String[][] dateAndWeeks = new String[days][2];
		for (int i = 0; i < dateAndWeeks.length; i++) {
			dateAndWeeks[i][0] = String.valueOf(i+1);
			dateAndWeeks[i][1] = weeks[start];
			if (start == 7) {
				start = 0;
			}
			start++;
		}
		return dateAndWeeks;
	}
	
	/**
	 * 根据年月获取指定月的日期和星期
	 * @param year
	 * @param month
	 * @return
	 */
	public static String[][] getDateAndWeeks(String year, String month) {
		return getDateAndWeeks(Integer.parseInt(year), Integer.parseInt(month));
	}
	
	public static void main(String[] args) {
		String[][] dateAndWeeks = getDateAndWeeks(2015, 6);
		for (String[] s : dateAndWeeks) {
			System.out.print(s[0]+",");
		}
		System.out.println();
		for (String[] s : dateAndWeeks) {
			System.out.print(s[1]+",");
		}
	}
}
