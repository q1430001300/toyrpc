package client.consume;

/**
 * 客户端消费接口
 */
public interface IConsume {

    /**
     * 连接
     * @param ip
     * @param port
     */
    void connect(String ip, String port);

    /**
     * 消费接口
     */
    void Consume();

    /**
     * 异常捕获
     */
    void exceptionCaught();
}
