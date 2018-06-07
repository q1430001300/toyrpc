import client.ChannelFutureManager;
import client.RpcClient;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import protocal.RpcRequest;
import serialize.object.ObjectEncode;

public class Test {


    @org.junit.Test
    public void test1() throws Exception {
        RpcClient rpcClient = new RpcClient();
        rpcClient.connect(8083, "127.0.0.1");
        ChannelFuture channelFuture = ChannelFutureManager.getChannelFuture();
        RpcRequest rpcRequest = new RpcRequest();
//        ObjectEncode objectEncoder = new ObjectEncode();
//        ChannelHandlerContext encode = channelFuture.channel().pipeline().context("encode");
//        Channel channel = channelFuture.channel();
//        System.out.println(channel);
//        DefaultChannelProgressivePromise promise = new DefaultChannelProgressivePromise(channel);
//        objectEncoder.write(encode, rpcRequest, promise);
        Thread.sleep(1000);
        System.out.println(rpcRequest);
        System.out.println(channelFuture.channel().pipeline().lastContext() == null);
        channelFuture.channel().pipeline().lastContext().writeAndFlush(rpcRequest);

    }
}
