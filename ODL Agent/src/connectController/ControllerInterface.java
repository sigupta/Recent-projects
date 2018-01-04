package connectController;

import java.nio.channels.Channel;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ControllerInterface {
    public void connect(String IP) throws Exception {
        String host = IP;//"127.0.0.1";//"localhost";
        int port = 6633;//ODL port;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {

                @Override
				public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ControllerInterfaceHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
    
/*public void connect_parallel(String host, int port) throws Exception {
        
        
        try {
             Bootstrap b_new = new Bootstrap();
             b_new.group(new NioEventLoopGroup()).channel(NioSocketChannel.class).remoteAddress(host, port).handler(new ControllerInterfaceHandler());
            Channel ch = b_new.connect().sync().channel();
            ChannelFuture lastWriteFuture = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (;;) {
                String line = in.readLine();
                if (line == null) break;
                lastWriteFuture = ch.write(line + "\r\n");
                if (line.toLowerCase().equals("bye")) {
                    ch.closeFuture().sync();
                    break;
                }
            }
            if (lastWriteFuture != null) lastWriteFuture.sync();
        } finally {
           // b_new.shutdown();
        }*/
         
    }
