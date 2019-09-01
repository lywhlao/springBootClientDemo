package com.example.demo.service.httpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MultiHttpClientConnThread extends Thread {
    private CloseableHttpClient client;
    private HttpGet get;

    public MultiHttpClientConnThread(CloseableHttpClient client, HttpGet get) {
        this.client = client;
        this.get = get;
    }

    public MultiHttpClientConnThread(Runnable target, CloseableHttpClient client, HttpGet get) {
        super(target);
        this.client = client;
        this.get = get;
    }

    public MultiHttpClientConnThread(ThreadGroup group, Runnable target, CloseableHttpClient client, HttpGet get) {
        super(group, target);
        this.client = client;
        this.get = get;
    }

    public MultiHttpClientConnThread(String name, CloseableHttpClient client, HttpGet get) {
        super(name);
        this.client = client;
        this.get = get;
    }

    public MultiHttpClientConnThread(ThreadGroup group, String name, CloseableHttpClient client, HttpGet get) {
        super(group, name);
        this.client = client;
        this.get = get;
    }

    public MultiHttpClientConnThread(Runnable target, String name, CloseableHttpClient client, HttpGet get) {
        super(target, name);
        this.client = client;
        this.get = get;
    }

    public MultiHttpClientConnThread(ThreadGroup group, Runnable target, String name, CloseableHttpClient client, HttpGet get) {
        super(group, target, name);
        this.client = client;
        this.get = get;
    }

    public MultiHttpClientConnThread(ThreadGroup group, Runnable target, String name, long stackSize, CloseableHttpClient client, HttpGet get) {
        super(group, target, name, stackSize);
        this.client = client;
        this.get = get;
    }

    // standard constructors
    public void run(){
        try {
            for (int i = 0; i < 10000; i++) {
                HttpResponse response = client.execute(get);
                EntityUtils.consume(response.getEntity());
            }
        } catch (ClientProtocolException ex) {
        } catch (IOException ex) {
        }
    }
}
