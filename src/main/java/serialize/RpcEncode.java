package serialize;

import io.netty.buffer.ByteBuf;

/**
 * @author: 潘明杰
 * @date: 2018/6/7 13:01
 * @description:
 */
public interface RpcEncode<T> {


    /**
     * 自定义编码
     * @param t
     * @return
     */
    ByteBuf encode(T t);
}
