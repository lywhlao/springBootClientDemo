package com.example.demo.service.httpClient;

import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import javax.sound.midi.Soundbank;
import java.util.concurrent.TimeUnit;

/**
 * @Author: laojiaqi
 * @Date: 2019/8/14 22:37
 * @Description:
 */
public class HttpClientService {


   public static ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
        @Override
        public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
            HeaderElementIterator it = new BasicHeaderElementIterator
                    (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase
                        ("timeout")) {
                    return Long.parseLong(value) * 1000;
                }
            }
            return 600 * 1000;
        }
    };



    public static void main(String[] args) throws InterruptedException {
        HttpRoute route=new HttpRoute(new HttpHost("127.0.0.1",8088));
        SocketConfig socketConfig = SocketConfig.custom().
                setSoTimeout(60000).build();

        HttpGet get = new HttpGet("http://127.0.0.1:8088/ok");
        PoolingHttpClientConnectionManager connManager
                = new PoolingHttpClientConnectionManager();
        connManager.setDefaultMaxPerRoute(1);
        connManager.setMaxTotal(1);
        CloseableHttpClient client = HttpClients.custom()
                .setKeepAliveStrategy(myStrategy)
                .setConnectionManager(connManager)
                .setConnectionTimeToLive(80, TimeUnit.SECONDS)
                .build();


        //        RequestConfig requestConfig = RequestConfig.custom().
//                setSocketTimeout(20000).
//                setConnectTimeout(10000).
//                setConnectionRequestTimeout(10000).build();

        MultiHttpClientConnThread[] threads
                = new  MultiHttpClientConnThread[1];
        for(int i = 0; i < threads.length; i++){
            threads[i] = new MultiHttpClientConnThread(client, get);
        }
        for (MultiHttpClientConnThread thread: threads) {
            thread.start();
        }
        for (MultiHttpClientConnThread thread: threads) {
            thread.join(100000);
        }
    }



}
