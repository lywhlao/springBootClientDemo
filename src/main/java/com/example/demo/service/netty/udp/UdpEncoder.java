package com.example.demo.service.netty.udp;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @Author: laojiaqi
 * @Date: 2019/8/11 15:32
 * @Description:
 */
public class UdpEncoder extends MessageToMessageEncoder<String> {

    private final InetSocketAddress remote;

    public UdpEncoder(InetSocketAddress remote) {
        this.remote = remote;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        byte[] value = msg.getBytes(Charsets.UTF_8);
        ByteBuf buffer = ctx.alloc().buffer(value.length);
        buffer.writeBytes(value);
        out.add(new DatagramPacket(buffer, remote));
        System.out.println("send msg:"+msg);
    }
}
