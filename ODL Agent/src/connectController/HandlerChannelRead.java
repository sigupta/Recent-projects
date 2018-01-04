package connectController;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class HandlerChannelRead {

	
	 public void channelReadTOR(ChannelHandlerContext ctx, Object msg) {

   	 ByteBuf m = (ByteBuf) msg;

        if (m.isReadable()){
        	int contentLength = m.readableBytes();
       // 	System.out.println("cl = " + contentLength);
        	int contentIndex = 0;
        	
        	
        	
        	while (contentIndex < contentLength ){
        		
        		if(MsgDataTOR.msgLength == 0){
            		MsgDataTOR.msgLength = m.getShort(contentIndex + 2);
            	}
     //   		System.out.println("current message Length " + MsgDataTOR.msgLength);
        		int bufferLength = MsgDataTOR.tempMsg.readableBytes();
    //    		System.out.println("current buffer Length " + bufferLength);
        		
        		if(MsgDataTOR.msgLength == bufferLength + (contentLength - contentIndex )){
        			MsgDataTOR.update(m.copy(contentIndex , contentLength - contentIndex));
        			(new ControllerInterfaceHandler()).generateResponseTOR(ctx, MsgDataTOR.tempMsg);
        			MsgDataTOR.reset();
        			contentIndex +=(contentLength - contentIndex);
        			} else if(MsgDataTOR.msgLength < (bufferLength + (contentLength - contentIndex))){
        				System.out.println(MsgDataTOR.msgLength - bufferLength);
        				ByteBuf reqdPart = m.copy(contentIndex , MsgDataTOR.msgLength - bufferLength);
        				MsgDataTOR.update(reqdPart);
        				(new ControllerInterfaceHandler()).generateResponseTOR(ctx, MsgDataTOR.tempMsg);
        				contentIndex += ( MsgDataTOR.msgLength - bufferLength);
        				MsgDataTOR.reset();
        				 
        				
        			} else if(MsgDataTOR.msgLength > (bufferLength + (contentLength - contentIndex))){
        				MsgDataTOR.update(m.copy(contentIndex , contentLength - contentIndex));
        				contentIndex += (contentLength - contentIndex);
        			}
        		
        		
        	}
        	}
        m.release();
       }
	 
	 public void channelReadPOD1(ChannelHandlerContext ctx, Object msg) {

	   	 ByteBuf m = (ByteBuf) msg;

	        if (m.isReadable()){
	        	int contentLength = m.readableBytes();
	       // 	System.out.println("cl = " + contentLength);
	        	int contentIndex = 0;
	        	
	        	
	        	
	        	while (contentIndex < contentLength ){
	        		
	        		if(MsgDataPOD1.msgLength == 0){
	            		MsgDataPOD1.msgLength = m.getShort(contentIndex + 2);
	            	}
	     //   		System.out.println("current message Length " + MsgDataPOD1.msgLength);
	        		int bufferLength = MsgDataPOD1.tempMsg.readableBytes();
	    //    		System.out.println("current buffer Length " + bufferLength);
	        		
	        		if(MsgDataPOD1.msgLength == bufferLength + (contentLength - contentIndex )){
	        			MsgDataPOD1.update(m.copy(contentIndex , contentLength - contentIndex));
	        			(new ControllerInterfaceHandler()).generateResponsePOD(ctx, MsgDataPOD1.tempMsg);
	        			MsgDataPOD1.reset();
	        			contentIndex +=(contentLength - contentIndex);
	        			} else if(MsgDataPOD1.msgLength < (bufferLength + (contentLength - contentIndex))){
	        				System.out.println(MsgDataPOD1.msgLength - bufferLength);
	        				ByteBuf reqdPart = m.copy(contentIndex , MsgDataPOD1.msgLength - bufferLength);
	        				MsgDataPOD1.update(reqdPart);
	        				(new ControllerInterfaceHandler()).generateResponsePOD(ctx, MsgDataPOD1.tempMsg);
	        				contentIndex += ( MsgDataPOD1.msgLength - bufferLength);
	        				MsgDataPOD1.reset();
	        				 
	        				
	        			} else if(MsgDataPOD1.msgLength > (bufferLength + (contentLength - contentIndex))){
	        				MsgDataPOD1.update(m.copy(contentIndex , contentLength - contentIndex));
	        				contentIndex += (contentLength - contentIndex);
	        			}
	        		
	        		
	        	}
	        	}
	        m.release();
	       }
	 
	 public void channelReadPOD2(ChannelHandlerContext ctx, Object msg) {

	   	 ByteBuf m = (ByteBuf) msg;

	        if (m.isReadable()){
	        	int contentLength = m.readableBytes();
	       // 	System.out.println("cl = " + contentLength);
	        	int contentIndex = 0;
	        	
	        	
	        	
	        	while (contentIndex < contentLength ){
	        		
	        		if(MsgDataPOD2.msgLength == 0){
	            		MsgDataPOD2.msgLength = m.getShort(contentIndex + 2);
	            	}
	     //   		System.out.println("current message Length " + MsgDataPOD2.msgLength);
	        		int bufferLength = MsgDataPOD2.tempMsg.readableBytes();
	    //    		System.out.println("current buffer Length " + bufferLength);
	        		
	        		if(MsgDataPOD2.msgLength == bufferLength + (contentLength - contentIndex )){
	        			MsgDataPOD2.update(m.copy(contentIndex , contentLength - contentIndex));
	        			(new ControllerInterfaceHandler()).generateResponsePOD(ctx, MsgDataPOD2.tempMsg);
	        			MsgDataPOD2.reset();
	        			contentIndex +=(contentLength - contentIndex);
	        			} else if(MsgDataPOD2.msgLength < (bufferLength + (contentLength - contentIndex))){
	        				System.out.println(MsgDataPOD2.msgLength - bufferLength);
	        				ByteBuf reqdPart = m.copy(contentIndex , MsgDataPOD2.msgLength - bufferLength);
	        				MsgDataPOD2.update(reqdPart);
	        				(new ControllerInterfaceHandler()).generateResponsePOD(ctx, MsgDataPOD2.tempMsg);
	        				contentIndex += ( MsgDataPOD2.msgLength - bufferLength);
	        				MsgDataPOD2.reset();
	        				 
	        				
	        			} else if(MsgDataPOD2.msgLength > (bufferLength + (contentLength - contentIndex))){
	        				MsgDataPOD2.update(m.copy(contentIndex , contentLength - contentIndex));
	        				contentIndex += (contentLength - contentIndex);
	        			}
	        		
	        		
	        	}
	        	}
	        m.release();
	       }
}
