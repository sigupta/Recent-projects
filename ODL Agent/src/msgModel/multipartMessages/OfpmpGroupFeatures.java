package msgModel.multipartMessages;

import static io.netty.buffer.Unpooled.buffer;
import io.netty.buffer.ByteBuf;

public class OfpmpGroupFeatures {
	/*
	 * This function creates a multipart reply message for group features
	 * @return - ByteBuf with the multipart reply message formed
	 */
	
	public ByteBuf create(){

		ByteBuf b = buffer(40);
		b.writeInt(0); // types
		b.writeInt(0); // capabilities
		
		//max groups
		b.writeInt(0); 
		b.writeInt(0); 
		b.writeInt(0); 
		b.writeInt(0); 
		
		//actions
		b.writeInt(0); 
		b.writeInt(0); 
		b.writeInt(0); 
		b.writeInt(0); 
				
		return b;
	}

}
