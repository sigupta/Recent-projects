package logic;

import java.nio.ByteBuffer;
import java.util.ArrayList;



import connectSwitch.FPGABitStream;
import connectSwitch.FPGAarray;

import msgModel.FpgaParam;

public class SwitchLogic {
	public static void storeForward(FpgaParam param){
	
		System.out.println("Processing flows for switch related functions");
		
		
		//converting the bits into FPGA frame ByteBuffer
		//int[][] tmpTOR = (new FPGAarray()).convertTOR(param.getTsbitmap(), param.getIpv4dstInt(),param.getOutputPort(), param.getOutWavelength(), param.getInputPort());
		//int[][] tmpPOD = (new FPGAarray()).convertPOD(param.getTsbitmap(), param.getdropW(), param.getintraW(),param.getdropOW(),param.getintraOW());
		SwitchLogicHelper handler = new SwitchLogicHelper();
		
		
		switch(Thread.currentThread().getName()){
		case "nioEventLoopGroup-2-1": int[][] tmpTOR = (new FPGAarray()).convertTOR(param.getTsbitmap(), param.getIpv4dstInt()
														,param.getOutputPort(), param.getOutWavelength(), param.getInputPort());
									   handler.switchLogicTOR(tmpTOR, param); 
									   break ;
		case "nioEventLoopGroup-3-1": int[][] tmpPOD1 = (new FPGAarray()).convertPOD(param.getTsbitmap(), param.getdropW(), 
											param.getintraW(),param.getdropOW(),param.getintraOW());
										handler.switchLogicPOD1(tmpPOD1, param); break ;
		case "nioEventLoopGroup-4-1": int[][] tmpPOD2 = (new FPGAarray()).convertPOD(param.getTsbitmap(), param.getdropW(), 
									param.getintraW(),param.getdropOW(),param.getintraOW());
										handler.switchLogicPOD2(tmpPOD2, param); break ;
		}
		
		
		//ByteBuffer tmp = FPGABitStream.convert(param.getTsbitmap(), param.getIpv4dstn(), param.getOutputPort(), param.getOutWavelength());
		//ControlPacket.connect(tmp);
		
	}
}
