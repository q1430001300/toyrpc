package result;

import protocal.RpcResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用来处理请求结果
 */
public class RpcResultContext {

    private static Map<String, RpcResultHandler> resultHandlerMap = new ConcurrentHashMap<>();


    public static RpcResponse getResponse(String requestId, RpcResultHandler rpcResultHandler) {
        try {
            resultHandlerMap.put(requestId, rpcResultHandler);
            rpcResultHandler.getLatch().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultHandlerMap.get(requestId).getResponse();
    }


    public static void addRpcResultHandler(String requestId, RpcResponse rpcResponse) {
        RpcResultHandler rpcResultHandler = resultHandlerMap.get(requestId);
        rpcResultHandler.setResponse(rpcResponse);
        rpcResultHandler.getLatch().countDown();

    }
}
