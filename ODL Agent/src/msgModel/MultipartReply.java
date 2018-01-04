package msgModel;

import static io.netty.buffer.Unpooled.buffer;

import io.netty.buffer.ByteBuf;
import msgModel.multipartMessages.*;


public class MultipartReply {
	/*
	 * This method creates the multipart reply message for the specific type of multipart request
	 * @param - Multipart request message
	 * @return - The generated multipart reply
	 */
	public ByteBuf create(ByteBuf msg){

		int bodyLength = 0;
		ByteBuf body = buffer(0);

		switch(msg.getShort(8)){
			case 0: bodyLength = 1056;
					OfpmpDesc tmp = new OfpmpDesc();
						body = tmp.create();
					break;
			case 8: bodyLength = 40;
					OfpmpGroupFeatures tmp1 = new OfpmpGroupFeatures();
					body = tmp1.create();
					break;
			case 11: bodyLength = 16;
					OfpmpMeterFeatures tmp2 = new OfpmpMeterFeatures();
					body = tmp2.create();
					break;
			case 13: bodyLength = 64;
					OfpmpPortDesc tmp3 = new OfpmpPortDesc();
					body = tmp3.create();
					break;
			default : System.out.println("");
					break;
		}

		ByteBuf b = buffer(16);

		b.writeByte(4);
		b.writeByte(19);
		b.writeShort(16 + bodyLength );
		b.writeInt(msg.getInt(4));

		b.writeShort(msg.getShort(8)); //type
		b.writeShort(msg.getShort(10)); //flags
		b.writeInt(0); //pad[4]
		
		msg.release();

		return (new msgHelpers()).merge(b, body);
	}
}