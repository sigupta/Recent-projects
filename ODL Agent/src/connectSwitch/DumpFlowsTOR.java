package connectSwitch;

import java.nio.ByteBuffer;

import msgModel.ControlPacketTable;
import msgModel.FlowTableTOR;

/**
 * 
 * This class checks the flows for each schedule ID. If the flows have reached the value specified
 * in the flow counter, it sends them to the switch and updates the flow table
 *
 */
public class DumpFlowsTOR {
	public static void flush(int schID){
		
		System.out.println("Writing SSID " + schID + " flows onto the switch");
		
		//Reconstructing flows to a new array
		int[][] finalFlow = new int[160][4]; 
		for(int i =0; i<160;i++){
			for(int[][] tmp : FlowTableTOR.flowsTOR.get(schID)){
				if (tmp[i][0]!= 0){
					finalFlow[i][0] = tmp[i][0]; // ip address
					finalFlow[i][1] = tmp[i][1]; // output port
					finalFlow[i][2] = tmp[i][2]; // wavelength
					finalFlow[i][3] = tmp[i][3]; // input port
				}
			}
}
		
		ControlPacketTable.cpTable.get("TOR").add(finalFlow);
		//writing data on the switch
			//ControlPacket.connect(finalFlow);
			
			
//			try {
//				Thread.sleep(20000);
//			} catch (Exception e) {
//				
//			}
				
		
			
		//removing SSID from the flowTable
		FlowTableTOR.flowsTOR.remove(schID);
	}
}
