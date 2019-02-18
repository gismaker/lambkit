package test.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.DataFormatException;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.lambkit.common.util.DateTimeUtils;

public class DateTimeUtilsTest {

	public static void main(String[] args) {
    	try {
    		/*
    		System.out.println("String -> Date");
    		Date[] dates = DateTimeUtils.convertToDate("2014-02-26", "2014-03-05");
			for(Date date : dates) {
				String strdate = DateFormatUtils.format(date, "yyyy-MM-dd");
				System.out.println(strdate);
			}
			*/
			System.out.println();
			System.out.println("String -> Timestamp");
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp timest = Timestamp.valueOf("2014-03-05 03:18:00.00");
			System.out.println(sdf.format(timest));
			System.out.println();
			Timestamp[] timestamps = DateTimeUtils.convertToTimestamp("2014-02-26 19:00:34.00", "2014-03-05 03:18:00.00");
			for(Timestamp timestamp : timestamps) {
				String strdate = sdf.format(timestamp);
				System.out.println(strdate);
			}
			System.out.println();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (DataFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	System.out.println("getSystemTranceDay");
    	//Date date = DateTimeUtils.getSystemTranceDay();
    	//String strdate = DateFormatUtils.format(date, "yyyy-MM-dd");
    	//System.out.println(strdate);
	}
}
