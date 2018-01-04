package connectSwitch;

import java.nio.ByteBuffer;

import msgModel.ControlPacketTable;
import msgModel.FlowTablePOD1;
import msgModel.FlowTableTOR;

/**
 * 
 * This class checks the flows for each schedule ID. If the flows have reached the value specified
 * in the flow counter, it sends them to the switch and updates the flow table
 *
 */
public class DumpFlowsPOD1 {
	public static void flush(int schID){
		
		System.out.println("Forwarding " + schID + " to the scheduler");
		
		//Reconstructing flows to a new array
		int[][] finalFlow = new int[80][4]; 
		for(int i =0; i<80;i++){
			for(int[][] tmp : FlowTablePOD1.flowsPOD1.get(schID)){
				if (tmp[i][0]!= 0){
					finalFlow[i][0] = tmp[i][0];
					finalFlow[i][1] = tmp[i][1];
					finalFlow[i][2] = tmp[i][2];
					finalFlow[i][3] = tmp[i][3];
				}
			}
}
		
		ControlPacketTable.cpTable.get("POD1").add(finalFlow);
		//writing data on the switch
			//ControlPacket.connect(finalFlow);
			
			
//			try {
//				Thread.sleep(20000);
//			} catch (Exception e) {
//				
//			}
				
		
			
		//removing SSID from the flowTable
		FlowTablePOD1.flowsPOD1.remove(schID);
	}
}