package connectController;


import static io.netty.buffer.Unpooled.*;
import io.netty.buffer.ByteBuf;
import msgModel.msgHelpers;

public class MsgDataPOD2 {
	public static int msgLength = 0;
	public static ByteBuf tempMsg = buffer(0);
	
	public static void update(ByteBuf msgPart){
		if(tempMsg.readableBytes() == 0){
			tempMsg = msgPart;
		} else {
			tempMsg = msgHelpers.merge(tempMsg, msgPart);
		}
	}
	
	public static void reset(){
		
		msgLength = 0;
		tempMsg = buffer(0);
	}
	
	
}
