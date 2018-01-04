package msgModel.multipartMessages;

import static io.netty.buffer.Unpooled.buffer;

import io.netty.buffer.ByteBuf;

public class OfpmpMeterFeatures {

	/*
	 * This function creates a multipart reply message for meter features
	 * @return - ByteBuf with the multipart reply message formed
	 */

	public ByteBuf create(){

		ByteBuf b = buffer(16);
		b.writeInt(0); // maximum meters
		b.writeInt(0); // band types
		b.writeInt(0); // capabilities
		b.writeByte(0); //max bands
		b.writeByte(0); //max colors
		b.writeShort(0); // pad[2]
		return b;
	}
}
