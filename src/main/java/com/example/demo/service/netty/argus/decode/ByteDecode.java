package com.example.demo.service.netty.argus.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author: laojiaqi
 * @Date: 2019/9/2 22:52
 * @Description:
 */
public class ByteDecode extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        out.add(in.readByte());
        System.out.println("in.readByte");
    }
}
