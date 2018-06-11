package client.balance;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.Random;

/**
 * 随机数负载
 */
public class RandomBalanceStragy implements IBalanceStragy {


    @Override
    public Channel balanceStragy(Map<String, Channel> map) {
        int size = map.size();
        int index = new Random().nextInt(size);
        return map.get("127.0.0.1:8080");
    }

}
