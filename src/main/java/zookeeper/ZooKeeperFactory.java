package zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public class ZooKeeperFactory {

    private final static String ip;

    private final static String port;

    private ZooKeeper zooKeeper;
    CountDownLatch latch = new CountDownLatch(1);

    static {
        ResourceBundle resource = ResourceBundle.getBundle("zk");
        ip = resource.getString("zk.ip");
        port = resource.getString("zk.port");
    }

    public ZooKeeper createZookeeper() throws IOException {
        if (zooKeeper != null) {
            return zooKeeper;
        }
        return new ZooKeeper(ip + ":" + port, 50, this::process);
    }


    public void process(WatchedEvent watchedEvent) {

    }
}
