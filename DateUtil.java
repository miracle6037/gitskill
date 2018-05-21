package com.didispace;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	private static int offSetHour;
	private static int offSetMinute;

	@org.junit.Test
	public void test() {
		String dateStr = "2018-05-21T21:12:00.000-08:00";
		String dateStr1 = "2018-05-21T21:12:00.000+0800";
		System.out.println(parseString2LocalDatetTime(dateStr, "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
		System.out.println(parseString2LocalDatetTime(dateStr1, "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
	}

	public static LocalDateTime parseString2LocalDatetTime(String stringDate, String formatter) {
		String offSet;
		int lastPlusSignIndex = stringDate.lastIndexOf("+");
		int countMuinsSign = stringDate.split("-").length;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
		LocalDateTime formatdDate = LocalDateTime.parse(stringDate, dateTimeFormatter);

		if (lastPlusSignIndex != -1) {
			offSet = stringDate.substring(lastPlusSignIndex + 1, stringDate.length());
			getHourAndMinuteFromOffSet(offSet);
			return formatdDate.minusHours(offSetHour).minusMinutes(offSetMinute);
		}
		// 2018/05/21T21:12:00.000-08:00
		// 2018-05-21T21:12:00.000-08:00
		if (countMuinsSign == 2 || countMuinsSign == 4) {
			offSet = stringDate.substring(stringDate.lastIndexOf("-") + 1, stringDate.length());
			getHourAndMinuteFromOffSet(offSet);
			return formatdDate.plusHours(offSetHour).plusMinutes(offSetMinute);
		}
		return formatdDate;
	}

	public static void getHourAndMinuteFromOffSet(String offSet) {
		if (offSet.lastIndexOf(":") != -1) {
			offSetHour = Integer.parseInt(offSet.split(":")[0]);
			offSetMinute = Integer.parseInt(offSet.split(":")[1]);
		} else {
			offSetHour = Integer.parseInt(offSet.substring(0, 2));
			offSetMinute = Integer.parseInt(offSet.substring(2, 4));
		}
	}

}
