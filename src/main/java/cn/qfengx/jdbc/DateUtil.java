package cn.qfengx.jdbc;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	//Date与Date互转
	/**
	 * java.util.Date转java.sql.Date(date)
	 * @param udate
	 * @return
	 */
	public static java.sql.Date udateTosdate(java.util.Date udate) {
		return new java.sql.Date(udate.getTime());
	}
	
	/**
	 * java.util.Date转java.sql.Timestamp(datetime)
	 * @param udate
	 * @return
	 */
	public static java.sql.Timestamp udateTosdatetime(java.util.Date udate) {
		return new java.sql.Timestamp(udate.getTime());
	}

	/**
	 * java.sql.Date(date)转java.util.Date
	 * @param sdate
	 * @return
	 */
	public static java.util.Date sdateToudate(java.sql.Date sdate) {
		return new java.util.Date(sdate.getTime());
	}
	
	/**
	 * java.sql.Timestamp(datetime)转java.util.Date
	 * @param sdatetime
	 * @return
	 */
	public static java.util.Date sdatetimeToudate(java.sql.Timestamp sdatetime) {
		return new java.util.Date(sdatetime.getTime());
	}
	
	/**
	 * java.sql.Timestamp(datetime)转java.sql.Date(date)
	 * @param sdatetime
	 * @return
	 */
	public static java.sql.Date sdatetimeTosdate(java.sql.Timestamp sdatetime) {
		return new java.sql.Date(sdatetime.getTime());
	}
	
	/**
	 * java.sql.Date(date)转java.sql.Timestamp(datetime)
	 * @param sdate
	 * @return
	 */
	public static java.sql.Timestamp sdateTosdatetime(java.sql.Date sdate) {
		return new java.sql.Timestamp(sdate.getTime());
	}
	
	//Date与String互转
	/**
	 * java.sql.Date(date)转字符串(yyyy-MM-dd)
	 * @param sd
	 * @return
	 */
 	public static String sdateTostrdate(java.sql.Date sd) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(sd);
	}
	
	
 	/**
	 * java.util.Date转字符串(yyyy-MM-dd)
	 * @param udate
	 * @return
	 */
	public static String udateTostrdate(java.util.Date udate) {
		return new SimpleDateFormat("yyyy-MM-dd").format(udate);
	}
	
	
	/**
	 * java.util.Date转字符串(yyyy-MM-dd HH:mm:ss)
	 * @param udate
	 * @return
	 */
	public static String udateTostrdatetime(java.util.Date udate) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(udate);
	}
	
	
	/**
	 * java.sql.Timestamp(datetime)转字符串(yyyy-MM-dd)
	 * @param sdatetime
	 * @return
	 */
	public static String sdatetimeTostrdate(java.sql.Timestamp sdatetime) {
		return new SimpleDateFormat("yyyy-MM-dd").format(sdatetime);
	}
	
	
	/**
	 * java.sql.Timestamp(datetime)转字符串(yyyy-MM-dd HH:mm:ss)
	 * @param sdatetime
	 * @return
	 */
	public static String sdatetimeTostrdatetime(java.sql.Timestamp sdatetime) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sdatetime);
	}
	
	
	/**
	 * 字符串(yyyy-MM-dd)转java.sql.Date(date)
	 * @param strdate
	 * @return
	 * @throws ParseException 
	 */
	public static java.sql.Date strdateTosdate(String strdate) throws ParseException {
		return udateTosdate(new SimpleDateFormat("yyyy-MM-dd").parse(strdate));
	}
	
	
	/**
	 * 字符串(yyyy-MM-dd)转java.util.Date
	 * @param strdate
	 * @return
	 * @throws ParseException 
	 */
	public static java.util.Date strdateToudate(String strdate) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(strdate);
	}
	
	
	/**
	 * 字符串(yyyy-MM-dd HH:mm:ss)转java.util.Date
	 * @param strdatetime
	 * @return
	 * @throws ParseException 
	 */
	public static java.util.Date strdatetimeToudate(String strdatetime) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strdatetime);
	}
	
	
	/**
	 * 字符串(yyyy-MM-dd)转java.sql.Timestamp(datetime)
	 * @param strdate
	 * @return
	 * @throws ParseException 
	 */
	public java.sql.Timestamp strdateTosdatetime(String strdate) throws ParseException {
		return udateTosdatetime(new SimpleDateFormat("yyyy-MM-dd").parse(strdate));
	}
	
	
	/**
	 * 字符串(yyyy-MM-dd HH:mm:ss)转java.sql.Timestamp(datetime)
	 * @param strdatetime
	 * @return
	 * @throws ParseException 
	 */
	public java.sql.Timestamp strdatetimeTosdatetime(String strdatetime) throws ParseException {
		return udateTosdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strdatetime));
	}
	

}















