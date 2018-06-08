package proxy;

/**
 * 代理接口
 */
public interface IProxy {

    <T> T getClass(Class<T> tClass);
}
