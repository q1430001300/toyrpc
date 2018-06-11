package client.init;

import client.channel.ChannelProvider;
import client.proxy.CglibProxy;
import client.proxy.IProxy;

public class ClientStrap {

    public static void beginClient() {
        ChannelProvider channelProvider = new ChannelProvider();
        channelProvider.connectAndConsume();
    }

    public static <T> T getClass(Class<T> t) {
        IProxy proxy = new CglibProxy();
        return proxy.getClass(t);
    }
}
