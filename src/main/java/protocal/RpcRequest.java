package protocal;

import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

public class RpcRequest<T> implements Serializable {


    private String requestId = UUID.randomUUID().toString();

    private Class<T> invokeClass;

    private String methodName;

    private Object[] objects;

    public String getRequestId() {
        return requestId;
    }


    public Class<T> getInvokeClass() {
        return invokeClass;
    }

    public void setInvokeClass(Class<T> invokeClass) {
        this.invokeClass = invokeClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "requestId='" + requestId + '\'' +
                ", invokeClass=" + invokeClass +
                ", methodName='" + methodName + '\'' +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}
