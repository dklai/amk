package com.dklai.robot.amk;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.dklai.robot.amk.cmd.Command;
import com.dklai.robot.amk.cmd.factory.CommandFactory;

public class AMK {

	public static void main(String[] args) {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			System.err.println("不支持键、鼠控制");
			System.exit(1); // 系统不支持键鼠控制
		}
		
		InputStream in = null;
		try {
			if (System.in.available() > 0) {
				in = System.in;
			}
		} catch (IOException e) {
			System.err.println("命令行管道不可读");	
			System.exit(2); // 命令行管道不可读
		}
		
		if (in == null && args.length > 0) {
			File cmdFile = new File(args[0]);
			if (cmdFile.exists() && cmdFile.isFile()) {
				try {
					in = new FileInputStream(cmdFile);
				} catch (FileNotFoundException e) {
					System.err.println("命令文件读取异常:" + args[0]);
					System.exit(3); // 命令文件读取异常
				}
			} else {
				System.err.println("不存在的命令文件：" + args[0]);
				System.exit(4); // 命令文件读取异常
			}
		}
		
		if (in == null) { // 默认文件
			String fileName = "amk.txt";
			File cmdFile = new File(fileName);
			if (cmdFile.exists() && cmdFile.isFile()) {
				try {
					in = new FileInputStream(cmdFile);
				} catch (FileNotFoundException e) {
					System.err.println("命令文件读取异常:" + fileName);
					System.exit(3); // 命令文件读取异常
				}
			} else {
				System.err.println("当前目录不存在的命令文件：" + fileName);
				System.exit(4); // 命令文件读取异常
			}
		}
		
		CommandFactory factory = new CommandFactory();
		List<Command> commands = new ArrayList<Command>();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.defaultCharset()));
		String cmdLine = null;
		try {
			while ( (cmdLine = reader.readLine())!=null) {
				Command command = factory.build(cmdLine);
				if (command != null) {
					commands.add(command);
				}
			}
		} catch (IOException e) {
			System.err.println("读取命令行异常");
			System.exit(5); // 读取命令行异常
		}
		
		for (Command command : commands) {
			command.doCommand(robot);
		}
	}
}
