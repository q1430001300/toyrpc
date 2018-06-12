package client.balance;

import io.netty.channel.Channel;

import java.util.*;

/**
 * 随机数负载
 */
public class RandomBalanceStragy implements IBalanceStragy {


    @Override
    public Channel balanceStragy(Map<String, Channel> map) {
        int size = map.size();
        int index = new Random().nextInt(size);
        List<String> keys = new ArrayList();
        for (String s : map.keySet()) {
            keys.add(s);
        }
        return map.get(keys.get(index));
    }

}
