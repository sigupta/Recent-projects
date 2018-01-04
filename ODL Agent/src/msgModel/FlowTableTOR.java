package msgModel;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

public class FlowTableTOR {
	public static HashMap<Integer, ArrayList<int[][]>> flowsTOR ;
	
	static{
		flowsTOR = new HashMap<Integer, ArrayList<int[][]>>();
	}
	
	public static void displayData(){
		System.out.println("Number of SSID's stored in the agent for TOR = "+flowsTOR.size());
		
		for(int i:flowsTOR.keySet()){
			System.out.println("SSID : " + i + " has " + flowsTOR.get(i).size() + " flows");
		}
	}
}
