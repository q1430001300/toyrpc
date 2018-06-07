package client;

import io.netty.channel.Channel;

import java.util.concurrent.CopyOnWriteArrayList;

public class ChannelManager {

    private static final CopyOnWriteArrayList<Channel> channels = new CopyOnWriteArrayList();

    public static void addChannel(Channel channel) {
        channels.add(channel);
        boolean registered = channels.get(0).isRegistered();
        System.out.println(registered);
    }

    /**
     * todo 是否可以加负载均衡???
     */
    public static Channel getChannel() {
        if (getChannelSize() <= 0) {
            throw new NullPointerException("无可用的channel");
        }
        return channels.get(0);
    }

    public static void removeChannel(Channel channel) {
        channels.remove(channel);
    }

    public static void removeAll() {
        channels.clear();
    }

    public static int getChannelSize() {
        return channels.size();
    }

}
