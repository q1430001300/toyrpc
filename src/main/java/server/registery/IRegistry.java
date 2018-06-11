package server.registery;

/**
 * 服务端注册接口
 */
public interface IRegistry {

    /**
     * 接口
     * @param ip
     * @param port
     */
    void connect(String ip, String port);

    /**
     * 注册
     */
    void register();

    /**
     *异常接口捕获
     */
    void exceptionCaught();
}
