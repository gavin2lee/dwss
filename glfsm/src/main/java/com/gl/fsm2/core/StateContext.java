package com.gl.fsm2.core;

import com.gl.fsm2.core.state.State;

public class StateContext {
	private String username;
	private Command command;
	private StateableObject object;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public StateableObject getObject() {
		return object;
	}

	public void setObject(StateableObject object) {
		this.object = object;
	}

	public StateContext setState(State state) {
		if (getObject() != null) {
			getObject().setCurrentState(state);
		}
		
		return this;
	}

	@Override
	public String toString() {
		return "StateContext [username=" + username + ", command=" + command + ", object=" + object + "]";
	}

}
