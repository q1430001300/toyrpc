package protocal;

/**
 * @auther: 潘明杰
 * @date: 2018/6/11 12:33
 * @description:
 */
public class RpcResponseUtil {

    public static <T> RpcResponse generateSuccessResopnse(String requestId, T result) {
        return generateResopnse(requestId, null, ResultCode.SUCCESS, result);
    }

    public static <T> RpcResponse generateFailResopnse(String requestId, String msg, T result) {
        return generateResopnse(requestId, msg, ResultCode.FAIL, result);
    }

    public static <T> RpcResponse generateFailResopnse(String requestId, String msg) {
        return generateResopnse(requestId, msg, ResultCode.FAIL, null);
    }

    public static <T> RpcResponse generateResopnse(String requestId, String msg, ResultCode resultCode, T result) {
        return new RpcResponse().
                setRequestId(requestId).
                setRessultCode(resultCode).
                setMsg(msg).setResult(result);
    }
}
