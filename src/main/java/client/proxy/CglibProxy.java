package client.proxy;

import client.channel.ChannelManager;
import io.netty.channel.Channel;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protocal.ResultCode;
import protocal.RpcRequest;
import client.result.RpcResultContext;
import client.result.RpcResultHandler;
import protocal.RpcResponse;

import java.lang.reflect.Method;

/**
 * cglib动态代理
 */
public class CglibProxy implements MethodInterceptor, IProxy {

    private Enhancer enhancer = new Enhancer();

    private static final Logger logger = LoggerFactory.getLogger(CglibProxy.class);

    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();

    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Class<?> declaringClass = method.getDeclaringClass();
        if (!declaringClass.isAssignableFrom(Object.class)) {
            Class<?> returnType = method.getReturnType();
            RpcRequest request = buildRequest(method, declaringClass, objects);
            logger.warn("request msg:{}", request);
            Channel channel = ChannelManager.getChannel();
            channel.writeAndFlush(request);
            //getResult
            RpcResponse response = RpcResultContext.getResponse(request.getRequestId(), new RpcResultHandler());
            if (response.getRessultCode().equals(ResultCode.SUCCESS)) {
                return response.getResult();
            } else {
                throw new Exception(response.getMsg());
            }
        }
        return method.invoke(o, objects);

    }

    /**
     * 构造请求
     *
     * @param method
     * @param declaringClass
     * @param objects
     * @return
     */
    private RpcRequest buildRequest(Method method, Class<?> declaringClass, Object[] objects) {
        return new RpcRequest()
                .setMethod(method.getName())
                .setDeclaringClass(declaringClass)
                .setObjects(objects)
                .setParameterTypes(method.getParameterTypes());
    }


    @Override
    public <T> T getClass(Class<T> tClass) {
        if (!tClass.isInterface()) {
            throw new IllegalArgumentException("the param is not a interface,pls check out!");
        }
        return (T) this.getProxy(tClass);
    }
}
