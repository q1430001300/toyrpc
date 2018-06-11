package server.init;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protocal.RpcRequest;
import protocal.RpcResponse;
import server.resolver.ResponseResolver;

public class ServerHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof RpcRequest) {
            RpcRequest rpcRequest = (RpcRequest) msg;
            RpcResponse rpcResponse = new ResponseResolver(rpcRequest).resloveResponse();
            logger.warn("响应消息:{}",rpcResponse);
            ctx.channel().writeAndFlush(rpcResponse);
        }

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
