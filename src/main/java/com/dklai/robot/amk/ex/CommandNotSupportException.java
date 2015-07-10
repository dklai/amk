package com.dklai.robot.amk.ex;

public class CommandNotSupportException extends CommandException {
	private static final long serialVersionUID = -2802319654503471235L;

	public CommandNotSupportException() {
		super();
	}

	public CommandNotSupportException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CommandNotSupportException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandNotSupportException(String message) {
		super(message);
	}

	public CommandNotSupportException(Throwable cause) {
		super(cause);
	}
}
