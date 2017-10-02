package com.gl.fsm.boot;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.gl.fsm.core.Command;
import com.gl.fsm.core.CommandDescription;
import com.gl.fsm.core.FiniteStateManager;
import com.gl.fsm.core.LeaveApplication;
import com.gl.fsm.core.state.State;
import static com.gl.fsm.core.state.StateDescription.*;

public class LeaveApplicationRobot {
	public static final String USER_SYS = "SYS";

	public static void main(String[] args) {
		String emp = "E0";
		String mngt1 = "M1";
		String mngt2 = "M2";
		
		LeaveApplication object = new LeaveApplication();
		object.setCreatedAt(new Date());
		object.setDaysToApply(16);
		object.setUsername(emp);
		object.setUpdatedAt(new Date());
		object.setUpdatedBy(emp);
		
		FiniteStateManager fsm = new FiniteStateManager();
		fsm.start(emp, object);
		
		Command cancelCmd = new Command(CommandDescription.CANCEL, CommandDescription.CANCEL_NAME);
		fsm.handle(emp, cancelCmd);
		
		check(fsm);
		
		
		fsm.clear();
		fsm.start(emp, object);
		
		Command approveL1Cmd = new Command(CommandDescription.APPROVE,CommandDescription.APPROVE_NAME);
		fsm.handle(mngt1, approveL1Cmd);
		
		check(fsm);
		
		fsm.handle(emp, cancelCmd);
		check(fsm);
		
		fsm.clear();
		fsm.start(emp, object);
		
		fsm.handle(mngt1, approveL1Cmd);
		
		check(fsm);
		
		Command approveL2Cmd = new Command(CommandDescription.APPROVE,CommandDescription.APPROVE_NAME);
		fsm.handle(mngt2, approveL2Cmd);
		check(fsm);
		
		Command resumeCmd = new Command(CommandDescription.RESUME,CommandDescription.RESUME_NAME);
		fsm.handle(emp, resumeCmd);
		check(fsm);
	}
	
	public static void check(FiniteStateManager fsm) {
		List<String> toCloseStateCodes = Arrays.asList(CANCELLED,REJECTED,RESUMED);
		State currentState = fsm.getCurrentState();
		if(toCloseStateCodes.contains(currentState.getStateCode())) {
			Command closeCmd = new Command(CommandDescription.CLOSE, CommandDescription.CLOSE_NAME);
			fsm.handle(USER_SYS, closeCmd);
		}
	}

}
