package com.example.demo.service.netty.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author: laojiaqi
 * @Date: 2019/9/2 22:52
 * @Description:
 */
public class CustomDecode extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println(in.readableBytes());
        if (in.readableBytes() >= 4) {
            out.add(in.readInt());
        }
    }
}
