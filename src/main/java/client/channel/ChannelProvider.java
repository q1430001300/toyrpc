package client.channel;

import client.init.RpcClient;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import zookeeper.ZookeeperConstant;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class ChannelProvider {


    private ZooKeeper zooKeeper;
    CountDownLatch countDownLatch = new CountDownLatch(1);

    public void connectAndConsume() {
        try {
            zooKeeper = new ZooKeeper(ZookeeperConstant.IP + ZookeeperConstant.SEPARATOR + ZookeeperConstant.PORT, 5000, this::process);
            countDownLatch.await();
            consume();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consume() {
        try {
            List<String> addresses = zooKeeper.getChildren(ZookeeperConstant.SLASH_SEPARATOR, this::process);
            connectToServer(addresses);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void connectToServer(List<String> addresses) {
        if (Objects.equals(ChannelManager.getChannelSize(), 0)) {
            for (String address : addresses) {
                if (!address.startsWith("zoo")) {
                    String host = address.split(":")[0];
                    String port = address.split(":")[1];
                    try {
                        RpcClient.connect(Integer.parseInt(port), host);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else {
            Set<String> addressSet = ChannelManager.getAddress();
            assert addresses != null;
            for (String address : addresses) {
                if (addressSet.contains(address)) {
                    addressSet.remove(address);
                }
            }
            ChannelManager.removeChannel(addressSet);
        }
    }


    public void exceptionCaught() {
        if (zooKeeper != null) {
            try {
                zooKeeper.close();
                RpcClient.shutDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void process(WatchedEvent event) {
        if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
            if (event.getType() == Watcher.Event.EventType.None && null == event.getPath()) {
                countDownLatch.countDown();
            } else {
                if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                    try {
                        consume();
                    } catch (Exception e) {
                        exceptionCaught();
                    }
                }
            }
        }
    }
}
