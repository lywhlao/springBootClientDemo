package com.example.demo.service.nio;

import org.apache.commons.codec.binary.Hex;
import org.apache.tomcat.util.buf.HexUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-25 16:48
 * @Description:
 */
public class Socks5Test {

    final static byte[] REQ_SOCKS_5 = {05, 01, 00};


    public static void main(String[] args) throws IOException, InterruptedException {

        ByteBuffer allocate = ByteBuffer.allocate(1024);

        //selector
        Selector selector = Selector.open();

        //channel
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE | SelectionKey.OP_READ);
        channel.connect(new InetSocketAddress("127.0.0.1", 1088));


        while (true) {
            //wait for events
            selector.select(2000);

            //get events
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            if (selectionKeys.size() == 0) {
                System.out.println("no event");
                continue;
            }
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                if (next.isConnectable()) {
                    boolean success = ((SocketChannel) next.channel()).finishConnect();
                    if(success){
                        System.out.println("connect finish success");
                    }else{
                        System.out.println("connect finish fail");
                    }
                } else if (next.isWritable()) {
                    System.out.println("===>write");
                    ((SocketChannel) next.channel()).write(ByteBuffer.wrap(REQ_SOCKS_5));
                    //只关注read事件
                    next.interestOps(SelectionKey.OP_READ);
                } else if (next.isReadable()) {
                    System.out.println("===>read");
                    int read = ((SocketChannel) next.channel()).read(allocate);
                    if (read > 0) {
                        allocate.flip();
                        while (allocate.hasRemaining()){
                            System.out.println(allocate.get());
                        }
                    }
                }
                iterator.remove();
            }
            Thread.sleep(5000L);
        }
    }
}
