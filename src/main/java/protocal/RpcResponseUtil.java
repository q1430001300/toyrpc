package protocal;

/**
 * @auther: 潘明杰
 * @date: 2018/6/11 12:33
 * @description:
 */
public class RpcResponseUtil {

    public static RpcResponse generateSuccessResopnse(String requestId, String result) {
        return generateResopnse(requestId, null, ResultCode.SUCCESS, result);
    }

    public static RpcResponse generateFailResopnse(String requestId, String msg, String result) {
        return generateResopnse(requestId, msg, ResultCode.FAIL, result);
    }

    public static RpcResponse generateFailResopnse(String requestId, String msg) {
        return generateResopnse(requestId, msg, ResultCode.FAIL, null);
    }

    public static RpcResponse generateResopnse(String requestId, String msg, ResultCode resultCode, String result) {
        return new RpcResponse().
                setRequestId(requestId).
                setRessultCode(resultCode).
                setMsg(msg).setResult(result);
    }
}
