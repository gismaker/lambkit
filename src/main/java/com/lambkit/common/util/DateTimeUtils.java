/**
 * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

import com.jfinal.kit.StrKit;

/**  
 * 该类主要服务于sql中基于时间的统计查询，在写sql的过程中建议不要使用to_char或者to_date等oracle函数  
 * 这样不利用索引（除非你对to_char进行了类似索引的操作  
 * ），比如：在表的logintime字段上建立了索引，但是在sql中使用to_char(logintime,'yyyy-MM-dd')  
 * 作为检索条件的时候，数据库在logintime上建立的索引就没用了。在数据量很大的时候会影响检索的速度。  
 *  提供如下方法：   
 *  1、获取当前时间（按天截取时间）  
 *  2、根据指定时间提供天、周、旬、月、季度、年的开始时间，结束时间（时间格式采java.util.Date）  
 *  3、给定字符串类型的startTime和endTime，工具类负责类型的转换（String转换成Date）   
 *  注意：  
 *  1、在sql中使用开始时间和最后时间的时候，为了保证统计数据的正确性，  
 *    sql按给出的例子组织：t.logintime >= startTime and t.loginTime <= entTime   
 *  2、时间的字符串格式采用 yyyy-MM-dd  
 *   
 */   
public final class DateTimeUtils {   
   /*
    private static SimpleDateFormat sDateFormat = new SimpleDateFormat(   
            "yyyy-MM-dd");   
   
    public static final int FIRSTTEN = 1 ;   
    public static final int MIDTEN = 2;   
    public static final int LASTTEN = 3;   
       
    public static final int FIRSTQUARTER = 1;   
    public static final int SECONDQUARTER = 2;   
    public static final int THIRDQUARTER = 3;   
    public static final int FORTHQUARTER = 4;   
    */
    
    /**
     * 逻辑： 1、检查startTime,endTime的有效性（是否为空，数据格式）， 异常处理: 1、两个参数都为空，抛出空指针异常  
     * 2、数据格式不对，直接抛出 3、一个参数为空，另一个参数格式正确的情况下，为空的参数采用系统时间，为了保证startTime <=  
     * endTime,工具类会做适当的调整 2、转换 3、返回值是个Date[2]数组，date[0] 保存startTime值,date[1]  
     * 保存startTime值，其中startTime <= endTime  
     * 
     * @param startTime
     * @param endTime
     * @return
     * @throws NullPointerException
     * @throws DataFormatException
     * @throws ParseException
     */
    public static Timestamp[] convertToTimestamp(String startTime, String endTime)   
            throws NullPointerException, DataFormatException, ParseException {   
    	Timestamp stime = new Timestamp(System.currentTimeMillis());   
        Timestamp etime = new Timestamp(System.currentTimeMillis());     
        Timestamp[] timestamps = new Timestamp[2];   
        if (StrKit.isBlank(startTime) && StrKit.isBlank(endTime)) {   
            throw new NullPointerException("两个参数不能同时为空");   
        }   
        Pattern pattern = Pattern   
        		.compile("^[1-9]\\d{3}-[01]?\\d-[0|1|2|3]?\\d [012]?\\d:[0-5]?\\d:[0-5]?\\d[.]?[0-9]?\\d$");
        if (!StrKit.isBlank(endTime)) {   
            // 先判断endTime格式是否正确   
            Matcher matcher = pattern.matcher(endTime);   
            if (matcher.matches()) {   
                etime = Timestamp.valueOf(endTime);   
            } else {   
                throw new DataFormatException(   
                        "参数endTime的格式不正确！正确的格式 yyyy-MM-dd 比如：2010-12-12！");   
            }   
        }
        if (!StrKit.isBlank(startTime)) {   
            Matcher matcher = pattern.matcher(startTime);   
            if (matcher.matches()) {   
                stime = Timestamp.valueOf(startTime);    
            } else {   
                throw new DataFormatException(   
                        "参数startTime的格式不正确！正确的格式 yyyy-MM-dd 比如：2010-12-12！");   
            }   
        }   
        if (!stime.before(etime)) {   
        	Timestamp temp = stime;   
            stime = etime;   
            etime = temp;   
            temp = null;   
        }   
        timestamps[0] = stime;   
        timestamps[1] = etime;   
        return timestamps;
    }
    
    
    /**
     * String转Timestamp，格式为yyyy-MM-dd HH:mm:ss[.f..]
     * @param stime
     * @return
     * @throws DataFormatException
     */
	public static Timestamp parseTime(String stime) throws DataFormatException {
		Pattern pattern = Pattern   
                .compile("^[1-9]\\d{3}-[01]?\\d-[0|1|2|3]?\\d [012]?\\d:[0-5]?\\d:[0-5]?\\d[.]?[0-9]?\\d$"); // 2010-12-22
        if (!StrKit.isBlank(stime)) {
            // 先判断endTime格式是否正确   
            Matcher matcher = pattern.matcher(stime);
            if (matcher.matches()) {   
                return Timestamp.valueOf(stime);
            } else {   
                throw new DataFormatException(
                        "格式不正确！正确的格式 yyyy-MM-dd HH:mm:ss[.f...]比如：2010-12-12 08:00:03.00！");   
            }   
        }
        return null;
	}
	
	public static Timestamp parse(Long stime) {
		return new Timestamp(stime);
	}
	
	/**
	 * Timestamp转String，格式自定义
	 * @param stime
	 * @param format,SimpleDateFormat
	 * @return
	 */
	public static String format(Timestamp stime, String format) {
		DateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(stime);
	}
	
	public static String formatDefault(Timestamp stime) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(stime);
	}
	
	public final static long tolong(Timestamp stime) {
		return stime.getTime();
	}
	
	public final static long tolong(Date date) {
		return date.getTime();
	}
	
	public final static long tolong(String stime) throws DataFormatException {
		return tolong(parseTime(stime));
	}
	
	public final static Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public final static long getCurrentTimeLong() {
		return System.currentTimeMillis();//tolong(new Timestamp(System.currentTimeMillis()));
	}
	
	/***
	 * long 日期转换成 yyyy-MM-dd HH:mm:ss 字符串格式
	 * @param date
	 * @return
	 */
	public final static String longToString(Long date)
	{
		return dateToString(new Date(date));
	}
	
	/***
	 * 日期转换成 yyyy-MM-dd HH:mm:ss 字符串格式
	 * @param date
	 * @return
	 */
	public final static String dateToString(Date date)
	{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	/***
	 *  String日期转化日期类型
	 * @param date
	 * @return
	 * @author saga
	 */
	public final static Date stringToDate(String date)
	{
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		} catch (ParseException e) {
			System.out.println("日期转换错误："+e.getMessage());
		}
		return new Date();
	}
	
	/***
	 * java.util.date日期转换成 Timestamp
	 * @param date
	 * @return
	 * @author saga
	 */
	public final static Timestamp dateToTimestamp(Date date)
	{
		try {
			return new Timestamp(date.getTime());
		} catch (Exception e) {
			System.out.println("日期转换错误："+e.getMessage());
		}
		return new Timestamp(System.currentTimeMillis());
	}
	
}  

