package balanceStragy;

import io.netty.channel.Channel;

import java.util.Map;

/**
 * 负载均衡接口
 */
public interface IBalanceStragy {

    /**
     * 负载均衡的策略接口
     * @param map
     * @return
     */
    Channel balanceStragy(Map<String, Channel> map);
}
