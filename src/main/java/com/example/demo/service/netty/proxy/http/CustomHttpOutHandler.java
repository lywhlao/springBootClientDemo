package com.example.demo.service.netty.proxy.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Author: laojiaqi
 * @Date: 2019/9/11 23:06
 * @Description:
 */
public class CustomHttpOutHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
          ctx.channel().writeAndFlush("hello!!!");
    }
}
