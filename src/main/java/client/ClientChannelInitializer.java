package client;

import handler.ClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 用于客户端添加channelhandler
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ObjectDecoder(65536 * 1024, ClassResolvers.cacheDisabled(null)));
        ch.pipeline().addLast(new ObjectEncoder());
//        ch.pipeline().addLast(new IdleStateHandler(20, 20, 20));
        ch.pipeline().addLast(new ClientHandler());
    }
}
