import base.Student;
import client.channel.ChannelManager;
import client.init.ClientStrap;
import client.init.RpcClient;
import client.proxy.CglibProxy;
import client.proxy.IProxy;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protocal.RpcRequest;
import base.IStudent;
import server.init.ServerStrap;

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
        rpcClient.connect(8080, "127.0.0.1");
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
        rpcClient.connect(8080, "127.0.0.1");
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5, 5, 50, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        IProxy proxy = new CglibProxy();


        IStudent student = proxy.getClass(IStudent.class);
        Student byId = student.findById(1L);

        latch.await();

    }


    @org.junit.Test
    public void test4() throws InterruptedException {
        ServerStrap.beginServer();
    }

    @org.junit.Test
    public void test3() throws InterruptedException {

        ClientStrap.beginClient();
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(10, 10, 50, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        IStudent aClass = ClientStrap.getClass(IStudent.class);
        for (long i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new MyThread(aClass, i));
        }
        Thread.sleep(10000);
    }


    public class MyThread implements Runnable {

        IStudent aclass;
        Long i;

        public MyThread(IStudent aclass, long i) {
            this.aclass = aclass;
            this.i = i;
        }

        @Override
        public void run() {
            Student byId = aclass.findById(i);
            System.err.println(i + "  " + byId.getId());
        }
    }

}
