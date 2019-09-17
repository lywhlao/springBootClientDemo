package com.example.demo.service.netty.proxy.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Author: laojiaqi
 * @Date: 2019/9/13 07:49
 * @Description:
 */
public class ProxyClientHandler extends SimpleChannelInboundHandler {

    String realAddress="127.0.0.1";
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof FullHttpRequest){
            FullHttpRequest fullHttpRequest=(FullHttpRequest) ((FullHttpRequest) msg).retain();
            String uri = fullHttpRequest.uri();
            Channel httpInBound = ctx.channel();
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.group(ctx.channel().eventLoop())//减少线程间切换
                    .channel(NioSocketChannel.class)
//                    .remoteAddress(realAddress,8090)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new SimpleChannelInboundHandler<Object>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    httpInBound.writeAndFlush(msg);
                                }
                            });
                        }
                    });
            ChannelFuture sync = bootstrap.connect(realAddress,8090);
            sync.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()) {
                        future.channel().writeAndFlush(fullHttpRequest);
                        System.out.println("client connect success");
                    }else{
                        System.out.println("client connect fail");
                        future.cause().printStackTrace();
                    }
                }
            });
        }
//        System.out.println("=======proxy========");
//        System.out.println(msg);
//        ByteBuf buffer = ctx.alloc().buffer();
//        buffer.duplicate();
//        ctx.channel().flush();
//        ctx.fireChannelRead(msg);
    }
}
