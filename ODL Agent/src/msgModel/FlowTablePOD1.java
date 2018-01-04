package msgModel;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

public class FlowTablePOD1 {
	public static HashMap<Integer, ArrayList<int[][]>> flowsPOD1 ;
	
	static{
		flowsPOD1 = new HashMap<Integer, ArrayList<int[][]>>();
	}
	
	public static void displayData(){
		System.out.println("Number of SSID's stored in the agent for TOR = "+flowsPOD1.size());
		
		for(int i:flowsPOD1.keySet()){
			System.out.println("SSID : " + i + " has " + flowsPOD1.get(i).size() + " flows");
		}
	}
}