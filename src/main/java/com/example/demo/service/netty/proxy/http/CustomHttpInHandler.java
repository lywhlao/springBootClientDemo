package com.example.demo.service.netty.proxy.http;

import com.google.common.base.Charsets;
import com.intuit.karate.StringUtils;
import com.intuit.karate.http.HttpUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;
import java.util.Map;

/**
 * @Author: laojiaqi
 * @Date: 2019/9/11 22:55
 * @Description:
 */
public class CustomHttpInHandler extends ChannelInboundHandlerAdapter {
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
////        if (msg instanceof LastHttpContent) {
////            ByteBuf content = Unpooled.copiedBuffer("Hello World.", CharsetUtil.UTF_8);
////            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
////            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
////            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, content.readableBytes());
////            ctx.write(response);
////            System.out.println("////"+msg);
////        }
//        if(msg instanceof FullHttpRequest){
//            System.out.println("==="+msg);
//            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
//            HttpMethod method = fullHttpRequest.method();
//            //1。针对get请求
//            if(method == HttpMethod.GET){
//                String uri = fullHttpRequest.uri();
//                QueryStringDecoder decoder = new QueryStringDecoder(uri);
//                Map<String, List<String>> parameters = decoder.parameters();
//                parameters.forEach((k,v)->{
//                    System.out.println(k+"==>"+v);
//                });
//                writeResponse(ctx,"method get response");
//            }
//            //2。针对post请求
//            if(method == HttpMethod.POST){
//                HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(fullHttpRequest);
//                ByteBuf content = fullHttpRequest.content();
//                String s = content.toString(Charsets.UTF_8);
////                writeResponse(ctx,"method post response");
//                ctx.fireChannelRead(((FullHttpRequest) msg).retain());
//            }
//        }else{
//            System.out.println(">>>:"+msg);
//        }
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("custom get msg==>"+msg);
//        ctx.fireChannelRead(msg);
        writeResponse(ctx,String.valueOf(msg));

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    private void writeResponse(ChannelHandlerContext ctx,String respMsg){
        ByteBuf contentBuf=Unpooled.copiedBuffer(respMsg, Charsets.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, contentBuf);
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
        response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, contentBuf.readableBytes());
//        ctx.writeAndFlush(response);
        ctx.fireChannelRead(response);
    }
}
