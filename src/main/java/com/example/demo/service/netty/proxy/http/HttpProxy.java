package com.example.demo.service.netty.proxy.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.net.InetSocketAddress;

/**
 * @Author: laojiaqi
 * @Date: 2019/9/11 21:28
 * @Description:
 */
public class HttpProxy {


    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        serverBootstrap.group(loopGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(8088))
                .childHandler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast(new HttpServerCodec());
                ch.pipeline().addLast(new HttpObjectAggregator(512*1024));
                ch.pipeline().addLast(new CustomHttpInHandler());
                ch.pipeline().addLast(new ProxyClientHandler());
            }
        });
        ChannelFuture bind = serverBootstrap.bind();
        bind.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("success");
                }else{
                    System.out.println("fail");
                    future.cause().printStackTrace();
                }
            }
        });

    }
}
