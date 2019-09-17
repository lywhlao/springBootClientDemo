package com.example.demo;

import com.example.demo.service.netty.code.CustomDecode;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.channels.Channel;

/**
 * @Author: laojiaqi
 * @Date: 2019/8/7 10:55
 * @Description:
 */
public class NettyTest {


    @Test
    public void embededTest(){
        EmbeddedChannel channel=new EmbeddedChannel();
        ByteBuf bf= Unpooled.buffer();
        Integer value=new Integer(1);
        byte[] bytes = value.toString().getBytes(Charsets.UTF_8);
        channel.pipeline().addFirst(new CustomDecode());
        channel.writeInbound(bf);
        channel.finish();

        Object o = channel.readInbound();
        System.out.println(o);
    }

}
