package msgModel;

import java.util.ArrayList;
import java.util.HashMap;

public class ControlPacketTable {
public static HashMap<String, ArrayList<int[][]>> cpTable ;
	
	static{
		cpTable = new HashMap<String, ArrayList<int[][]>>();
		ArrayList<int[][]> a1 = new ArrayList<int[][]>();
		ArrayList<int[][]> a2 = new ArrayList<int[][]>();
		ArrayList<int[][]> a3 = new ArrayList<int[][]>();
		
		cpTable.put("TOR", a1);
		cpTable.put("POD1", a2);
		cpTable.put("POD2", a3);
		
	}
	
	public static void displayData(){
		
		
		for(String i:cpTable.keySet()){
			System.out.println("Switch Type : " + i + " has " + cpTable.get(i).size() + " entries");
		}
	}
}


