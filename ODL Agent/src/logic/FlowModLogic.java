package logic;
import static io.netty.buffer.Unpooled.buffer;



import io.netty.buffer.ByteBuf;
import msgModel.*;

public class FlowModLogic {
	/*
	 * This class separates the logic for extraction of key FPGA parameters and forwarding them to the 
	 * FPGA switch
	 */
	public static void initiateTOR(ByteBuf msg){
		//checking match
				System.out.println("FlowMod length : " + msg.getShort(2));
				FpgaParam values = new FpgaParam();

				System.out.println("FlowMod received --- Displaying received field data ");
				int matchLength = msg.getShort(50);
				int actualMatchLength = matchLength + (8 - (matchLength % 8) ) %8; // adjusting length to include padding also

				values = (new FlowMod()).matchExtractTOR(msg.copy(48 ,  matchLength),values);
				
				int instructionIndex = 48 + actualMatchLength;
				
				
				if(msg.getShort(2) > instructionIndex){
					values = FlowModLogic.instructionExtractTOR(msg.copy(instructionIndex , msg.getShort(instructionIndex + 2)) , values);
				}
				msg.release();
				
				// Sent for further processing for the switch
			//	System.out.println("Entering switch logic");
				SwitchLogic.storeForward(values);
				
				
						
	}
	
	
	public static void initiatePOD(ByteBuf msg){
		//checking match
				System.out.println("FlowMod length : " + msg.getShort(2));
				FpgaParam values = new FpgaParam();

				System.out.println("FlowMod received --- Displaying received field data ");
				int matchLength = msg.getShort(50);
				int actualMatchLength = matchLength + (8 - (matchLength % 8) ) %8; // adjusting length to include padding also

				values = (new FlowMod()).matchExtractPOD(msg.copy(48 ,  matchLength),values);
				
				int instructionIndex = 48 + actualMatchLength;
				
				
				if(msg.getShort(2) > instructionIndex){
					values = FlowModLogic.instructionExtractPOD(msg.copy(instructionIndex , msg.getShort(instructionIndex + 2)) , values);
				}
				msg.release();
				
				
				
				// Sent for further processing for the switch
			//	System.out.println("Entering switch logic");
				SwitchLogic.storeForward(values);
				
				
						
	}
	
	/*
	 *  This function extracts data from Instruction structure in FlowMod
	 *  @param - ByteBuf containing instruction part of FLowmod
	 *  @return - FPGA param object containing the values extracted from instruction set  
	 */
	
public static FpgaParam instructionExtractTOR (ByteBuf inst , FpgaParam param){
		
		if (inst.getShort(2) >8 && inst.getShort(8) == 0 ){
			param.setOutputPort(inst.getInt(12));
			System.out.println("Output port :  " + param.getOutputPort());
		}
		
		inst.release();
		
		return param;
	}
	
	public static FpgaParam instructionExtractPOD (ByteBuf inst , FpgaParam param){
		
		if (inst.getShort(2) >8 && inst.getShort(8) == 0 ){
			param.setOutputPort(inst.getInt(12));
			System.out.println("Output port :  " + param.getOutputPort());
			
						  
			if(param.getOutputPort() == 2 && param.getInterIntraBit() == 0){
			
				// nothing to be done
				System.out.println("No operation");
			}
			
			if(param.getOutputPort() == 2 && param.getInterIntraBit() == 1){
				// can't happen
				
				System.out.println("Should not happen");
			}
			
			if((param.getOutputPort() == 3 || param.getOutputPort() == 4) && param.getInterIntraBit() == 1){
				//drop_w = 1;
				param.setDropW(1);
				//intra_ow = 1;
				param.setIntraOW(1);
				System.out.println("out port = 3 or 4 and Intra Bit =1");
			}
			
			if((param.getOutputPort() == 3 || param.getOutputPort() == 4) && param.getInterIntraBit() == 0){
				//drop_w = 1;
				param.setDropW(1);
				System.out.println("out port = 3 or 4 and Intra Bit =0");
			}  
		  
		}
		
		inst.release();
		
		return param;
	}

}
