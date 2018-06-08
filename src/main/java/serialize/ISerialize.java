package serialize;

/**
 * 序列化接口
 */
public interface ISerialize<T> {


    /**
     * 编码
     * @param t
     * @return
     */
    byte[] encode(T t);


    /**
     * 解码
     * @param bytes
     * @param clas
     * @return
     */
    T deCode(byte[] bytes, Class<T> clas);
}
