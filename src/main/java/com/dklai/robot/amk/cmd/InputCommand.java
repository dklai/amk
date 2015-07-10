package com.dklai.robot.amk.cmd;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class InputCommand implements Command {
	// 定义字符按键映射
	private static Map<Character, Integer> keyMap = new HashMap<Character, Integer>();
	private static Map<Character, Integer> keyShiftMap = new HashMap<Character, Integer>();
	static {
		keyMap.put('`', KeyEvent.VK_BACK_QUOTE);
		keyMap.put('-', KeyEvent.VK_MINUS);
		keyMap.put('=', KeyEvent.VK_EQUALS);
		keyMap.put('[', KeyEvent.VK_OPEN_BRACKET);
		keyMap.put(']', KeyEvent.VK_CLOSE_BRACKET);
		keyMap.put('\\', KeyEvent.VK_BACK_SLASH);
		keyMap.put(';', KeyEvent.VK_SEMICOLON);
		keyMap.put('\'', KeyEvent.VK_QUOTE);
		keyMap.put(',', KeyEvent.VK_COMMA);
		keyMap.put('.', KeyEvent.VK_PERIOD);
		keyMap.put('/', KeyEvent.VK_SLASH);

		keyShiftMap.put('~', KeyEvent.VK_BACK_QUOTE);
		keyShiftMap.put('!', KeyEvent.VK_1);
		keyShiftMap.put('@', KeyEvent.VK_2);
		keyShiftMap.put('#', KeyEvent.VK_3);
		keyShiftMap.put('$', KeyEvent.VK_4);
		keyShiftMap.put('%', KeyEvent.VK_5);
		keyShiftMap.put('^', KeyEvent.VK_6);
		keyShiftMap.put('&', KeyEvent.VK_7);
		keyShiftMap.put('*', KeyEvent.VK_8);
		keyShiftMap.put('(', KeyEvent.VK_9);
		keyShiftMap.put(')', KeyEvent.VK_0);
		keyShiftMap.put('_', KeyEvent.VK_MINUS);
		keyShiftMap.put('+', KeyEvent.VK_EQUALS);
		keyShiftMap.put('{', KeyEvent.VK_OPEN_BRACKET);
		keyShiftMap.put('}', KeyEvent.VK_CLOSE_BRACKET);
		keyShiftMap.put('|', KeyEvent.VK_BACK_SLASH);
		keyShiftMap.put(':', KeyEvent.VK_SEMICOLON);
		keyShiftMap.put('"', KeyEvent.VK_QUOTE);
		keyShiftMap.put('<', KeyEvent.VK_COMMA);
		keyShiftMap.put('>', KeyEvent.VK_PERIOD);
		keyShiftMap.put('?', KeyEvent.VK_SLASH);
	}
	
	private char[] chars;
	
	public InputCommand(String chars) {
		this.chars = chars.trim().toCharArray();
	}

	public void doCommand(Robot robot) {
		for (char c : chars) {
			if (c >= '0' && c <= '9') {
				robot.keyPress(c);
				robot.keyRelease(c);
			} else if (c >= 'a' && c <= 'z') {
				robot.keyPress(KeyEvent.VK_A + (c - 'a'));
				robot.keyRelease(KeyEvent.VK_A + (c - 'a'));
			} else if (c >= 'A' && c <= 'Z') {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(c);
				robot.keyRelease(c);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (keyMap.containsKey(c)) {
				int code = keyMap.get(c).intValue();
				robot.keyPress(code);
				robot.keyRelease(code);
			} else if (keyShiftMap.containsKey(c)) {
				int code = keyShiftMap.get(c).intValue();
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(code);
				robot.keyRelease(code);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}
		}
	}
}
