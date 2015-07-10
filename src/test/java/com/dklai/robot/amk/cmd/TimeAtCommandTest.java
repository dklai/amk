package com.dklai.robot.amk.cmd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

public class TimeAtCommandTest {

	@Test
	public static void main(String ... args) throws ParseException {
		Command command = new TimeAtCommand("20:10:30.20");
		command.doCommand(null);
		System.out.println("TimeAt:" + new Date());
		Calendar  c = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
		System.out.println("Miles:" + c.get(Calendar.MILLISECOND));
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.S", Locale.SIMPLIFIED_CHINESE);  
		
		Calendar tmp = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
		tmp.setTime(sdf.parse("00:00:01.00"));
        
        Calendar  calendar = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
        calendar.set(Calendar.HOUR_OF_DAY, tmp.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, tmp.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, tmp.get(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, tmp.get(Calendar.MILLISECOND));
		
		System.out.println(calendar.getTime());
		
		if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		System.out.println(calendar.getTime());
		
		long timeBegin = System.currentTimeMillis();
		for (int i= 0 ; i < 1000 ; i++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		long timeEnd = System.currentTimeMillis();
		
		System.out.println("TimeUse: " + (timeEnd - timeBegin));
	}
	
	@Test
	public void testTimeAt() {
	
	}
}
