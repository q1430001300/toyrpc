package client.channel;

import client.balance.IBalanceStragy;
import client.balance.RandomBalanceStragy;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ChannelManager {

    private static final Map<String, Channel> channels = new ConcurrentHashMap<>();

    /**
     * 负载均衡,目前只实现随机负载,
     * 常用的算法有
     * hash负载,根据客户端ip地址hash取值,
     * 轮询,依次使用,需要通过锁实现,
     * 加权算法...
     */
    private static IBalanceStragy balanceStragy = new RandomBalanceStragy();

    public static void addChannel(String address, Channel channel) {
        channels.put(address, channel);
    }


    public static Channel getChannel() {
        if (getChannelSize() <= 0) {
            throw new NullPointerException("no available channel....");
        }

        return balanceStragy.balanceStragy(channels);
    }

    public static void removeChannel(String address) {
        channels.remove(address);
    }

    public static void removeAll() {
        channels.clear();
    }

    public static int getChannelSize() {
        return channels.size();
    }

}
