package msgModel;

import static io.netty.buffer.Unpooled.buffer;

import io.netty.buffer.ByteBuf;
import java.util.*;
	/*
	 * This class contains helper methods which may be used during the creation of messages or by the Main Logic
	 * for assistance
	 */
public class msgHelpers {
	/*
	 * This function can merge two messages in form of ByteBuf
	 * @param ByteBuf - the first of the two messages to be merged
	 * @param ByteBuf - the second of the two messages to be merged
	 * @return - The merged message
	 */
	public static ByteBuf merge(ByteBuf msg1 , ByteBuf msg2){
		int length1 = msg1.readableBytes();
		int length2 = msg2.readableBytes();

	//	System.out.println(length1+length2);

		ByteBuf mergedMsg = buffer(length1 + length2);
		mergedMsg.writeBytes(msg1);
		mergedMsg.writeBytes(msg2);
		
		msg1.release();
		msg2.release();

		return mergedMsg;
	}
	
	public static BitSet fromString(String binary) {
	     BitSet bitset = new BitSet(binary.length());
	     for (int i = 0; i < binary.length(); i++) {
	         if (binary.charAt(i) == '1') {
	             bitset.set(i);
	         }
	     }
	     return bitset;
	 }
	
	/*
	 * This function converts a 32 bit IP address in form of ByteBuf to a IP address of form "192.168.1.1"
	 * @param - The IP address in form of Bytes
	 * @returns - String of the IP of form "192.168.1.1"
	 */
	public static String ipAddr(ByteBuf ip){
		String ipAddr = "";
		
		ipAddr += ip.getByte(0);
		
		for (int i=1; i<4 ;i++){
			ipAddr = ipAddr + "." + ip.getByte(i);
		}
		
		ip.release();
		return ipAddr;
 	}
	
	public static int ipAddr_3rdByte_only(ByteBuf ip){
		String ipAddr = "";
		int ipAddrByte3 = 0;
		ipAddr = ""+ip.getByte(2);
		ipAddrByte3 = Integer.parseInt(ipAddr);
		System.out.println("*******IP Address "+ipAddrByte3);
		
		
		ip.release();
		return ipAddrByte3;
 	}
	
	public static int ipAddr_Int(ByteBuf ip){
		
		String ipAddr_without_dots = "";
		
		ipAddr_without_dots += ip.getByte(0);
		
		for (int i=1; i<4 ;i++){
			
			ipAddr_without_dots += ip.getByte(i);
		}
		
		ip.release();
		return Integer.parseInt(ipAddr_without_dots);
 	}
}
