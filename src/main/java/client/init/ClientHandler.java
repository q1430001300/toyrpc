package client.init;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import protocal.RpcResponse;
import client.result.RpcResultContext;

public class ClientHandler extends ChannelHandlerAdapter {


//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        RpcRequest request = new RpcRequest();
//        ctx.writeAndFlush(request);
//    }


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

//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        if (evt instanceof IdleStateEvent) {
//            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
//            System.out.println(123);
//        }
//    }
}
