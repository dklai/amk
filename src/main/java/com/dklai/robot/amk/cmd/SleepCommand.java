package com.dklai.robot.amk.cmd;

import java.awt.Robot;

import com.dklai.robot.amk.ex.CommandException;

public class SleepCommand implements Command{
	private long millis;
	
	public SleepCommand(String args) {
		String time = args.trim().toLowerCase();
		try {
			if (time.endsWith("h")){
				time = time.replace("h", "");
				millis = Long.parseLong(time) * 60 * 60 * 1000;
			} else if (time.endsWith("m")) {
				time = time.replace("m", "");
				millis = Long.parseLong(time) * 60 * 1000;
			} else if (time.endsWith("s")) {
				time = time.replace("s", "");
				millis = Long.parseLong(time) * 1000;
			} else if (time.endsWith("小时")){
				time = time.replace("小时", "");
				millis = Long.parseLong(time) * 60 * 60 * 1000;
			} else if (time.endsWith("分钟")) {
				time = time.replace("分钟", "");
				millis = Long.parseLong(time) * 60 * 1000;
			} else if (time.endsWith("秒")) {
				time = time.replace("秒", "");
				millis = Long.parseLong(time) * 1000;
			} else {
				millis = Long.parseLong(time);
			}
		} catch (NumberFormatException e) {
			throw new CommandException("生成 Sleep 指令异常", e);
		}
	}
	
	public SleepCommand(long millis) {
		this.millis = millis;
	}

	public void doCommand(Robot robot) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new CommandException("执行 Sleep 指令异常", e);
		}
	}

}
