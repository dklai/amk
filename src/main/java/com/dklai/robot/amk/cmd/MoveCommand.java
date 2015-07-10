package com.dklai.robot.amk.cmd;

import java.awt.Robot;

import com.dklai.robot.amk.ex.CommandException;

public class MoveCommand implements Command {
	private int x;
	private int y;
	
	public MoveCommand(String args) {
		try {
			if (args.contains(",")) {
				String [] arr = args.split(",");
				x = Integer.parseInt(arr[0].trim());
				y = Integer.parseInt(arr[1].trim());
			} else {
				String [] arr = args.trim().split("\\s+");
				x = Integer.parseInt(arr[0].trim());
				y = Integer.parseInt(arr[1].trim());
			}
		} catch (NumberFormatException e) {
			throw new CommandException("生成 Move 指令异常", e);
		}
	}
	
	public MoveCommand(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void doCommand(Robot robot) {
		robot.mouseMove(x, y);
	}
}
