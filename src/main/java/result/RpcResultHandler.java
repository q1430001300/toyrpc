package result;

import protocal.RpcResponse;

import java.util.concurrent.CountDownLatch;

public class RpcResultHandler {

    private CountDownLatch latch = new CountDownLatch(1);

    private RpcResponse response;


    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public RpcResponse getResponse() {
        return response;
    }

    public void setResponse(RpcResponse response) {
        this.response = response;
    }
}
