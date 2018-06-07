import client.ChannelManager;
import client.RpcClient;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protocal.RpcRequest;

public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    @org.junit.Test
    public void test1() throws Exception {
        RpcClient rpcClient = new RpcClient();
        rpcClient.connect(8083, "127.0.0.1");
        Channel channel = ChannelManager.getChannel();
        RpcRequest rpcRequest = new RpcRequest();
        logger.info("xx:{}", rpcRequest);
        channel.writeAndFlush(rpcRequest);
        channel.closeFuture().sync();

//        Thread.sleep(1000);
    }
}
