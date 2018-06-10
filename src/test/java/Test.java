import client.channel.ChannelManager;
import client.init.RpcClient;
import client.proxy.CglibProxy;
import client.proxy.IProxy;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protocal.RpcRequest;
import base.IStudent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5, 5, 50, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        IProxy proxy = new CglibProxy();


        IStudent student = proxy.getClass(IStudent.class);

        for (long i = 0; i < 5; i++) {
            Runnable runnable = new TestThread(student, i);
            threadPoolExecutor.execute(runnable);
        }


        latch.await();

    }


    public class TestThread implements Runnable {
        private IStudent student;

        private Long num;

        public TestThread(IStudent student, Long num) {
            this.student = student;
            this.num = num;
        }

        @Override
        public void run() {
            String byId = student.findById(num);
            logger.warn("请求的num:{},结果:{}", num, byId);
        }
    }
}
