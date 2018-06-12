package server.registery;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.init.RpcServer;
import zookeeper.ZookeeperConstant;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public class ServerRegistry {
    private ZooKeeper zooKeeper;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private static final Logger logger = LoggerFactory.getLogger(ServerRegistry.class);

    public void connectAndRegister() {
        try {
            zooKeeper = new ZooKeeper(ZookeeperConstant.IP + ZookeeperConstant.SEPARATOR + ZookeeperConstant.PORT, 5000, this::process);
            countDownLatch.await();
            register();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process(WatchedEvent event) {
        if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
            if (event.getType() == Watcher.Event.EventType.None && null == event.getPath()) {
                countDownLatch.countDown();
            }
        }
    }

    public void register() {
        ResourceBundle server = ResourceBundle.getBundle("server");
        String ip = server.getString("server.ip");
        int port = Integer.parseInt(server.getString("server.port"));
        try {

            zooKeeper.create(ZookeeperConstant.BASE_PATH + ZookeeperConstant.SLASH_SEPARATOR + ip + ZookeeperConstant.SEPARATOR + port, null, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL, (int rc, String path, Object ctx,
                                           String name) -> {
                        logger.warn("path is :{},name is :{}", path, name);
                    }, null);
            RpcServer.connect(port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerRegistry serverRegistry = new ServerRegistry();

        serverRegistry.connectAndRegister();
    }
}
