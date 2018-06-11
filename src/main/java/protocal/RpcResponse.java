package protocal;

import java.io.Serializable;

/**
 * 响应
 */
public class RpcResponse implements Serializable {


    private String requestId;

    private ResultCode ressultCode;

    private String msg;

    private Object result;


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

    public Object getResult() {
        return result;
    }

    public RpcResponse setResult(Object result) {
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
}
