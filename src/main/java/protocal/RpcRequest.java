package protocal;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

/**
 * 请求
 *
 * @param <T>
 */
public class RpcRequest<T> implements Serializable {

    /**
     * uuid
     */
    private String requestId = UUID.randomUUID().toString();

    /**
     * 调用的类
     */
    private Class<T> declaringClass;

    /**
     * 方法
     */
    private Method method;

    /**
     * 参数
     */
    private Object[] objects;

    /**
     * 参数类型
     */
    private Class[] parameterTypes;

    public String getRequestId() {
        return requestId;
    }


    public Class<T> getDeclaringClass() {
        return declaringClass;
    }

    public RpcRequest<T> setDeclaringClass(Class<T> declaringClass) {
        this.declaringClass = declaringClass;
        return this;
    }

    public Method getMethod() {
        return method;
    }

    public RpcRequest<T> setMethod(Method method) {
        this.method = method;
        return this;
    }

    public Object[] getObjects() {
        return objects;
    }

    public RpcRequest<T> setObjects(Object[] objects) {
        this.objects = objects;
        return this;
    }

    public RpcRequest<T> setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public RpcRequest<T> setParameterTypes(Class[] parameterTypes) {
        this.parameterTypes = parameterTypes;
        return this;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "requestId='" + requestId + '\'' +
                ", declaringClass=" + declaringClass +
                ", method=" + method +
                ", objects=" + Arrays.toString(objects) +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                '}';
    }
}
