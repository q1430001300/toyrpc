package client;

import io.netty.channel.ChannelFuture;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author pmj
 * 用于管理channelFuture
 */
public class ChannelFutureManager {

    private static final CopyOnWriteArrayList<ChannelFuture> futures = new CopyOnWriteArrayList();

    public static void addChannelFuture(ChannelFuture channelFuture) {
        futures.add(channelFuture);
    }

    /**
     * todo 是否可以加负载均衡???
     */
    public static ChannelFuture getChannelFuture() {
        if (getFutureSize() <= 0) {
            throw new NullPointerException("无可用的channel");
        }
        return futures.get(0);
    }

    public static void removeFuture(ChannelFuture channelFuture) {
        futures.remove(channelFuture);
    }

    public static void removeAll() {
        futures.clear();
    }

    public static int getFutureSize() {
        return futures.size();
    }

}
