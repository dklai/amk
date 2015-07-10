package com.dklai.robot.amk.cmd;

import java.awt.Robot;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.dklai.robot.amk.ex.CommandException;

public class TimeAtCommand implements Command {
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss.S", Locale.SIMPLIFIED_CHINESE);  
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss", Locale.SIMPLIFIED_CHINESE);  
	
	private long miles;
	
	public TimeAtCommand(String args) {
		Calendar tmp = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
		try {
			if (args.contains(".")) {
				tmp.setTime(sdf1.parse(args.trim()));
			} else {
				tmp.setTime(sdf2.parse(args.trim()));
			}
		} catch (ParseException e) {
			throw new CommandException("生成 TimeAt 指令异常", e);
		}
        
        Calendar  calendar = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
        calendar.set(Calendar.HOUR_OF_DAY, tmp.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, tmp.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, tmp.get(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, tmp.get(Calendar.MILLISECOND));
		
		if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		miles = calendar.getTimeInMillis();
	}
	
	public TimeAtCommand(long miles) {
		this.miles = miles;
	}

	public void doCommand(Robot robot) {
		while(true) {
			long sleep = 10000;
			long diff = miles - System.currentTimeMillis();
			if (diff < 2) {
				break;
			} else if (diff < 20) {
				sleep = 1;
			} else if (diff < 200) {
				sleep = 10;
			} else if (diff < 2000) {
				sleep = 100;
			} else if (diff < 20000) {
				sleep = 1000;
			} else {
				sleep = 10000;
			}
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
