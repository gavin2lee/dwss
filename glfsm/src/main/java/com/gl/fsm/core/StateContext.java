package com.gl.fsm.core;

public class StateContext {
	private String username;
	private String command;
	private StateableObject object;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public StateableObject getObject() {
		return object;
	}
	public void setObject(StateableObject object) {
		this.object = object;
	}
	@Override
	public String toString() {
		return "StateContext [username=" + username + ", command=" + command + ", object=" + object + "]";
	}
	
	
}
