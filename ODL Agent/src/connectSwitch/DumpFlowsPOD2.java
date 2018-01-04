package connectSwitch;

import java.nio.ByteBuffer;

import msgModel.ControlPacketTable;
import msgModel.FlowTablePOD2;
import msgModel.FlowTableTOR;

/**
 * 
 * This class checks the flows for each schedule ID. If the flows have reached the value specified
 * in the flow counter, it sends them to the switch and updates the flow table
 *
 */
public class DumpFlowsPOD2 {
	public static void flush(int schID){
		
		System.out.println("forwarding " + schID + " flows to the scheduler");
		
		//Reconstructing flows to a new array
		int[][] finalFlow = new int[80][4]; 
			for(int i =0; i<80;i++){
				for(int[][] tmp : FlowTablePOD2.flowsPOD2.get(schID)){
					if (tmp[i][0]!= 0){
						finalFlow[i][0] = tmp[i][0];
						finalFlow[i][1] = tmp[i][1];
						finalFlow[i][2] = tmp[i][2];
						finalFlow[i][3] = tmp[i][3];
					}
				}
			}
			
			ControlPacketTable.cpTable.get("POD2").add(finalFlow);
			//removing SSID from the flowTable
			FlowTablePOD2.flowsPOD2.remove(schID);
		
//		//Initialize the control packet
//		int num_of_slots = 80;
//		int[][] controlPacket = new int[num_of_slots][3];
//				for(int i = 0; i < num_of_slots; i++){
//					controlPacket[i][0] = i+4;           // IP
//					controlPacket[i][1] = i+4;		   // output port
//					controlPacket[i][2] = i+4;//num_of_slots;// wavelength
//				}
//		
//		System.out.println("Final Flow length = "+finalFlow.length);		
//		System.out.println("Control Packet length = "+controlPacket.length);
//				
//		 for(int i=0; i<80; i++){
//			 for(int j=0; j<3; j++){
//				 System.out.print("finalFlow[" + i + "," + j + "] = " + finalFlow[i][j] + " ");
//			 }
//			 System.out.print("\n");
//		 }
//		 
//		 controlPacket = finalFlow;
		
		
		//writing data on the switch
			//ControlPacket.connect(finalFlow);
			
			
//			try {
//				Thread.sleep(20000);
//			} catch (Exception e) {
//				
//			}
				
		
			
		
	}
}