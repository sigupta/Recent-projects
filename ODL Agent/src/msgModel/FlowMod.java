package msgModel;
import io.netty.buffer.ByteBuf;

import java.math.BigInteger;

/*
	 * This class contains the function to help the flowmod logic to extract relevant data from the flowmod
	 * message
	 */
public class FlowMod {
	
	/*
	 * This function takes in match data from flowmod message and returns all the different match fields 
	 * as an object of type FPGA param
	 * @param ByteBuf containing match data
	 * @returns - all the match data extracted in form of an object
	 */
	public FpgaParam matchExtractTOR (ByteBuf match , FpgaParam param){
		
		
		
		
		int lengthIndex = 7;
		// System.out.println(match.getShort(2));
		while (lengthIndex < match.getShort(2)){
			int fieldLength = match.getByte(lengthIndex);
			
				//System.out.println("FieldType : " + (match.getByte(lengthIndex-1) >>1) + "  Value :" + match.getByte(lengthIndex +i) );
				switch(match.getByte(lengthIndex-1)>>1){
				case 0: param.setInputPort(match.getInt(lengthIndex+1));
						System.out.println("Input Port : " + param.getInputPort());
						break;
				case 2: param = metadataExtractTOR(param , match.copy(lengthIndex+1,fieldLength));
						break;
				case 5: param.setEthType(match.getUnsignedShort(lengthIndex+1));
						//param.setInputPort(3);
						System.out.println("Ethernet Type : " + param.getEthType());
						break;
				case 11:param.setIpv4src(msgHelpers.ipAddr(match.copy(lengthIndex+1,fieldLength)));
						param.setIpv4srcInt(msgHelpers.ipAddr_3rdByte_only(match.copy(lengthIndex+1,fieldLength)));
						param.setIpv4srcn(match.copy(lengthIndex+1,fieldLength).getInt(0));
						System.out.println("IPv4 Source Address : " + param.getIpv4src());
						break;
				case 12:param.setIpv4dst(msgHelpers.ipAddr(match.copy(lengthIndex+1,fieldLength)));
						param.setIpv4dstInt(msgHelpers.ipAddr_3rdByte_only(match.copy(lengthIndex+1,fieldLength)));
						param.setIpv4dst_Int(msgHelpers.ipAddr_Int(match.copy(lengthIndex+1,fieldLength)));
						param.setIpv4dstn(match.copy(lengthIndex+1,fieldLength).getInt(0));
						System.out.println("IPv4 Destination Address : " + param.getIpv4dst());
						break;
				default: System.out.println("some data send in flowmod not supported by the agent at the moment");
						break;
				
			}
			
			lengthIndex += fieldLength +4;
			
		}
		
		
		match.release();
		return param;
	}
	
public FpgaParam matchExtractPOD (ByteBuf match , FpgaParam param){
		
		
		
		
		int lengthIndex = 7;
		// System.out.println(match.getShort(2));
		while (lengthIndex < match.getShort(2)){
			int fieldLength = match.getByte(lengthIndex);
			
				//System.out.println("FieldType : " + (match.getByte(lengthIndex-1) >>1) + "  Value :" + match.getByte(lengthIndex +i) );
				switch(match.getByte(lengthIndex-1)>>1){
				case 0: param.setInputPort(match.getInt(lengthIndex+1));
						System.out.println("Input Port : " + param.getInputPort());
						break;
				case 2: param = metadataExtractPOD(param , match.copy(lengthIndex+1,fieldLength));
						break;
				case 5: param.setEthType(match.getUnsignedShort(lengthIndex+1));
						System.out.println("Ethernet Type : " + param.getEthType());
						break;
				case 11:param.setIpv4src(msgHelpers.ipAddr(match.copy(lengthIndex+1,fieldLength)));
						param.setIpv4srcInt(msgHelpers.ipAddr_3rdByte_only(match.copy(lengthIndex+1,fieldLength)));
						param.setIpv4srcn(match.copy(lengthIndex+1,fieldLength).getInt(0));
						System.out.println("IPv4 Source Address : " + param.getIpv4src());
						break;
				case 12:param.setIpv4dst(msgHelpers.ipAddr(match.copy(lengthIndex+1,fieldLength)));
						param.setIpv4dstInt(msgHelpers.ipAddr_3rdByte_only(match.copy(lengthIndex+1,fieldLength)));
						param.setIpv4dst_Int(msgHelpers.ipAddr_Int(match.copy(lengthIndex+1,fieldLength)));
						param.setIpv4dstn(match.copy(lengthIndex+1,fieldLength).getInt(0));
						System.out.println("IPv4 Destination Address : " + param.getIpv4dst());
						break;
				default: System.out.println("some data send in flowmod not supported by the agent at the moment");
						break;
				
			}
			
			lengthIndex += fieldLength +4;
			
		}
		
		
		match.release();
		return param;
	}
	
