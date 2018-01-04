package logic;

import io.netty.buffer.ByteBuf;
import static io.netty.buffer.Unpooled.buffer;
import msgModel.*;

public class MainLogic {
	/**
	  This class represents the main logical flow of the operations
	carried out on the message received by the controller
	 * @throws Exception if message not recognized
	*/
	
	public static long[] datapathID = new long[]{0,1,2};
	public static int dataCounter = 0;
	public ByteBuf GenerateTOR(int type, ByteBuf msg) throws Exception{

		ByteBuf createdMsg = buffer(0) ;


		switch (type){
			
			case 0: 
					System.out.println("message type received : " + "OFPT_HELLO[0]");
					System.out.println("Content Length : "+msg.readableBytes()+" Bytes");
					createdMsg = (new Hello()).create(msg);
					System.out.println("message response sent: " + "OFPT_HELLO[0]");
					break;
			case 2: 
					System.out.println("message type received : " + "ECHO_REQUEST[2]");
					System.out.println("Content Length : "+msg.readableBytes()+" Bytes");
					createdMsg = (new EchoReply()).create(msg);
					System.out.println("message response sent: " + "ECHO_REPLY[3]");
					break;
			case 5: 
					System.out.println("message type received : " + "OFPT_FEATURES_REQUEST[5]");
					System.out.println("Content Length : "+msg.readableBytes()+" Bytes");
					createdMsg = (new FeaturesReply()).create(msg,dataCounter);
					System.out.println("message response sent: " + "OFPT_FEATURES_REPLY[6]");
					break;
			case 14:
					System.out.println("message type received : " + "OFPT_FLOW_MOD[14]");
					System.out.println("Content Length : "+msg.readableBytes()+" Bytes");
					if(msg.getByte(25) == 0){
						FlowModLogic.initiateTOR(msg);
					}
					//System.out.println("message type received : " + "OFPT_FLOW_MOD[14]");
					break;
			case 18:
					System.out.println("message type received : " + "OFPT_MULTIPART_REQUEST[18]");
					System.out.println("Content Length : "+msg.readableBytes()+" Bytes");
					System.out.println("message response sent: " + "OFPT_MULTIPART_REPLY[19]");
					createdMsg = (new MultipartReply()).create(msg);
				//	System.out.println("message response sent: " + "OFPT_MULTIPART_REPLY[19]");
					break;
			case 20:
					System.out.println("message type received : " + "OFPT_BARRIER_REQUEST[18]");
					System.out.println("Content Length : "+msg.readableBytes()+" Bytes");
					createdMsg = (new BarrierReply()).create(msg);
					System.out.println("message response sent: " + "OFPT_BARRIER_REPLY[19]");
					break;
			case 24:
					System.out.println("message type received : " + "OFPT_ROLE_REQUEST[24]");
					System.out.println("Content Length : "+msg.readableBytes()+" Bytes");
					createdMsg = (new RoleReply()).create(msg);
					System.out.println("message response sent: " + "OFPT_ROLE_REPLY[25]");
					break;
			

			default: msg.release(); 
					 throw new Exception();

		}

		return createdMsg;


	}

	public ByteBuf GeneratePOD(int type, ByteBuf msg) throws Exception{

		ByteBuf createdMsg = buffer(0) ;


		switch (type){
			
			case 0: 
					System.out.println("message type received : " + "OFPT_HELLO[0]");
					System.out.println("Content Length : "+msg.readableBytes()+" Bytes");
					createdMsg = (new Hello()).create(msg);
					System.out.println("message response sent: " + "OFPT_HELLO[0]");
					break;
			case 2: 
					System.out.println("message type received : " + "ECHO_REQUEST[2]");
					System.out.println("Content Length : "+msg.readableBytes()+" Bytes");
					createdMsg = (new EchoReply()).create(msg);
					System.out.println("message response sent: " + "ECHO_REPLY[3]");
					break;
			case 5: 
					System.out.println("message type received : " + "OFPT_FEATURES_REQUEST[5]");
					System.out.println("Content Length : "+msg.readableBytes()+" Bytes");
					createdMsg = (new FeaturesReply()).create(msg,dataCounter);
					System.out.println("message response sent: " + "OFPT_FEATURES_REPLY[6]");
					break;
			case 14:
					System.out.println("message type received : " + "OFPT_FLOW_MOD[14]");
					System.out.println("Content Length : "+msg.readableBytes()+" Bytes");
					if(msg.getByte(25) == 0){
						FlowModLogic.initiatePOD(msg);
					}
					//System.out.println("message type received : " + "OFPT_FLOW_MOD[14]");
					break;
			case 18:
					System.out.println("message type received : " + "OFPT_MULTIPART_REQUEST[18]");
					System.out.println("Content Length : "+msg.readableBytes()+" Bytes");
					System.out.println("message response sent: " + "OFPT_MULTIPART_REPLY[19]");
					createdMsg = (new MultipartReply()).create(msg);
				//	System.out.println("message response sent: " + "OFPT_MULTIPART_REPLY[19]");
					break;
			case 20:
					System.out.println("message type received : " + "OFPT_BARRIER_REQUEST[18]");
					System.out.println("Content Length : "+msg.readableBytes()+" Bytes");
					createdMsg = (new BarrierReply()).create(msg);
					System.out.println("message response sent: " + "OFPT_BARRIER_REPLY[19]");
					break;
			case 24:
					System.out.println("message type received : " + "OFPT_ROLE_REQUEST[24]");
					System.out.println("Content Length : "+msg.readableBytes()+" Bytes");
					createdMsg = (new RoleReply()).create(msg);
					System.out.println("message response sent: " + "OFPT_ROLE_REPLY[25]");
					break;
			

			default: msg.release(); 
					 throw new Exception();

		}

		return createdMsg;


	}

}
