package protocal;

import java.io.Serializable;

/**
 * 响应
 */
public class RpcResponse implements Serializable {


    private String requestId;

    private RessultCode ressultCode;

    private String msg;

    private Object result;


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public RessultCode getRessultCode() {
        return ressultCode;
    }

    public void setRessultCode(RessultCode ressultCode) {
        this.ressultCode = ressultCode;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
