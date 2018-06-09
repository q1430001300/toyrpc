import client.ChannelManager;
import client.RpcClient;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protocal.RpcRequest;
import proxy.CglibProxy;
import proxy.IProxy;
import base.IStudent;

import java.util.concurrent.CountDownLatch;

public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);
    private CountDownLatch latch = new CountDownLatch(1);

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


    @org.junit.Test
    public void test2() throws InterruptedException {
        RpcClient rpcClient = new RpcClient();
        rpcClient.connect(8083, "127.0.0.1");
        IProxy proxy = new CglibProxy();
        IStudent student = proxy.getClass(IStudent.class);
        String byId = student.findById(1L);
        System.out.println(byId);
        latch.await();

    }
}
