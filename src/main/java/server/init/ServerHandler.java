package server.init;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import protocal.ResultCode;
import protocal.RpcRequest;
import protocal.RpcResponse;
import server.resolver.ResponseResolver;

public class ServerHandler extends ChannelHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof RpcRequest) {
            RpcRequest rpcRequest = (RpcRequest) msg;
            RpcResponse rpcResponse = new ResponseResolver(rpcRequest).resloveResponse();
            ctx.writeAndFlush(rpcResponse);
        }
        ctx.fireChannelRead(msg);

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
