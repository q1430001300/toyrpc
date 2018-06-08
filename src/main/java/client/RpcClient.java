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


    public void connect(int port, String host) throws InterruptedException {
        Bootstrap b = new Bootstrap();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            b.group(bossGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ClientChannelInitializer());
            //异步连接操作
            ChannelFuture future = b.connect(new InetSocketAddress(host, port)).sync();
//            RpcRequest rpcRequest = new RpcRequest();
//            System.out.println(rpcRequest);
//            future.channel().writeAndFlush(rpcRequest);
//            ChannelManager.addChannelFuture(future.channel());
        } finally {
            //所有资源释放完成之后,清空资源,在此发起重连操作
//            bossGroup.shutdownGracefully();
        }
    }
}
