package com.sfxie.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.GregorianCalendar;
import java.util.Map;

public class DateHelper {
	/**yyyy-MM-dd HH:mm:ss*/
	public static String format_ss = "yyyy-MM-dd HH:mm:ss";
	/**yyyyMMddHH:mm:ss*/
	public static String format_all = "yyyyMMddHHmmss";
	/**yyyy-MM-dd HH:mm*/
	public static String format_mm = "yyyy-MM-dd HH:mm";
	/**yyyy-MM-dd HH*/
	public static String format_hh = "yyyy-MM-dd HH";
	/**yyyy-MM-dd*/
	public static String format_no = "yyyy-MM-dd";
	
	
	private static final DateFormat[] ACCEPT_DATE_FORMATS = {  
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),  
        new SimpleDateFormat("yyyy-MM-dd HH:mm"),  
        new SimpleDateFormat("yyyy-MM-dd HH") ,  
        new SimpleDateFormat("yyyy-MM-dd")
    };
	
	/**
	  * 将日期类型字符串自动转化成日期
	  * @TODO	
	  * @author 	xieshengfeng
	  * @email  	xsfcy@126.com
	  * @since 	上午10:24:25 2015年8月17日
	  * @param dateString
	  * @return	
	  *
	  */
	public static Date parseDate(String dateString){
		if(null==dateString || dateString.equals("")){
			return null;
		}
		for (DateFormat format : ACCEPT_DATE_FORMATS) {   
           try {   
               return format.parse(dateString);//遍历日期支持格式，进行转换   
           } catch(Exception e) {   
               continue;   
           }   
       }
		return null;   
	}
	/**
	 * 返回中文格式的当前时间
	 * 
	 * @return [yyyy年MM月dd日 a hh:mm:ss]
	 */
	public static String getChineseNow() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日 a HH:mm:ss");
		Date nowc = new Date();
		String pid = formatter.format(nowc);
		return pid;
	}

	/**
	 * 返回中文格式的当前日期
	 * 
	 * @return [yyyy-mm-dd]
	 */
	public static String getShortNow() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date nowc = new Date();
		String pid = formatter.format(nowc);
		return pid;
	}
	/**
	 * 返回中文格式的当前日期
	 * 
	 * @return [yyyy-mm-dd]
	 */
	public static String getShortNow(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String pid = formatter.format(d);
		return pid;
	}
	/**
	 * 返回当天的日期
	 * 
	 * @return [yyyyMMdd]
	 */
	public static String getShortNow1() {
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date nowc = new Date();
		String pid = formatter.format(nowc);
		return pid;
	}

	/**
	 * 返回短日期格式
	 * 
	 * @return [yyyy-mm-dd]
	 */
	public static String formatShort(String strDate) {
		String ret = "";
		if (strDate != null && !"1900-01-01 00:00:00.0".equals(strDate)
				&& strDate.indexOf("-") > 0) {
			ret = strDate;
			if (ret.indexOf(" ") > -1)
				ret = ret.substring(0, ret.indexOf(" "));
		}
		return ret;
	}

	/**
	 * 返回时间格式
	 * 
	 * @return [hh-mi-ss]
	 */
	public static String formatTime(String strDate) {
		String ret = "";
		if (strDate != null && !"1900-01-01 00:00:00.0".equals(strDate)) {
			ret = strDate;
			if (ret.indexOf(" ") > -1)
				ret = ret.substring(ret.indexOf(" ") + 1, ret.length());

		}
		return ret;
	}

	/**
	 * 返回时间格式
	 * 
	 * @return [hh-mi]
	 */
	public static String formatShortTime(String strDate) {
		String ret = "";
		if (strDate != null && !"1900-01-01 00:00:00.0".equals(strDate)) {
			ret = strDate;
			if (ret.indexOf(" ") > -1)
				ret = ret.substring(ret.indexOf(" ") + 1, 16);

		}
		return ret;
	}

	/**
	 * 返回标准格式的当前时间
	 * 
	 * @return [yyyy-MM-dd]
	 */
	public static String getNowYYYYMMDD() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date nowc = new Date();
		String pid = formatter.format(nowc);
		return pid;
	}
	/**
	 * 返回标准格式的当前时间
	 * 
	 * @return [yyyy-MM-dd 00:00:00]
	 */
	public static Date getDateStart() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date nowc = new Date();
		String pid = formatter.format(nowc);
		return new Date(pid+" 00:00:00");
	}
	/**
	 * 返回标准格式的当前时间
	 * 
	 * @return [yyyy-MM-dd 23:59:59]
	 */
	public static Date getDateEnd() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date nowc = new Date();
		String pid = formatter.format(nowc);
		return new Date(pid+" 23:59:59");
	}
	/**
	 * 返回标准格式的当前时间
	 * 
	 * @return [yyyy-MM-dd hh:mm:ss]
	 */
	public static String getNow() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowc = new Date();
		String pid = formatter.format(nowc);
		return pid;
	}
	public static Date getNowDate() {
		Date nowc = new Date();
		return nowc;
	}

	/**
	 * 返回中文格式的当前日期
	 * 
	 * @return [yyyymmdd]
	 */
	public static String getNoBeepNow() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date nowc = new Date();
		String pid = formatter.format(nowc);
		return pid;
	}

	/**
	 * 返回当前年份
	 * 
	 * @return [yyyy]
	 */

	public static String getYear() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date nowc = new Date();
		String pid = formatter.format(nowc);
		return pid;
	}

	/**
	 * 返回当前月份
	 * 
	 * @return [MM]
	 */

	public static String getMonth() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		Date nowc = new Date();
		String pid = formatter.format(nowc);
		return pid;
	}

	/**
	 * 返回当前日
	 * 
	 * @return [dd]
	 */
	public static String getDay() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		Date nowc = new Date();
		String pid = formatter.format(nowc);
		return pid;
	}

	/**
	 * 返回当前时间24小时制式
	 * 
	 * @return [H:mm:ss]
	 */

	public static String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("H:mm:ss");
		Date nowc = new Date();
		String pid = formatter.format(nowc);
		return pid;
	}

	/**
	 * 返回当前时间24小时制式
	 * 
	 * @return [H:mm]
	 */

	public static String getTimeBykm() {
		SimpleDateFormat formatter = new SimpleDateFormat("H:mm");
		Date nowc = new Date();
		String pid = formatter.format(nowc);
		return pid;
	}

	/**
	 * 格式化中文日期短日期格式
	 * 
	 * @param gstrDate
	 *            输入欲格式化的日期
	 * @return [yyyy年MM月dd日]
	 */

	public static String formatShortDateC(Date gstrDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		String pid = formatter.format(gstrDate);
		return pid;
	}

	/**
	 * 格式化中文日期长日期格式
	 * 
	 * @param gstrDate
	 *            输入欲格式化的日期
	 * @return [yyyy年MM月dd日 H:mm:ss]
	 */

	public static String formatLongDateC(Date gstrDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日 HH:mm:ss");
		String pid = formatter.format(gstrDate);
		return pid;
	}

    /**
     * 格式化中文日期长日期格式
     *
     * @param gstrDate
     *            输入欲格式化的日期
     * @return [yyyy年MM月dd日 H:mm:ss]
     */

    public static String formatDate(Date gstrDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        String pid = formatter.format(gstrDate);
        return pid;
    }
	/**
	 * 格式化标准日期短日期格式
	 * 
	 * @param gstrDate
	 *            输入欲格式化的日期
	 * @return [yyyy-MM-dd]
	 */
	public static String formatShortDate(Date gstrDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		String pid = formatter.format(gstrDate);
		return pid;
	}



	/**
	 * 格式化标准日期长日期格式
	 * 
	 * @param gstrDate
	 *            输入欲格式化的日期
	 * @return [yyyy-MM-dd H:mm:ss]
	 */
	public static String formatLongDate(Date gstrDate) {
		if(null==gstrDate){
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String pid = formatter.format(gstrDate);
		return pid;
	}

    /**
     * 格式化标准日期长日期格式
     *
     * @param dateStr
     *            输入欲格式化的日期
     * @return [yyyy-MM-dd H:mm:ss]
     */
    public static Date parseLongDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
	 * 格式化用户定义的日期格式
	 * 
	 * @param gstrDate
	 *            输入欲格式化的日期
	 * @param gstrType
	 *            用户自定义的日期格式
	 * @return [用户定义格式的日期]
	 */
	public static String formatDateType(Date gstrDate, String gstrType) {
		SimpleDateFormat formatter = new SimpleDateFormat(gstrType);
		String pid = formatter.format(gstrDate);
		return pid;
	}

	/**
	 * 格式化字符串为Date类型
	 * 
	 * @param strdate
	 *            字符串
	 * @return 格式化后的Date
	 */
	public static Date getDate(String strdate) {
		return java.sql.Date.valueOf(strdate);
	}

	/**
	 * 格式化字符串为Date类型
	 * 
	 * @param strdatetime
	 *            字符串,"yyyy-mm-dd hh:mi:ss"
	 * @return 格式化后的Date
	 */
	public static Date getDateTime(String strdatetime) {
		return java.sql.Timestamp.valueOf(strdatetime);
	}

    public static Date getShortDateTime(String date){
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date getShortDate() throws Exception {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Date day = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        return day;
    }
	/**
	 * 比较两个日期，精确到天。
	 * 
	 * @param d1
	 *            日期1
	 * @param d2
	 *            日期2
	 * @return 比较结果
	 *         <p>
	 *         0 d1=d2
	 *         </p>
	 *         <p>
	 *         1 d1>d2
	 *         </p>
	 *         <p>
	 *         -1 d1小于d2
	 *         </p>
	 * 
	 */
	public static int compareToByDay(Date d1, Date d2) {
		return DateHelper.formatShortDate(d1).compareTo(
				DateHelper.formatShortDate(d2));
	}

	/**
	 * 在当前日期上添加days的天数
	 * 
	 * @param srcdate
	 * @param days
	 * @return
	 */
	public static Date addByDate(Date srcdate, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(srcdate);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 格式化字符串为Date类型
	 * 
	 * @param strdatetime
	 *            字符串,"yyyy-MM-dd hh:mi:ss"
	 * @return 格式化后的Date
	 */
	public static java.sql.Date stringToSqlDate(String strdatetime)
			throws Exception {
		java.sql.Date date = null;
		try {
			if (strdatetime == null || strdatetime.trim().length() < 1)
				return null;
			date = date.valueOf(strdatetime);
		} catch (Exception e) {
			throw new Exception("非法的时间格式,应该是yyyy-MM-dd");
		}
		return date;
	}

	/**
	 * 将yyyymmdd格式转换为yyyy-mm-dd格式
	 */
	public static String formatNormal(String sDate) {
		if (sDate != null && sDate.length() == 8) {
			sDate = sDate.substring(0, 4) + "-" + sDate.substring(4, 6) + "-"
					+ sDate.substring(6);
		}
		return sDate;
	}

	/**
	 * 将字符串格式转换为sqldate
	 * 
	 * @throws Exception
	 */
	public static java.sql.Date getSqlDate(String stime) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.sql.Date sqlDate = null;
		try {
			Date date = sdf.parse(stime);
			sqlDate = new java.sql.Date(date.getTime());
		} catch (Exception e) {
			throw new Exception("时间转换出现异常，请检查时间格式。");
		}
		return sqlDate;
	}
	
    /**
     * 根据字符串日期 datestr, 日期模板获得 Date 时间:Mar 16, 2009 3:37:41 PM
     * 
     * @param datestr
     * @param format 如:yyyyMMdd,yyyy-MM-dd
     * @return
     */
    public static Date stringToDateMain(String datestr, String format) {
        if (null == datestr || datestr.equals(""))
            return null;
        if (0 < datestr.indexOf("CST")||0<datestr.indexOf("GMT")) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",
                    Locale.US);
                Date d = sdf.parse(datestr);
                return d;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        try {
            return sdf.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

	/**
	 * 返回中文格式的当前时间
	 * 
	 * @return [yyyy年MM月dd日 a hh时mm分ss秒]
	 */
	public static String getChineseTime() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日 a HH时mm分ss秒");
		Date nowc = new Date();
		String pid = formatter.format(nowc);
		return pid;
	}

	/**
	 * 获取date日期后的n天的日期
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static String getSomeday(String date, int n) {

		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date oneday = null;
		try {
			oneday = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(oneday);
		ca.set(Calendar.DAY_OF_YEAR, ca.get(Calendar.DAY_OF_YEAR) + n);
		Date somday = new Date(ca.getTimeInMillis());

		return format.format(somday);

	}

	/**
	 * 获取date日期后的n天的日期
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static String getSomeTimeday(String date, int n) {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date oneday = null;
		try {
			oneday = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(oneday);
		ca.set(Calendar.DAY_OF_YEAR, ca.get(Calendar.DAY_OF_YEAR) + n);
		Date somday = new Date(ca.getTimeInMillis());

		return format.format(somday);

	}

	/**
	 * 返回明天的日期
	 * 
	 * @return yyyyMMdd
	 */
	public static String getNextDay() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.set(Calendar.DAY_OF_YEAR, ca.get(Calendar.DAY_OF_YEAR) + 1);
		Date dt = new Date(ca.getTimeInMillis());
		DateFormat ft = new SimpleDateFormat("yyyyMMdd");
		String nextDay = ft.format(dt);
		return nextDay;
	}

	/**
	 * 获取给定时间和当前时间的间隔天数
	 * 
	 * @param date
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static int getDays(Date date) {
		if (date.after(new Date())) {
			Calendar ca = Calendar.getInstance();
			long currDays = ca.getTimeInMillis();
			ca.setTime(date);
			long givenDays = ca.getTimeInMillis();
			int dis = (int) ((givenDays - currDays) / (24 * 3600 * 1000)) + 1;
			return dis;
		}
		return 0;
	}

	/**
	 * 获取给定时间和当前时间的间隔天数
	 * 
	 * @param date
	 *            yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	public static int getDay(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date now = null;
		try {
			now = df.parse(df.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date.after(now)) {
			Calendar ca = Calendar.getInstance();
			ca.setTime(now);
			long currDays = ca.getTimeInMillis();
			ca.setTime(date);
			long givenDays = ca.getTimeInMillis();
			int dis = (int) ((givenDays - currDays) / (24 * 3600 * 1000));
			return dis;
		}
		return 0;
	}

    public static Date addMinutse(Date date,int mins){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE,mins );
        return calendar.getTime();
    }

    public static Date addHours(Date date,int hours){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR,hours);
        return calendar.getTime();
    }
    
    /**
     * 将指定格式字符串转换成时间格式
     * @param strDate 要转换的字符串
     * @param formatType 时间格式，如：yyyy-MM-dd hh:mm:ss
     * @return
     * @throws Exception
     */
    public static Date stringToDate (String strDate,String formatType) throws Exception {
    	Date date = null;
    	if (strDate != null && !"".equals(strDate)) {
    		date = new SimpleDateFormat(formatType).parse(strDate);
    	}
    	return date;
    }
    
	public static Date getStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
    
	/** 
	 * 获取一天最后一刻
	 */
	public static Date getEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));
		return calendar.getTime();
	}
	
	/** 
	 *	取月末
	 */
	public static Date getMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//取最后一号
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		//取最后时刻
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
		return calendar.getTime();
	}
	
	/** 
	 *	取月始
	 */
	public static Date getMonthBegin(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//取第一天
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		//取开始时刻
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
		return calendar.getTime();
	}
	
	public static Date getStartDate (Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
    /**
     * 得到指定时间的往前或往后 n天/n周/n月/n季度/n年开始时间
     * @param date 指定时间
     * @param amount 往前或往后 数量
     * @param type 类型：1-天; 2-周; 3-月; 4-季度; 5-年
     * @return
     */
    public static Date getStartDate (Date date, int amount, int type) {
    	Calendar calendar = new GregorianCalendar();  
    	calendar.setTime(date);
    	calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		if (type == 1) {
			calendar.add(Calendar.DATE, amount);
		} else if (type == 2) {
			calendar.set(Calendar.DAY_OF_WEEK, 1);
			calendar.add(Calendar.DATE, amount * 7);
		} else if (type == 3) {
			calendar.set(Calendar.DAY_OF_MONTH, 1);   
			calendar.add(Calendar.MONTH, amount);
		} else if (type == 4) {
			calendar.set(Calendar.DAY_OF_MONTH, 1);  
			int month = calendar.get(Calendar.MONTH);
			int months[] = { 1, 4, 7, 10 };  
	        if (month >= 2 && month <= 4)  
	        	month = months[0];  
	        else if (month >= 5 && month <= 7)  
	        	month =  months[1];  
	        else if (month >= 8 && month <= 10)  
	        	month =  months[2];  
	        else  
	        	month =  months[3];
			calendar.set(Calendar.MONTH, month + 3 * amount);
		} else if (type == 5) {
			calendar.set(Calendar.DAY_OF_MONTH, 1);  
			calendar.set(Calendar.MONTH, 1);
			calendar.add(Calendar.YEAR, amount);
		}
		return calendar.getTime();
    }
    /**
	 * 字符串转换为日期
	 * 
	 * @author wangbaoyin
	 * @param String strDate：日期的字符串形式
	 * @param String format：转换格式
	 * @return String
	 * @throws
	 */
	public static Date strToDate(String strDate, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 字符串转换为日期
	 * 
	 * @author wangbaoyin
	 * @param String strDate：日期的字符串形式
	 * @param String format：转换格式
	 * @return String
	 * @throws
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(get_format(strDate));
		Date date = null;
		try {
			date = dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	private static String get_format(String value){
		String format_ret = "";
		if(value==null || value.trim().equals("")){
			return format_ret;
		}
		
		int len = value.length();
		if(len==format_ss.length()){
			format_ret = format_ss;
		}
		else if(len==format_mm.length()){
			format_ret = format_mm;
		}
		else if(len==format_hh.length()){
			format_ret = format_hh;
		}
		else if(len==format_no.length()){
			format_ret = format_no;
		}
		return format_ret;
	}
	/**
	 * 循环时间间隔内的天数
	 * @TODO	
	 * @author 	xieshengfeng
	 * @since 	上午10:27:51 2015-8-4
	 * @param startTime
	 * 			开始时间
	 * @param endTime
	 * 			结束时间
	 * @param doLoopDatePeriod	
	 * 			动作处理接口
	 *
	 */
	public static void loopDatePeriod(Date startTime,Date endTime,DoLoopDatePeriod doLoopDatePeriod){
		Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(startTime);
		end.setTime(endTime);
		end.add(Calendar.DAY_OF_MONTH,1);
        while(start.before(end)){
        	doLoopDatePeriod.action(start);
            start.add(Calendar.DAY_OF_MONTH,1);
        }
	}
	/**
	 * 判断时间是否是某一范围内
	 * @TODO	
	 * @param compatedDate
	 * 			被比较时间
	 * @param startDate
	 * 			开始时间
	 * @param endDate
	 * 			结束时间
	 * @return	
	 *
	 */
	public static boolean between(Date compatedDate,Date startDate,Date endDate){
		if(startDate.getTime()<=compatedDate.getTime() && compatedDate.getTime()<=endDate.getTime()){
			return true;
		}
		return false;
	}
	/**
	 * 获取两个时间的比较字符串
	 * @TODO	
	 * @author 	xieshengfeng
	 * @since 	下午4:07:25 2015-7-29
	 * @param date1
	 * @param date2
	 * @return	返回类似"359天1小时2分10秒"字符串
	 *
	 */
	public static String getCompareString(Date date1,Date date2){
		long l;
		if(date1.after(date2)){
			l = date1.getTime()-date2.getTime();
		}else{
			l = date2.getTime()-date1.getTime();
		}
		
		long day=l/(24*60*60*1000);
		long hour=(l/(60*60*1000)-day*24);
		long min=((l/(60*1000))-day*24*60-hour*60);
		long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		
/*		Calendar calendar = new GregorianCalendar();  
    	calendar.set(Calendar.MINUTE, Integer.parseInt(new String(""+min)));
		calendar.set(Calendar.SECOND, Integer.parseInt(new String(""+s)));
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(new String(""+hour)));
		calendar.set(Calendar.DAY_OF_YEAR, Integer.parseInt(new String(""+day)));
		
		Date ddd = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日 a HH:mm:ss");
		System.out.println(formatter.format(ddd));*/
		return ""+day+"天"+hour+"小时"+min+"分"+s+"秒";
		
	}
	
	public static Map getWeekDay(Date date) {
		Map<String, Date> map = new HashMap<String, Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		map.put("mon", cal.getTime());
//		print("********得到本周一的日期*******" + df.format(cal.getTime()));
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		map.put("sun", cal.getTime());
//		print("********得到本周天的日期*******" + df.format(cal.getTime()));
		return map;
		
	}
	/**
	 * 分钟转化成秒
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午3:40:57 2016年3月24日
	 * @example
	 * @param minute
	 * @return	
	 *
	 */
	public static int minute2Secord(float minute){
		return (int) Float.parseFloat(String.valueOf(minute*60));
	}
	/**
	 * 小时转化成秒
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午3:41:10 2016年3月24日
	 * @example
	 * @param minute
	 * @return	
	 *
	 */
	public static int hour2Secord(float minute){
		return (int) Float.parseFloat(String.valueOf(minute*60*60));
	}
	public interface DoLoopDatePeriod{
		public void action(Calendar currentCalendar);
	}
	
	public static void main(String[] args) throws ParseException {
		System.out.println(hour2Secord(1.0f));
	}
	
}
