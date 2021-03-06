package protocal;

import java.io.Serializable;

/**
 * 响应
 */
public class RpcResponse<T> implements Serializable {


    private String requestId;

    private ResultCode ressultCode;

    private String msg;

    private T result;


    public String getRequestId() {
        return requestId;
    }

    public RpcResponse setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public ResultCode getRessultCode() {
        return ressultCode;
    }

    public RpcResponse setRessultCode(ResultCode ressultCode) {
        this.ressultCode = ressultCode;
        return this;
    }

    public T getResult() {
        return result;
    }

    public RpcResponse setResult(T result) {
        this.result = result;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RpcResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "RpcResponse{" +
                "requestId='" + requestId + '\'' +
                ", ressultCode=" + ressultCode +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
