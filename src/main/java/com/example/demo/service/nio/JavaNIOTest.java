package com.example.demo.service.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-04 22:31
 * @Description:
 */
public class JavaNIOTest {
    public static void main(String[] args) throws IOException {
        //selector
        Selector selector = Selector.open();

        //channel
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_CONNECT);
        channel.connect(new InetSocketAddress("223.199.28.237",9999));

        //wait for events
        selector.select(2000);

        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();

        while (iterator.hasNext()){
            SelectionKey next = iterator.next();
            if(next.isConnectable()){
                System.out.println("connect able");
            }else{
                System.out.println("not connect able");
            }
        }
    }
}
