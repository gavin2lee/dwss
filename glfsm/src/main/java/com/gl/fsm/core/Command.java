package com.gl.fsm.core;

import java.util.HashMap;
import java.util.Map;

import static com.gl.fsm.core.CommandDescription.*;

public class Command {
	static final Map<String, String> NAMES = new HashMap<String, String>();

	static {
		NAMES.put(SUBMIT, SUBMIT_NAME);
		NAMES.put(CANCEL, CANCEL_NAME);
		NAMES.put(RESUME, RESUME_NAME);
		NAMES.put(CLOSE, CLOSE_NAME);
		NAMES.put(APPROVE, APPROVE_NAME);
		NAMES.put(REJECT, REJECT_NAME);
	}
	
	private String commandCode;
	private String CommandName;
	
	public static String findCommandName(String commandCode) {
		return NAMES.get(commandCode);
	}

	public Command(String commandCode) {
		this(commandCode, findCommandName(commandCode));
	}

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
