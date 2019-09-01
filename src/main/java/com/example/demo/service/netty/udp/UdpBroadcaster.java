package com.example.demo.service.netty.udp;

import com.example.demo.service.netty.echo.EchoServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * @Author: laojiaqi
 * @Date: 2019/8/7 21:58
 * @Description:
 */
public class UdpBroadcaster {

    private final int port;

    public UdpBroadcaster(int port) { this.port = port; }


    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new UdpEncoder(address));
                        }
                    });
            Channel channel = b.bind(9999).sync().channel();
            while (true){
                Scanner scanner=new Scanner(System.in);
                while ((scanner.hasNext())){
                    String next = scanner.next();
                    if(next.equalsIgnoreCase("exit")){
                        System.out.println("exit now");
                        break;
                    }
                    System.out.println("input:"+next);
                    channel.writeAndFlush(next);
                }
            }


        } finally {
            group.shutdownGracefully().sync();
        }

    }
}
