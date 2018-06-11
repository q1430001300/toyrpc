package client.init;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import protocal.RpcResponse;
import client.result.RpcResultContext;

public class ClientHandler extends ChannelHandlerAdapter {




    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelRegistered();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof RpcResponse) {
            RpcResponse response = (RpcResponse) msg;
            RpcResultContext.addRpcResultHandler(response.getRequestId(), response);
        }
    }
}
