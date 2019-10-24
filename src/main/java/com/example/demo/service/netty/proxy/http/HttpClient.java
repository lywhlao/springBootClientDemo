package com.example.demo.service.netty.proxy.http;

import com.google.common.base.Charsets;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * netty 实现client http 请求
 * @Author: laojiaqi
 * @Date: 2019/9/17 08:00
 * @Description:
 */
public class HttpClient {



    static final String URL = System.getProperty("url", "http://127.0.0.1:8090/ok.htm");

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        URI uri=new URI(URL);
        String host = uri.getHost();
        int port = uri.getPort();
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new HttpClientCodec());
                        ch.pipeline().addLast(new HttpObjectAggregator(512*1024));
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<Object>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                                if(msg instanceof FullHttpResponse){
                                    FullHttpResponse fullHttpResponse = (FullHttpResponse) msg;
                                    ByteBuf content = fullHttpResponse.content();
                                    String s = content.toString(Charsets.UTF_8);
                                    System.out.println("revice==>"+s);
                                }
                            }
                        });
                    }
                });
        ChannelFuture sync = bootstrap.connect(host,port);
        sync.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()) {
                    DefaultHttpRequest request = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,uri.getRawPath());
                    request.headers().set(HttpHeaderNames.HOST, host);
                    request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
                    request.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);
                    future.channel().writeAndFlush(request);
                    System.out.println("client connect success");
                }else{
                    System.out.println("client connect fail");
                    future.cause().printStackTrace();
                }
            }
        });
        sync.channel().closeFuture().sync();
    }
}
