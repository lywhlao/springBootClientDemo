package com.example.demo.service.nio;

import org.apache.http.util.ByteArrayBuffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
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

        String path = JavaNIOTest.class.getClassLoader().getResource("").getPath();


        ByteBuffer allocate = ByteBuffer.allocate(123);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(345);

        byteBuffer.put((byte)1);
        boolean b = byteBuffer.hasArray();
        System.out.println(b);

        allocate.put((byte)2);
        boolean b1 = allocate.hasArray();
        System.out.println(b1);

        //selector
        Selector selector = Selector.open();

        //channel
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_CONNECT|SelectionKey.OP_WRITE);
        channel.connect(new InetSocketAddress("223.199.28.237",9999));

        //wait for events
        selector.select(2000);


        //get events
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

        ByteArrayBuffer byteArrayBuffer=new ByteArrayBuffer(10);

    }

}
