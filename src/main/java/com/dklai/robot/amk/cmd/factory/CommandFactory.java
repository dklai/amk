package com.dklai.robot.amk.cmd.factory;

import java.util.HashMap;
import java.util.Map;

import com.dklai.robot.amk.cmd.ClickCommand;
import com.dklai.robot.amk.cmd.Command;
import com.dklai.robot.amk.cmd.DoubleClickCommand;
import com.dklai.robot.amk.cmd.EchoCommand;
import com.dklai.robot.amk.cmd.InputCommand;
import com.dklai.robot.amk.cmd.MoveCommand;
import com.dklai.robot.amk.cmd.SleepCommand;
import com.dklai.robot.amk.cmd.TimeAtCommand;
import com.dklai.robot.amk.ex.CommandException;
import com.dklai.robot.amk.ex.CommandNotSupportException;

public class CommandFactory {
	private static Map<String, Class<? extends Command>> commands = new HashMap<String, Class<? extends Command>>();
	
	static {
		commands.put("TIMEAT", TimeAtCommand.class);
		commands.put("SLEEP", SleepCommand.class);
		commands.put("DELAY", SleepCommand.class);
		commands.put("MOVE", MoveCommand.class);
		commands.put("CLICK", ClickCommand.class);
		commands.put("DOUBLECLICK", DoubleClickCommand.class);
		commands.put("DCLICK", DoubleClickCommand.class);
		commands.put("INPUT", InputCommand.class);
		commands.put("ECHO", EchoCommand.class);
		commands.put("OUTPUT", EchoCommand.class);
		
		commands.put("时间", TimeAtCommand.class);
		commands.put("定时", TimeAtCommand.class);
		commands.put("休眠", SleepCommand.class);
		commands.put("延迟", SleepCommand.class);
		commands.put("移动", MoveCommand.class);
		commands.put("单击", ClickCommand.class);
		commands.put("双击", DoubleClickCommand.class);
		commands.put("输入", InputCommand.class);
		commands.put("输出", EchoCommand.class);
	}
	
	public static void register(String cmd, Class<? extends Command> clazz) {
		commands.put(cmd.trim().toUpperCase(), clazz);
	}
	
	public Command build(String cmdLine) {
		if (cmdLine == null || cmdLine.trim().isEmpty() || cmdLine.trim().startsWith("#") || cmdLine.trim().startsWith("//")) {
			return null;
		}
		String cmd = null;
		String args = null;
		cmdLine = cmdLine.trim();
		
		int pos = cmdLine.indexOf("#");
		if (pos != -1) {
			cmdLine = cmdLine.substring(0, pos).trim();
		}
		pos = cmdLine.indexOf("//");
		if (pos != -1) {
			cmdLine = cmdLine.substring(0, pos).trim();
		}
		
		pos = cmdLine.indexOf(" ");
		if (pos == -1) {
			cmd = cmdLine;
			args = "";
		} else {
			cmd = cmdLine.substring(0, pos).trim();
			args = cmdLine.substring(pos).trim();
		}
		Class<? extends Command> clazz = commands.get(cmd.toUpperCase());
		if (clazz == null) {
			throw new CommandNotSupportException("指令 " + cmd + " 不支持");
		}
		try {
			return clazz.getConstructor(String.class).newInstance(args);
		} catch (Exception e) {
			throw new CommandException("生成 " + cmd + " 指令异常", e);
		} 
	}
}
