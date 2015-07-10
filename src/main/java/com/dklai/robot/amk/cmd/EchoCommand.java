package com.dklai.robot.amk.cmd;

import java.awt.Robot;

public class EchoCommand implements Command {
	private String echo;
	
	public EchoCommand(String echo) {
		this.echo = echo;
	}
	
	public void doCommand(Robot robot) {
		System.out.println(echo);
	}
}