	/*
	 * This function helps the match extract function to extract some parameters from the metadata and metadata mask field from 
	 * match
	 * @param - an FpgaParam object to change the values of metadata param
	 * @return - an FpgaParam object where the values have been changed
	 */
	public static FpgaParam metadataExtractTOR(FpgaParam param, ByteBuf md){
		String metadataHex= "";
		String metadataMaskHex= "";
	//	System.out.println("Length of metadata received : " + md.readableBytes());
		
		
		for (int i = 0;i<=7;i++){
			int d = md.getUnsignedByte(i);
		//	System.out.println("Integer d ="+d);
			if(d<=15){
				metadataHex = metadataHex +"0"+ decimal2Hex(d);
			}else
			metadataHex = metadataHex + decimal2Hex(d);
		}
		
		
		
		for (int i = 8;i<=15;i++){
			int d = md.getUnsignedByte(i);
		//	System.out.println("Integer d ="+d);
			if(d<=15){
				metadataMaskHex = metadataMaskHex +"0"+ decimal2Hex(d);
			}else
			metadataMaskHex = metadataMaskHex + decimal2Hex(d);	
		}

		/*****************MetaData Processing***********************************/
		
		System.out.println("		/********************** Translation of Optical Parameters ******************/");
		System.out.println("");
		BigInteger metadataDeci = hex2BigInteger(metadataHex);
		System.out.println("			<Metadata>"+metadataDeci+"</Metadata> ");
		String metadataBinary = BigIntgerToBinary (metadataDeci,64);
		String PadZeros = "";
	
		if(metadataBinary.length() < 64){
			for (int i=1; i<metadataBinary.length()-64; i++){
				PadZeros = PadZeros + "0";
			}
			metadataBinary = PadZeros + metadataBinary;
		}
		String metadataString = metadataBinary ;
		
		
		System.out.println("");
		
        /*****************MetaData-Mask Processing***********************************/		
		BigInteger metadataMaskDeci = hex2BigInteger(metadataMaskHex);
		System.out.println("			<Metadata-Mask>"+metadataMaskDeci+"</Metadata-Mask> ");
	
		metadataBinary = BigIntgerToBinary (metadataMaskDeci,64);
		metadataBinary = metadataBinary.substring(0, 49);
		System.out.println("");
		
		/*****************TRANSLATION ALGORTIHM ***********************************/	
		metadataString = metadataString + metadataBinary ;
		metadataBinary = metadataString;
		String bmp = metadataBinary.substring(0,80);
		param.setTsbitmap(msgHelpers.fromString(bmp));
		System.out.println("			Timeslot Bitmap = "+bmp);
		System.out.println("");
		String Wavelength_Match = metadataBinary.substring(80,88);
		param.setInWavelength(Integer.parseInt(Wavelength_Match,2));
		System.out.println("			Wavelength (match) = "+Wavelength_Match+"  ----------------->  "+Integer.parseInt(Wavelength_Match,2));
		System.out.println("");
		String ScheduleID = metadataBinary.substring(88,96);
		param.setScheduleID(Integer.parseInt(ScheduleID,2)); 
		System.out.println("			Schedule ID = "+ScheduleID+"  ----------------->  "+param.getScheduleID());
		System.out.println("");
		String FlowCounter = metadataBinary.substring(96,104);
		param.setFlowCounter(Integer.parseInt(FlowCounter,2));
		System.out.println("			Flow Counter = "+FlowCounter+"  ----------------->  "+ param.getFlowCounter());
		System.out.println("");
		String Wavelength_Action = metadataBinary.substring(104,112);
		param.setOutWavelength(Integer.parseInt(Wavelength_Action,2));
		System.out.println("			Wavelength (action) = "+Wavelength_Action+"  ----------------->  "+ param.getOutWavelength());
		System.out.println("");
		
		String interIntraBit = metadataBinary.substring(112,113);
		param.setInterIntraBit(Integer.parseInt(interIntraBit,2));
		System.out.println("			Special Bit = "+interIntraBit+"  ----------------->  "+ param.getInterIntraBit());
		System.out.println("");
		
		System.out.println("		/*************************************************** End of Translation ********************************************/");
		
		md.release();
		return param;
	}
	
	
	public static FpgaParam metadataExtractPOD(FpgaParam param, ByteBuf md){
		String metadataHex= "";
		String metadataMaskHex= "";
	//	System.out.println("Length of metadata received : " + md.readableBytes());
		
		for (int i = 0;i<=7;i++){
			int d = md.getUnsignedByte(i);
		//	System.out.println("Integer d ="+d);
			if(d<=15){
				metadataHex = metadataHex +"0"+ decimal2Hex(d);
			}else
			metadataHex = metadataHex + decimal2Hex(d);
		}
		
		
		
		for (int i = 8;i<=15;i++){
			int d = md.getUnsignedByte(i);
		//	System.out.println("Integer d ="+d);
			if(d<=15){
				metadataMaskHex = metadataMaskHex +"0"+ decimal2Hex(d);
			}else
			metadataMaskHex = metadataMaskHex + decimal2Hex(d);	
		}

		/*****************MetaData Processing***********************************/
		
		System.out.println("		/********************* Translation of Optical Parameters *******************/");
		System.out.println("");
		BigInteger metadataDeci = hex2BigInteger(metadataHex);
		System.out.println("			<Metadata>"+metadataDeci+"</Metadata> ");
		String metadataBinary = BigIntgerToBinary (metadataDeci,64);

		String PadZeros = "";
	
		if(metadataBinary.length() < 64){
			for (int i=1; i<metadataBinary.length()-64; i++){
				PadZeros = PadZeros + "0";
			}
			metadataBinary = PadZeros + metadataBinary;
		}
		String metadataString = metadataBinary ;
		
		
		System.out.println("");
		
        /*****************MetaData-Mask Processing***********************************/		
		BigInteger metadataMaskDeci = hex2BigInteger(metadataMaskHex);
		System.out.println("			<Metadata-Mask>"+metadataMaskDeci+"</Metadata-Mask> ");
	
		metadataBinary = BigIntgerToBinary (metadataMaskDeci,64);
		metadataBinary = metadataBinary.substring(0, 49);
		System.out.println("");
		
		/*****************TRANSLATION ALGORTIHM ***********************************/	
		metadataString = metadataString + metadataBinary ;
		metadataBinary = metadataString;
	
		String bmp = metadataBinary.substring(0,80);
		param.setTsbitmap(msgHelpers.fromString(bmp));
		System.out.println("			Timeslot Bitmap = "+bmp);
		System.out.println("");
		String Wavelength_Match = metadataBinary.substring(80,88);
		param.setInWavelength(Integer.parseInt(Wavelength_Match,2));
		System.out.println("			Wavelength (match) = "+Wavelength_Match+"  ----------------->  "+Integer.parseInt(Wavelength_Match,2));
		System.out.println("");
		String ScheduleID = metadataBinary.substring(88,96);
		param.setScheduleID(Integer.parseInt(ScheduleID,2)); 
		System.out.println("			Schedule ID = "+ScheduleID+"  ----------------->  "+param.getScheduleID());
		System.out.println("");
		
		String FlowCounter = metadataBinary.substring(96,104);
		param.setFlowCounter(Integer.parseInt(FlowCounter,2));
		System.out.println("			Flow Counter = "+FlowCounter+"  ----------------->  "+ param.getFlowCounter());
		System.out.println("");
		
		
		String Wavelength_Action = metadataBinary.substring(104,112);
		if(Integer.parseInt(Wavelength_Action,2) == 1)
		param.setOutWavelength(2);
		if(Integer.parseInt(Wavelength_Action,2) == 2)
		param.setOutWavelength(1);
		System.out.println("			Wavelength (action) = "+Wavelength_Action+"  ----------------->  "+ param.getOutWavelength());
		System.out.println("");
		
		String interIntraBit = metadataBinary.substring(112,113);
		param.setInterIntraBit(Integer.parseInt(interIntraBit,2));
		System.out.println("			Special Bit = "+interIntraBit+"  ----------------->  "+ param.getInterIntraBit());
		System.out.println("");
		
		System.out.println("		/*************************************************** End of Translation ********************************************/");
		
		md.release();
		return param;
	}
	
