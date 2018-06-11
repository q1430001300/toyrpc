package client.init;

import client.channel.ChannelManager;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class RpcClient {

    private static final EventLoopGroup group = new NioEventLoopGroup();


    public static void connect(int port, String host) throws InterruptedException {
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ClientChannelInitializer());
        ChannelFuture sync = b.connect(new InetSocketAddress(host, port)).sync();
        registChannelToContext(host, port, sync.channel());
    }


    private static void registChannelToContext(String host, int port, Channel channel) {
        String key = host + ":" + port;
        ChannelManager.addChannel(key, channel);
    }


    public static void shutDown() {
        if (group != null) {
            group.shutdownGracefully();
        }
    }
}
