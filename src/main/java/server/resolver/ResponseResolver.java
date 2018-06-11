package server.resolver;

import protocal.RpcRequest;
import protocal.RpcResponse;
import protocal.RpcResponseUtil;
import server.init.ServiceContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @auther: 潘明杰
 * @date: 2018/6/11 12:41
 * @description:
 */
public class ResponseResolver {

    private RpcRequest rpcRequest;


    public ResponseResolver(RpcRequest rpcRequest) {
        this.rpcRequest = rpcRequest;
    }

    public RpcResponse resloveResponse() {
        assert rpcRequest != null;
        RpcResponse rpcResponse;
        Class declaringClass = rpcRequest.getDeclaringClass();
        Object invoker = ServiceContext.getInvoker(declaringClass);
        Object[] objects = rpcRequest.getObjects();
        Method method = rpcRequest.getMethod();
        try {
            Object invoke = method.invoke(invoker, objects);
            rpcResponse = RpcResponseUtil.generateSuccessResopnse(rpcRequest.getRequestId(), invoke);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            rpcResponse = RpcResponseUtil.generateFailResopnse(rpcRequest.getRequestId(), e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            rpcResponse = RpcResponseUtil.generateFailResopnse(rpcRequest.getRequestId(), e.getMessage());
        }
        return rpcResponse;
    }
}
