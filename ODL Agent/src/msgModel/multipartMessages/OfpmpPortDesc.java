package msgModel.multipartMessages;

import static io.netty.buffer.Unpooled.buffer;

import io.netty.buffer.ByteBuf;

public class OfpmpPortDesc {
	/*
	 * This class creates a multipart reply message for Port Description
	 * @return - ByteBuf with the multipart reply message formed
	 */
		public ByteBuf create(){

			ByteBuf b = buffer(64);

			// ---------for Local port ----------
						b.writeInt(-2);   // port no
						b.writeInt(0);   // pad[4]
						b.writeByte(0);  // MAC address
						b.writeByte(0);  // MAC address
						b.writeByte(0);  // MAC address
						b.writeByte(0);  // MAC address
						b.writeByte(0);  // MAC address
						b.writeByte(1);  // MAC address
						b.writeShort(0); //pad[2]
						//name formation 
						String name = "s1";
						byte[] filler = new byte[14];
						byte[] total = new byte[16];
						System.arraycopy(name.getBytes(), 0, total, 0, name.length());
						System.arraycopy(filler, 0, total, name.length(), 14);
						b.writeBytes(total);
						// flags
						b.writeInt(1); //config
						b.writeInt(1); //state
						b.writeInt(0); //current
						b.writeInt(0); //advertised
						b.writeInt(0); //supported
						b.writeInt(0); //peer
						b.writeInt(0); //current speed
						b.writeInt(0); //max speed

			



			return b;
		}
}
