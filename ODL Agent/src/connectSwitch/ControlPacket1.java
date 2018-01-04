package connectSwitch;

import edu.ucsd.cs.riffa.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ControlPacket1 {

	public static void connect(ByteBuffer controlPacket_For_DumpFlows) {}
	
	public static void connect(int[][] controlPacket, int deviceID, int channel) {
		int fid = 0;
	//	int channel = 0;
		int num_of_slots = 80;
		int i, j, sent = 0, recvd = 0;
		byte tmp0, tmp1;  //example byte declaration


//		FpgaInfo info = Fpga.list();
//		System.out.println(info);
//		
//			for (i= 0; i <info.getNumFpgas();i++){
//				System.out.println("Device ID: "+info.getDeviceId(i));
//				if (info.getDeviceId(i) == deviceID) //Device Id 7034
//				{
//					fid = info.getId(i);
//					System.out.println("FPGA ID against the Device ID "+deviceID+" = "+fid);
//				}
//			}
//		Fpga fpga = Fpga.open(fid);
//		if(fpga == null){
//			System.out.println("Could not malloc memory for recvBuffer");
//			fpga.close();
//		}
		
		// Malloc the arrays
		ByteBuffer sendbuf = ByteBuffer.allocateDirect(num_of_slots*4*4);  //// *4, needs the number of bytes
		java.nio.IntBuffer intBufSend = sendbuf.asIntBuffer();		 //// Creates a view of this byte buffer as an int buffer. 
		ByteBuffer recvbuf = ByteBuffer.allocateDirect(4*4);  //// *4, needs the number of bytes--- +16 because the first 4 words(=16bytes) received from RIFFA are not actually valid
		java.nio.IntBuffer intBufRecv = recvbuf.asIntBuffer();       //// Creates a view of this byte buffer as an int buffer. 

		
		if(sendbuf == null){
			System.out.println("Could not malloc memory for sendBuffer");
		}
		if(recvbuf == null){
			System.out.println("Could not malloc memory for recvBuffer");
		}
		
		sendbuf.clear();
//		if (sendbuf.remaining() == 0) {
//			System.out.println("\n Something is in the sendbuf");
//		}
		intBufSend.clear();
//		if (intBufSend.remaining() == 0) {
//			System.out.println("\n Something is in the intBufSend");
//		}
		
		// Create the sent buffer
		for(i = 0; i< num_of_slots; i++){
			for(j = 0; j < 4; j++) {
				///// ***** Put an integer in the "integer view buffer" intBufsend,but the integer is in BIG ENDIAN byre order ***** //////// 
				intBufSend.put(i*4 + j, controlPacket[i][j]);
			}
		}
		sendbuf.clear();
		intBufSend.clear();
		//Initialize the recv buffer
		for(i = 0; i< 4; i++) {
			recvbuf.putInt(i,0);
		}
		
		//Display the sent data from inside the sendbuf, data there are in BIG ENDIAN byte order
		for(i = 0; i < num_of_slots; i++){
			//System.out.println("ControlPacket:" + " " + controlPacket[i][0] + " " +  controlPacket[i][1] + " " + controlPacket[i][2]);
			System.out.printf("%d - %02x%02x%02x%02x\t", i+1, sendbuf.get((i*(4*4))+0), sendbuf.get((i*(4*4))+1), sendbuf.get((i*(4*4))+2), sendbuf.get((i*(4*4))+3));  // Prints the buffer in LITTLE ENDIAN
			System.out.printf("%d - %02x%02x%02x%02x\t", i+1, sendbuf.get(((i*(4*4))+0)+1*4), sendbuf.get(((i*(4*4))+1)+1*4), sendbuf.get(((i*(4*4))+2)+1*4), sendbuf.get(((i*(4*4))+3)+1*4));
			System.out.printf("%d - %02x%02x%02x%02x\t", i+1, sendbuf.get(((i*(4*4))+0)+2*4), sendbuf.get(((i*(4*4))+1)+2*4), sendbuf.get(((i*(4*4))+2)+2*4), sendbuf.get(((i*(4*4))+3)+2*4));
			System.out.printf("%d - %02x%02x%02x%02x\n", i+1, sendbuf.get(((i*(4*4))+0)+3*4), sendbuf.get(((i*(4*4))+1)+3*4), sendbuf.get(((i*(4*4))+2)+3*4), sendbuf.get(((i*(4*4))+3)+3*4));
		}
		sendbuf.clear();
		intBufSend.clear();
		
		long startTime0 = System.nanoTime();
		// sending data
		//fpga.reset();
//		sent = fpga.send(channel, sendbuf, num_of_slots * 4, 0, true, 0); //SampleApp.java
		long endTime0 = System.nanoTime();
		long duration0 = endTime0 - startTime0;
		System.out.println("words sent:"+sent);

		// receiving data
//		recvd = fpga.recv(channel, recvbuf, 1000);
		System.out.println("words recv:"+recvd);
	
		//Done with the device
//		fpga.close();

		//Display the recv code of transaction complete (4 times the number 17)
		if (recvd != 0)
			System.out.println("Received: " + intBufRecv.get(0) +" "+ intBufRecv.get(1) +" "+ intBufRecv.get(2) +" "+ intBufRecv.get(3));
			
		System.out.printf("send bw: %f MB/s %fms\n",sent*4.0/1024/1024/(duration0/1000000000.0),(duration0/1000000.0) );
	}
}