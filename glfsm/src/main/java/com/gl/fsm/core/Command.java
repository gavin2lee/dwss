package com.gl.fsm.core;

public class Command {
	private String commandCode;
	private String CommandName;

	public Command(String commandCode, String commandName) {
		super();
		this.commandCode = commandCode;
		CommandName = commandName;
	}
	public String getCommandCode() {
		return commandCode;
	}
	public String getCommandName() {
		return CommandName;
	}
	@Override
	public String toString() {
		return "Command [commandCode=" + commandCode + ", CommandName=" + CommandName + "]";
	}
}
