package serialize.object;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import protocal.RpcRequest;
import serialize.RpcEncode;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.Buffer;

/**
 * @author: 潘明杰
 * @date: 2018/6/7 13:03
 * @description:
 */
public class ObjectEncode implements RpcEncode<RpcRequest> {

    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    /**
     * @param rpcRequest
     * @return
     */
    public ByteBuf encode(RpcRequest rpcRequest) {
        if (!(rpcRequest instanceof Serializable)) {
            throw new IllegalArgumentException("参数需要实现Serializable接口");
        }
        ByteBuf out = Unpooled.buffer();
        int startIdx = out.writerIndex();

        ByteBufOutputStream bout = new ByteBufOutputStream(out);
        try {
            bout.write(LENGTH_PLACEHOLDER);
            ObjectOutputStream oout = new CompactObjectOutputStream(bout);
            oout.writeObject(rpcRequest);
            oout.flush();
            oout.close();

            int endIdx = out.writerIndex();

            out.setInt(startIdx, endIdx - startIdx - 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;

    }
}
