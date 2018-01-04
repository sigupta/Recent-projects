package msgModel.multipartMessages;

import static io.netty.buffer.Unpooled.buffer;

import io.netty.buffer.ByteBuf;

public class OfpmpDesc {
	/*
	 * This class creates the switch Description multipart message reply
	 * @return - ByteBuf containing the multipart reply
	 */
	public ByteBuf create(){

		ByteBuf b = buffer(1056);
		String manufacturerDesc = "GWDG";
		String hardwareDesc = "Open flow agent ";
		String softwareDesc = "Java Eclipse";
		String serialNo = "12825651210124";
		String dpDebug = "none";

		byte[] tmp = new byte[1056];

		System.arraycopy(manufacturerDesc.getBytes(), 0, tmp, 0, manufacturerDesc.length());
		System.arraycopy(hardwareDesc.getBytes(), 0, tmp, 256, hardwareDesc.length());
		System.arraycopy(softwareDesc.getBytes(), 0, tmp, 512, softwareDesc.length());
		System.arraycopy(serialNo.getBytes(), 0, tmp, 768, serialNo.length());
		System.arraycopy(dpDebug.getBytes(), 0, tmp, 800, dpDebug.length());



		return b.writeBytes(tmp);


	}
}