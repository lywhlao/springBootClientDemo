package com.example.demo.service.netty.argus;

import com.example.demo.service.netty.argus.decode.ByteDecode;
import com.example.demo.service.netty.argus.handler.ByteInBoundHandler;
import com.example.demo.service.netty.argus.handler.ByteOutBoundHandler;
import com.example.demo.service.netty.proxy.http.CustomHttpInHandler;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.List;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-24 22:28
 * @Description:
 */
public class ConnectTest {

    final static byte[] REQ_SOCKS_5 = { 05, 01, 00 };

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ByteDecode());
                        ch.pipeline().addLast(new ByteInBoundHandler());
                        ch.pipeline().addLast(new ByteOutBoundHandler());

                    }
                });
        bootstrap.remoteAddress("localhost",1088);
//        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,1000);
//        bootstrap.option(ChannelOption.TCP_NODELAY,true);

//        List<String> ips= Lists.newArrayList("119.41.236.180:8010","124.205.11.245:8888","113.78.254.136:9000");
        List<String> ips= Lists.newArrayList("localhost:1088");
        ips.stream().forEach(item->{
            String[] items = item.split(":");
            Channel sync = null;
            try {
                sync = bootstrap.connect(items[0], Integer.parseInt(items[1])).sync().channel();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("===>write data");
                ChannelFuture channelFuture = sync.writeAndFlush(REQ_SOCKS_5);
                channelFuture.addListener(new GenericFutureListener<ChannelFuture>() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (!future.isSuccess()) {
                            System.err.print("write failed: ");
                            future.cause().printStackTrace(System.err);
                        }
                    }
                } );
                channelFuture.awaitUninterruptibly();
                System.out.println("===>write finish");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

//            sync.addListener(new ChannelFutureListener() {
//                @Override
//                public void operationComplete(ChannelFuture future) throws Exception {
//                    if(future.isSuccess()){
//                        System.out.println(Thread.currentThread().getName()+","+future.channel().remoteAddress()+" ==> connect success");
//
//                    }else{
//                        System.out.println( future.channel().remoteAddress()+" ==> connect fail");
//                    }
//                }
//            });
        });

        Thread.sleep(10000000L);

    }
}
