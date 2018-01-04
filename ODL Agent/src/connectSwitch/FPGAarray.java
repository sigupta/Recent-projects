package connectSwitch;

import java.nio.ByteBuffer;
import java.util.BitSet;

public class FPGAarray {
	// public static int[][] convert(byte[] timeslot, int ipAddr, int outPort, byte waveLength){
	public int[][] convertTOR(BitSet timeslot, int ipAddr, int outPort, int waveLength, int inputPort){

		  
		  int[][] slot = new int[160][4];

		  int s = 0;
		  if(outPort == 2){
			  s+=80;
		  }
		  
		  for (int i=0 ; i<80 ; i++){

		   if(timeslot.get(i)){
		    slot[s+i][0] = ipAddr; // adding 32 bit IP address
		    slot[s+i][1] = outPort; //output port
		    slot[s+i][2] = waveLength; //wavelength
		  //  System.out.println("input port ="+inputPort );
		    slot[s+i][3] = inputPort; // input port added to split the TOR information among South-FPGA channels

		   } else {
		    for(int j=0;j<4;j++){
		     slot[i][j] = 0;
		    }
		   }

		  }
		  
		  for (int i=0 ; i<160 ; i++){
			  System.out.println("slot numbers "+i+" "+slot[i][0]);
		  }
		  
		  return slot;
		 }
	
	public int[][] convertPOD(BitSet timeslot, int dropW, int dropOW, int intraW, int intraOW){

		  
		  int[][] slot = new int[80][4];

		  for (int i=0 ; i<80 ; i++){

		   if(timeslot.get(i)){
		    slot[i][0] = dropW; // 
		    slot[i][1] = dropOW; //
		    slot[i][2] = intraW; //
		    slot[i][3] = intraOW;
		    

		   } else {
		    for(int j=0;j<4;j++){
		     slot[i][j] = 0;
		    }
		   }

		  }
		  return slot;
		 }
}
