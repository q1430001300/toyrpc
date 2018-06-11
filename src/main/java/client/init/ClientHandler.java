package client.init;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protocal.RpcResponse;
import client.result.RpcResultContext;
import server.init.ServerHandler;

public class ClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelRegistered();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.warn("接收到响应:{}",msg);
        if (msg instanceof RpcResponse) {
            RpcResponse response = (RpcResponse) msg;
            RpcResultContext.addRpcResultHandler(response.getRequestId(), response);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
