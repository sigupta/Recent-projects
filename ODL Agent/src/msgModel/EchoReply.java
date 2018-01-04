package msgModel;

import static io.netty.buffer.Unpooled.buffer;

import io.netty.buffer.ByteBuf;

public class EchoReply {
	/*
	 * This function creates a Echo Reply message in response to the Echo req message received by the controller
	 * @param - Echo req message sent by controller
	 * @return - the Echo reply message generated by the Agent
	 */
	public ByteBuf create(ByteBuf msg){

		ByteBuf b = buffer(8);

		b.writeByte(4); //OF version
		b.writeByte(3); //Msg Type
		b.writeShort(8); //length
		b.writeInt(msg.getInt(4)); //Tx ID
		msg.release();

		return b;
	}
}