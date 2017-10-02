package com.gl.fsm.boot;

import com.gl.fsm.core.Command;
import com.gl.fsm.core.FiniteStateManager;

public class LeaveApplicationRobot {

	public static void main(String[] args) {
		String emp = "E0";
		String mngt1 = "M1";
		String mngt2 = "M2";
		FiniteStateManager fsm = new FiniteStateManager();
		Command newCmd = new Command("901", "提交请假单");
		
		fsm.handle(emp, newCmd);
		
		Command approve1Cmd = new Command("101", "主管批准");
		fsm.handle(mngt1, approve1Cmd);
		
	}

}
