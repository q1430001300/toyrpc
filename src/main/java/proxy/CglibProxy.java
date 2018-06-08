package proxy;

import client.ChannelManager;
import io.netty.channel.Channel;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import protocal.RpcRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * cglib动态代理
 */
public class CglibProxy implements MethodInterceptor, IProxy {
    private Enhancer enhancer = new Enhancer();


    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();

    }


    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Map<String, Object> map = new HashMap();
        String name = o.getClass().getName();
        String methodName = method.getName();
        map.put("className", name);
        map.put("methodName", methodName);
        map.put("arguments", objects);
        RpcRequest requestMessage = buildRequest(map);
        Channel channel = ChannelManager.getChannel();
        channel.writeAndFlush(requestMessage);
        //getResult
        return null;
    }


    private RpcRequest buildRequest(Object object) {
        RpcRequest rpcRequest = new RpcRequest();
        //todo
        return rpcRequest;
    }

    @Override
    public <T> T getClass(Class<T> tClass) {
        if (!tClass.isInterface()) {
            throw new IllegalArgumentException("传入的不是接口...");
        }
        return (T) this.getProxy(tClass);
    }
}
