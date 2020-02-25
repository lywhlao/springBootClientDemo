package com.example.demo.service.netty.argus.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-25 17:57
 * @Description:
 */
public class ByteOutBoundHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
        System.out.println("out==>"+String.valueOf(msg));
    }
}
