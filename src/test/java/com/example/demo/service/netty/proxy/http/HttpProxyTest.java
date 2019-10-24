package com.example.demo.service.netty.proxy.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: laojiaqi
 * @Date: 2019-09-24 22:47
 * @Description:
 */
public class HttpProxyTest {

    @Test
    public void testHttp() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(
                new HttpServerCodec(),
                new HttpObjectAggregator(512 * 1025),
                new CustomHttpInHandler());

        DefaultHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/");

        ByteBuf content = ((DefaultFullHttpRequest) request).content();
        boolean success = embeddedChannel.writeInbound(content);

        assert success;

//        Assert.assertTrue(embeddedChannel.writeInbound(request));
        boolean finish = embeddedChannel.finish();

        HttpResponse httpResponse = (HttpResponse) embeddedChannel.readInbound();


    }

}
