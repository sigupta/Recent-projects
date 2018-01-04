package connectSwitch;

import msgModel.ControlPacketTable;

public class SwitchForwardingScheduler extends Thread {
	
	public static int ID_SouthFPGA = 0;
	public static int ID_NorthFPGA_plane1 = 0;
	public static int ID_NorthFPGA_plane2 = 0;
	
	public void run(){
		int counter = 0;
		while (true){
			int sizeTOR = 0;
			int sizePOD1 = 0;
			int sizePOD2 = 0;
			sizeTOR = ControlPacketTable.cpTable.get("TOR").size();
			sizePOD1= ControlPacketTable.cpTable.get("POD1").size();
			sizePOD2= ControlPacketTable.cpTable.get("POD2").size();
			
			System.out.println("SizeTOR = "+sizeTOR);
			System.out.println("SizePOD1 = "+sizePOD1);
			System.out.println("SizePOD1 = "+sizePOD2);
			
			if(sizeTOR >= counter +1 && sizePOD1 >= counter +1 && sizePOD2 >= counter +1){
				// logic to write to switch
				
				System.out.println("South = "+ID_SouthFPGA);
				System.out.println("North 1= "+ID_NorthFPGA_plane1);
				System.out.println("North 2 = "+ID_NorthFPGA_plane2);
				
				// Splitting out control packet TOR based on input port 
				int ControlPacketTOR[][] = ControlPacketTable.cpTable.get("TOR").get(counter);
				int ControlPacketTOR_ch1[][] = new int[80][4];
				int ControlPacketTOR_ch2[][] = new int[80][4];
				int ControlPacketTOR_plane1[][] = new int[80][4];
				int ControlPacketTOR_plane2[][] = new int[80][4];
				
				

				// initializing values as 0 for all arrays
				 
				for (int i=0; i<80 ; i++){
				ControlPacketTOR_ch1[i][0] = 0;
				ControlPacketTOR_ch1[i][1] = 0;
				ControlPacketTOR_ch1[i][2] = 0;
				ControlPacketTOR_ch1[i][3] = 0;
				 
				 
				ControlPacketTOR_ch2[i][0] = 0;
				ControlPacketTOR_ch2[i][1] = 0;
				ControlPacketTOR_ch2[i][2] = 0;
				ControlPacketTOR_ch2[i][3] = 0;
				 
				ControlPacketTOR_plane1[i][0] = 0;
				ControlPacketTOR_plane1[i][1] = 0;
				ControlPacketTOR_plane1[i][2] = 0;
				ControlPacketTOR_plane1[i][3] = 0;
				 
				ControlPacketTOR_plane2[i][0] = 0;
				ControlPacketTOR_plane2[i][1] = 0;
				ControlPacketTOR_plane2[i][2] = 0;
				ControlPacketTOR_plane2[i][3] = 0;
				 
				}

				
				  for(int i =0 ; i<80 ;i++){
					  
				  

					  if(ControlPacketTOR[i][3] == 3){ // if input port = 3
						  // send to S-FPGA channel 1
						  System.out.println("input === 3");
						  ControlPacketTOR_ch1[i][0] = ControlPacketTOR[i][0];  // ip address
						  ControlPacketTOR_ch1[i][1] = ControlPacketTOR[i][1];  // output port
						  ControlPacketTOR_ch1[i][2] = ControlPacketTOR[i][2];  // wavelength
						  ControlPacketTOR_ch1[i][3] = 0;
						  
					  }
					  else if(ControlPacketTOR[i][3] == 4){ // if input port = 4
						  // send to S-FPGA channel 2
						  System.out.println("input === 4");
						  ControlPacketTOR_ch2[i][0] = ControlPacketTOR[i][0];  // ip address
						  ControlPacketTOR_ch2[i][1] = ControlPacketTOR[i][1];  // output port
						  ControlPacketTOR_ch2[i][2] = ControlPacketTOR[i][2];  // wavelength
						  ControlPacketTOR_ch2[i][3] = 0;
						  
					  }/*else{
						  System.out.println("input === else");
						  ControlPacketTOR_ch1[i][0] = 0;  // ip address
						  ControlPacketTOR_ch1[i][1] = 0;  // output port
						  ControlPacketTOR_ch1[i][2] = 0;  // wavelength
						  ControlPacketTOR_ch1[i][3] = 0;
						  
						  ControlPacketTOR_ch2[i][0] = 0;  // ip address
						  ControlPacketTOR_ch2[i][1] = 0;  // output port
						  ControlPacketTOR_ch2[i][2] = 0;  // wavelength
						  ControlPacketTOR_ch2[i][3] = 0;
						  
					  }*/
				  }
				  
				  for(int i =80 ; i<160 ;i++){
					  
					  if(ControlPacketTOR[i][3] == 3){ // if input port = 3
						  // send to S-FPGA channel 1
						  System.out.println("input === 3");
						  ControlPacketTOR_ch1[i-80][0] = ControlPacketTOR[i][0];  // ip address
						  ControlPacketTOR_ch1[i-80][1] = ControlPacketTOR[i][1];  // output port
						  ControlPacketTOR_ch1[i-80][2] = ControlPacketTOR[i][2];  // wavelength
						  ControlPacketTOR_ch1[i-80][3] = 0;
						  
					  }
					  else if(ControlPacketTOR[i][3] == 4){ // if input port = 4
						  // send to S-FPGA channel 2
						  System.out.println("input === 4");
						  ControlPacketTOR_ch2[i-80][0] = ControlPacketTOR[i][0];  // ip address
						  ControlPacketTOR_ch2[i-80][1] = ControlPacketTOR[i][1];  // output port
						  ControlPacketTOR_ch2[i-80][2] = ControlPacketTOR[i][2];  // wavelength
						  ControlPacketTOR_ch2[i-80][3] = 0;
						  
					  }/*else{
						  System.out.println("input === else");
						  ControlPacketTOR_ch1[i-80][0] = 0;  // ip address
						  ControlPacketTOR_ch1[i-80][1] = 0;  // output port
						  ControlPacketTOR_ch1[i-80][2] = 0;  // wavelength
						  ControlPacketTOR_ch1[i-80][3] = 0;
						  
						  ControlPacketTOR_ch2[i-80][0] = 0;  // ip address
						  ControlPacketTOR_ch2[i][1] = 0;  // output port
						  ControlPacketTOR_ch2[i][2] = 0;  // wavelength
						  ControlPacketTOR_ch2[i][3] = 0;
						  
					  }*/
				  }
				  
					  

					  
					  for (int i=0 ; i<80 ; i++){
				//	  if(ControlPacketTOR[i][1] == 1){ // if output port = 1
						  // send to N-FPGA plane 1
						  System.out.println("output === 1");
						  ControlPacketTOR_plane1[i][0] = ControlPacketTOR[i][0];  // ip address
						  ControlPacketTOR_plane1[i][1] = ControlPacketTOR[i][1];  // output port
						  ControlPacketTOR_plane1[i][2] = ControlPacketTOR[i][2];  // wavelength
						  ControlPacketTOR_plane1[i][3] = 0;
						  
				//	  }
				//	  else if(ControlPacketTOR[i][1] == 2){ // if output port = 2
						  // send to N-FPGA plane 2
						  System.out.println("output === 2");
						  ControlPacketTOR_plane2[i][0] = ControlPacketTOR[i+80][0];  // ip address
						  ControlPacketTOR_plane2[i][1] = ControlPacketTOR[i+80][1];  // output port
						  ControlPacketTOR_plane2[i][2] = ControlPacketTOR[i+80][2];  // wavelength
						  ControlPacketTOR_plane2[i][3] = 0;
				//	  }
//						  else{
//						  System.out.println("output === else");
//						  ControlPacketTOR_plane1[i][0] = ControlPacketTOR[i][0];  // ip address
//						  ControlPacketTOR_plane1[i][1] = ControlPacketTOR[i][1];  // output port
//						  ControlPacketTOR_plane1[i][2] = ControlPacketTOR[i][2];  // wavelength
//						  ControlPacketTOR_plane1[i][3] = 0;
//						  
//						  ControlPacketTOR_plane2[i][0] = ControlPacketTOR[i][0];  // ip address
//						  ControlPacketTOR_plane2[i][1] = ControlPacketTOR[i][1];  // output port
//						  ControlPacketTOR_plane2[i][2] = ControlPacketTOR[i][2];  // wavelength
//						  ControlPacketTOR_plane2[i][3] = 0;
//						  
//					  }

				  }
				
	
				  
				if (ID_SouthFPGA != 0){
					ControlPacket1.connect(ControlPacketTOR_ch1, ID_SouthFPGA, 0);
					ControlPacket1.connect(ControlPacketTOR_ch2, ID_SouthFPGA, 1);
				}
				
				if (ID_NorthFPGA_plane1 != 0){
				ControlPacket2.connect(ControlPacketTOR_plane1, ID_NorthFPGA_plane1, 0);
				ControlPacket2.connect(ControlPacketTable.cpTable.get("POD1").get(counter), ID_NorthFPGA_plane1,1);
				}
				
				if (ID_NorthFPGA_plane2 != 0){
				ControlPacket3.connect(ControlPacketTOR_plane2, ID_NorthFPGA_plane2, 0);
				ControlPacket3.connect(ControlPacketTable.cpTable.get("POD2").get(counter), ID_NorthFPGA_plane2, 1);
				}
				
				counter ++;
				
			}
			
			ControlPacketTable.displayData();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
