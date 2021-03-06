package msgModel;

import io.netty.buffer.ByteBuf;
import logic.MainLogic;

import static io.netty.buffer.Unpooled.buffer;

public class FeaturesReply {
	
	/*
	 *
	 * This function creates a Features Reply message in response to the Features Request message  received by the controller
	 * @param - Features Req message sent by controller
	 * @return - the Features Rep message generated by the Agent
	 */
	
	public ByteBuf create(ByteBuf msg , int c){

		ByteBuf b = buffer(32);
		b.writeByte(4); // version
		b.writeByte(6); // type
		b.writeShort(32); //length
		b.writeInt(msg.getInt(4)); //TX ID
		switch(Thread.currentThread().getName()){
		case "nioEventLoopGroup-2-1": b.writeLong(MainLogic.datapathID[0]); break ;
		case "nioEventLoopGroup-3-1": b.writeLong(MainLogic.datapathID[1]); break ;
		case "nioEventLoopGroup-4-1": b.writeLong(MainLogic.datapathID[2]); break ;
		}
		//b.writeLong(MainLogic.datapathID[c]); // datapath ID
		b.writeInt(256); //n_buffers
		b.writeByte(1); // n_tables
		b.writeByte(0); // auxillary_ID
		b.writeShort(0); // pad[2]
		b.writeInt(0); //capabilities
		b.writeInt(0); //reserved
		msg.release();

		return b;
	}
}
