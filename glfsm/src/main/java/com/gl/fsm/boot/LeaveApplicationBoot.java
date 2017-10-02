package com.gl.fsm.boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import com.gl.fsm.core.Command;
import com.gl.fsm.core.FiniteStateManager;
import com.gl.fsm.core.LeaveApplication;
import com.gl.fsm.core.state.State;
import com.gl.fsm.core.state.StateDescription;

public class LeaveApplicationBoot {
	public static final String USER_SYS = "SYS";
	public static final String M1 = "M1";
	public static final String M2 = "M2";

	public static void main(String[] args) throws NumberFormatException, IOException {
		String emp = "E0";
		
		System.out.println("type in days to leave:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int days = Integer.parseInt(br.readLine());
		
		LeaveApplication object = new LeaveApplication();
		object.setCreatedAt(new Date());
		object.setDaysToApply(days);
		object.setUsername(emp);
		object.setUpdatedAt(new Date());
		object.setUpdatedBy(emp);
		
		FiniteStateManager fsm = new FiniteStateManager();
		fsm.start(emp, object);
		
		while(true) {
			System.out.println("enter instruction <username>|<commandCode>:");
			String line = br.readLine();
			String [] parts = line.split(",");
			String username = parts[0];
			String commandCode = parts[1];
			
			Command cmd = new  Command(commandCode);
			System.out.println(String.format("CMD:%s", cmd));
			fsm.handle(username, cmd);
			
			State state = fsm.getCurrentState();
			
			if(StateDescription.CLOSED.equals(state.getStateCode())) {
				System.out.println();
				System.out.println("===============");
				System.err.println("CLOSED");
				System.out.println("===============");
				break;
			}
		}

	}

}
