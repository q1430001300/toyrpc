package client.channel;

import client.balance.IBalanceStragy;
import client.balance.RandomBalanceStragy;
import io.netty.channel.Channel;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class ChannelManager {

    /**
     * 此处原本是用concurrentHashMap来做容器,但是具有无序性,用来负载均衡可能出现某个连接
     * 一直被使用,而其他不使用的情况,然后就用linkedhashMap,有因为线程不安全,对channel的操作都
     * 加了锁
     */
    private static final Map<String, Channel> channels = new LinkedHashMap<>();

    /**
     * 负载均衡,目前只实现随机负载,
     * 常用的算法有
     * hash负载,根据客户端ip地址hash取值,
     * 轮询,依次使用,需要通过锁实现,
     * 加权算法...
     */
    private static IBalanceStragy balanceStragy = new RandomBalanceStragy();

    public static void addChannel(String address, Channel channel) {
        synchronized (ChannelManager.class) {
            channels.put(address, channel);
        }
    }


    public static Channel getChannel() {
        synchronized (ChannelManager.class) {
            if (getChannelSize() <= 0) {
                throw new NullPointerException("no available channel....");
            }

            return balanceStragy.balanceStragy(channels);
        }

    }

    public static void removeChannel(String address) {
        synchronized (ChannelManager.class) {

            channels.remove(address);
        }
    }

    public static void removeChannel(Set<String> address) {
        assert address != null;
        synchronized (ChannelManager.class) {
            for (String s : address) {
                removeChannel(s);
            }
        }

    }

    public static void removeAll() {
        synchronized (ChannelManager.class) {
            channels.clear();
        }

    }

    public static int getChannelSize() {
        return channels.size();
    }

    public static Set<String> getAddress() {
        return channels.keySet();
    }

}
