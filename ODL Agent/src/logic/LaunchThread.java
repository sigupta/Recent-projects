package logic;

import connectController.ControllerInterface;

public class LaunchThread extends Thread {
	
public void run(){
		
		try {
			ControllerInterface ci = new ControllerInterface();
			ci.connect("127.0.0.1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Connection not made");
			e.printStackTrace();
		}

	}

}