	public static String decimal2Hex(int d) {
        String digits = "0123456789ABCDEF";
        if (d == 0) return "0";
        String hex = "";
        while (d > 0) {
            int digit = d % 16;                // rightmost digit
            hex = digits.charAt(digit) + hex;  // string concatenation
            d = d / 16;
        }
        return hex;
    }
	
	public static Long hex2Decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        Long val = new Long(0);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }
	
	
	public static BigInteger hex2BigInteger(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        
        BigInteger value = new BigInteger("0");
      
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            value = (value.multiply(new BigInteger("16")).add(BigInteger.valueOf(d)));
        }
        return value;
    }
	
	static String BigIntgerToBinary (BigInteger n, int noOfbits) {
	    char[] c = new char[noOfbits];
	    for(int i = noOfbits-1; i >= 0; i--) {
	    	BigInteger remainder = n.remainder(new BigInteger("2"));
	    	if(remainder.compareTo(new BigInteger("0")) != 0){
	    		c[i] = '1';
	    	} else {
	    		c[i] = '0';
	    	}
	    	n = n.divide(new BigInteger("2"));
	    	
	    }
	    return new String(c);        
	}
	
	
	static String BigIntgerToBinary_new (BigInteger n, int noOfbits) {
	    char[] c = new char[64];
	    String binary = "";
	 //   for(int i = noOfbits-1; i >= 0; i--) {
	    int i = 0;
	    while (n.compareTo(new BigInteger("2")) != -1){ // || n.compareTo(new BigInteger("1")) != 0) {	
	    	BigInteger remainder = n.remainder(new BigInteger("2"));
	  //  	System.out.println("Remainder ="+remainder);
	    	if(remainder.compareTo(new BigInteger("0")) != 0){
	    		c[i] = '1';
	    	} else {
	    		c[i] = '0';
	    	}
	    	n = n.divide(new BigInteger("2"));
	    //	System.out.println("n = "+n);
	    	i++;
	    }
	    
	 //   System.out.println("last value of n ="+n);
	    if(n.compareTo(new BigInteger("0")) == 0){
	    	c[i] = '0';
	    }
	    else c[i] = '1';
	    
	    int BinaryLength = i;
	 //   }
	    binary = new String (c);
    	
    	char[] binarychar = new char[64];
    	int j =0;
    	for (int k= BinaryLength; k >=0 ; k--){
    		 binarychar[j] = binary.charAt(k); 
    	//	 System.out.println("character = "+binarychar[j]);
    		 j++;
    	}
    		
    		
    	
	    return new String(binarychar);        
	}
}
