import client.ChannelFutureManager;
import client.RpcClient;
import io.netty.channel.ChannelFuture;
import protocal.RpcRequest;

public class Test {


    @org.junit.Test
    public void test1() throws InterruptedException {
        RpcClient rpcClient = new RpcClient();
        rpcClient.connect(8083, "127.0.0.1");
        ChannelFuture channelFuture = ChannelFutureManager.getChannelFuture();
        RpcRequest rpcRequest = new RpcRequest();
        System.out.println(rpcRequest);
        channelFuture.channel().writeAndFlush(rpcRequest);
    }
}
