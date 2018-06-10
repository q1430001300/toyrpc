package balanceStragy;

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
        int index = new Random(size - 1).nextInt();
        return map.get(index);
    }
}
