package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import protocal.RpcRequest;

import java.net.InetSocketAddress;

public class RpcClient {

    EventLoopGroup group = null;

    public void connect(int port, String host) throws InterruptedException {
        Bootstrap b = new Bootstrap();
        group = new NioEventLoopGroup();
        b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ClientChannelInitializer());
        b.connect(new InetSocketAddress(host, port)).sync();
    }

    public void shutDown() {
        if (group != null) {
            group.shutdownGracefully();
        }
    }
}
