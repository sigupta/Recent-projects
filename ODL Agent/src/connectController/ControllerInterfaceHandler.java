package connectController;

import static io.netty.buffer.Unpooled.buffer;
import io.netty.buffer.ByteBuf;
import logic.MainLogic;
import msgModel.msgHelpers;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

	/*
	 * This class is used to create helper methods to assist the agent to read or write on the controller interface
	 */


public class ControllerInterfaceHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf buf;

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
    ctx.flush();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        buf = ctx.alloc().buffer(4); // (1)
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        buf.release(); // (1)
        buf = null;
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    	HandlerChannelRead cr = new HandlerChannelRead();
    	
    	String threadName = Thread.currentThread().getName();
    	//System.out.println(threadName);
    	switch (threadName){
    	case "nioEventLoopGroup-2-1" : cr.channelReadTOR(ctx, msg);
    									break;
    	case "nioEventLoopGroup-3-1" : cr.channelReadPOD1(ctx, msg);
										break;
    	case "nioEventLoopGroup-4-1" : cr.channelReadPOD2(ctx, msg);
										break;
    	default : System.out.println("Thread naming incorrect");	
    				break;
    	
    	}
    	
    	
    }




    

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    
    
    public void generateResponseTOR(ChannelHandlerContext ctx, ByteBuf msg){
    	try {
    		//	System.out.println("message type received : " + message.getByte(1));
    			ByteBuf responseMsg = (new MainLogic()).GenerateTOR(msg.getByte(1),msg);
    		//	System.out.println( "message response type : " + responseMsg.getByte(1));
    			ctx.writeAndFlush(responseMsg);
    			responseMsg.release();


    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    		//	System.out.println("");
    		}
    }
    
    public void generateResponsePOD(ChannelHandlerContext ctx, ByteBuf msg){
    	try {
    		//	System.out.println("message type received : " + message.getByte(1));
    			ByteBuf responseMsg = (new MainLogic()).GeneratePOD(msg.getByte(1),msg);
    		//	System.out.println( "message response type : " + responseMsg.getByte(1));
    			ctx.writeAndFlush(responseMsg);
    			responseMsg.release();


    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    		//	System.out.println("");
    		}
    }
}
