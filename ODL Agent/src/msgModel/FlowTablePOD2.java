package msgModel;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

public class FlowTablePOD2 {
	public static HashMap<Integer, ArrayList<int[][]>> flowsPOD2 ;
	
	static{
		flowsPOD2 = new HashMap<Integer, ArrayList<int[][]>>();
	}
	
	public static void displayData(){
		System.out.println("Number of SSID's stored in the agent for TOR = "+flowsPOD2.size());
		
		for(int i:flowsPOD2.keySet()){
			System.out.println("SSID : " + i + " has " + flowsPOD2.get(i).size() + " flows");
		}
	}
}