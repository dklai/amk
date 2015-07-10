package com.dklai.robot.amk.cmd;

import java.awt.Robot;
import java.awt.event.InputEvent;

import com.dklai.robot.amk.ex.CommandException;

public class DoubleClickCommand implements Command {
	private int x;
	private int y;
	private boolean move = false;
	
	public DoubleClickCommand() {
	}
	
	public DoubleClickCommand(String args) {
		if (args != null && !args.trim().isEmpty()) {
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
				move = true;
			} catch (NumberFormatException e) {
				throw new CommandException("生成 Move 指令异常", e);
			}
		}
	}
	
	public DoubleClickCommand(int x, int y) {
		this.x = x;
		this.y = y;
		move = true;
	}

	public void doCommand(Robot robot) {
		if (move) {
			robot.mouseMove(x, y);
		}
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

}
