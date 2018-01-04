package connectSwitch;


import java.nio.ByteBuffer;
import java.util.BitSet;

public class FPGABitStream {

	/**
	 * It generates a bit steam containing the 3 parameters below 80 times in a sequence
	 * corresponding to the bit indicator in the timeslot bitmap
	 *
	 * @param ipAddr - 32 bit IP address
	 * @param outPort - 8 bit output port
	 * @param waveLength - 8 bit wavelength
	 * @param timeslot - Byte array of length 10 containing the timeslot bitmap
	 *
	 * @returns A Bytebuffer containing the 8*80 bytes of flow information
	 */
	public static ByteBuffer convert(byte[] timeslot, int ipAddr, int outPort, byte waveLength){

		BitSet timeStamp = BitSet.valueOf(timeslot);
		ByteBuffer bb = ByteBuffer.allocateDirect(880);

		for (int i=0 ; i<80 ; i++){

			if(timeStamp.get(i)){
				bb.putInt(ipAddr); // adding IP address
				bb.putShort((short)0); // 16 bit padding
				bb.putInt(outPort); //output port
				bb.put(waveLength); //wavelength

			} else {
				for(int j=0;j<11;j++){
					bb.put((byte)0);
				}
			}

		}
		return bb;
	}
}