package logic;

import java.util.Scanner;

import connectController.*;
import connectSwitch.SwitchForwardingScheduler;

public class AgentStart {
	/**
	 * This class will take initiate the agent based on the user defined settings
	 * @param args
	 */
	public static void main(String args[]){
		String IP = "127.0.0.1";
		for (String s: args) {
            System.out.println("Connecting Controller at " + s);
            IP = s;
        }
		
				// Getting user input for controller IP
				Scanner reader = new Scanner(System.in);  // Reading from System.in
				System.out.print("Enter the Controller IP to be connected : ");
				IP = reader.next();
				
				// Getting user input for the FPGA Device IDs
				reader = new Scanner(System.in);  // Reading from System.in
				System.out.print("Enter the Device ID for South-FPGA : ");
				SwitchForwardingScheduler.ID_SouthFPGA = reader.nextInt();
				
				System.out.print("Enter the Device ID for North-FPGA plane 1 : ");
				SwitchForwardingScheduler.ID_NorthFPGA_plane1 = reader.nextInt();
				
				System.out.print("Enter the Device ID for North-FPGA plane 2 : ");
				SwitchForwardingScheduler.ID_NorthFPGA_plane2 = reader.nextInt();
		
				// Getting user input for the datapath ID
				reader = new Scanner(System.in);  // Reading from System.in
				System.out.print("Enter the Datapath ID for the TOR connection to be used for the agent : ");
				MainLogic.datapathID[0] = reader.nextInt();
				
				System.out.print("Enter the Datapath ID for the POD 1 connection to be used for the agent : ");
				MainLogic.datapathID[1] = reader.nextInt();
				
				System.out.print("Enter the Datapath ID for the POD 2 connection to be used for the agent : ");
				MainLogic.datapathID[2] = reader.nextInt();
				
		
		
		// Initiating the threads of controller connection to the Agent
		
				LaunchThread t = new LaunchThread();
				t.start();
				
				LaunchThread p1 = new LaunchThread();
				p1.start();
				
				LaunchThread p2 = new LaunchThread();
				p2.start();
				
				SwitchForwardingScheduler sfc = new SwitchForwardingScheduler();
				sfc.start();

	}
	

		
}
