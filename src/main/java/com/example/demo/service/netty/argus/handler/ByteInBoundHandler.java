package com.example.demo.service.netty.argus.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-24 22:45
 * @Description:
 */
public class ByteInBoundHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        System.out.println(msg);
    }
}
