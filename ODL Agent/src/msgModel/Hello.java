package msgModel;


import static io.netty.buffer.Unpooled.*;
import io.netty.buffer.ByteBuf;


public class Hello {
	/*
	 *
	 * This function creates a hello message in response to the hello message received by the controller
	 * @param - Hello message sent by controller
	 * @return - the hello message generated by the Agent
	 */
	public ByteBuf create(ByteBuf msg){

		ByteBuf b = buffer(8);

		b.writeByte(4); //version OF
		b.writeByte(0); // msg Type
		b.writeShort(8); // length
		b.writeInt(msg.getInt(4)); //TxID
		
		msg.release();


		return b;
	}
}
