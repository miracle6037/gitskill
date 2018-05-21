package com.didispace;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateUtil {
	int offSetHour;
	int offSetMinute;
	
	@org.junit.Test
	public void test() {
		String dateStr = "2018-05-21T21:12:00.000-08:00";
		String dateStr1 = "2018-05-21T21:12:00.000+0800";
		System.out.println(parseString2LocalDatetTime(dateStr, "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
		System.out.println(parseString2LocalDatetTime(dateStr1, "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
	}
	
	public LocalDateTime parseString2LocalDatetTime(String dateStr, String formatter) {
		String offSet;
		int lastPlusSign = dateStr.lastIndexOf("+");
		int countMuinsSign = dateStr.split("-").length;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
		LocalDateTime formatdDate = LocalDateTime.parse(dateStr, dateTimeFormatter);

		if(lastPlusSign != -1) {
			offSet = dateStr.substring(lastPlusSign+1, dateStr.length());
			getHourAndMinuteFromOffSet(offSet);
			return formatdDate.minusHours(offSetHour).minusMinutes(offSetMinute);
		}
		// 2018/05/21T21:12:00.000-08:00
		// 2018-05-21T21:12:00.000-08:00
		if (countMuinsSign == 2 || countMuinsSign == 4) {
			offSet = dateStr.substring(dateStr.lastIndexOf("-")+1, dateStr.length());
			getHourAndMinuteFromOffSet(offSet);
			return formatdDate.plusHours(offSetHour).plusMinutes(offSetMinute);
		}
		return formatdDate;
	}
	
	public void getHourAndMinuteFromOffSet(String offSet){
		if (offSet.lastIndexOf(":") != -1) {
			offSetHour = Integer.parseInt(offSet.split(":")[0]);
			offSetMinute = Integer.parseInt(offSet.split(":")[1]);
		} else {
			offSetHour = Integer.parseInt(offSet.substring(0, 2));
			offSetMinute = Integer.parseInt(offSet.substring(2, 4));
		}
	}
	
}
