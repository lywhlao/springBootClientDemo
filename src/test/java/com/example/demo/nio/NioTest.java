package com.example.demo.nio;

import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-07 22:37
 * @Description:
 */
public class NioTest {

    @Test
    public void test() throws IOException, InterruptedException {

        Selector selector = Selector.open();

//        List<String> items = Lists.newArrayList("39.137.95.73", "39.137.95.72",
//                                                "36.25.243.51","59.56.28.199","39.137.69.10","39.137.107.98");

        List<String> items = Lists.newArrayList("36.25.243.51");

        for (int i = 0; i < items.size(); i++) {
            SocketChannel channel=SocketChannel.open();
            channel.configureBlocking(false);
            channel.socket().setTcpNoDelay(true);
            String ipAddress = items.get(i);
            channel.register(selector, SelectionKey.OP_CONNECT|SelectionKey.OP_WRITE|SelectionKey.OP_READ,ipAddress);
            channel.connect(new InetSocketAddress(items.get(i),80));
        }

        while (true) {
            System.out.println("====next select======");
            selector.select(2000);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                int i = selectionKey.readyOps();
                //connect
                if (selectionKey.isConnectable()) {
                    System.out.println(selectionKey.attachment() + "--> Connected!");

                    int ops = selectionKey.interestOps();
                    ops&=~SelectionKey.OP_CONNECT;
                    ops|=SelectionKey.OP_WRITE;
                    ops|=SelectionKey.OP_READ;
                    selectionKey.interestOps(ops);


                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    boolean status = channel.finishConnect();
                    System.out.println("finish connect==>"+status);


                    channel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE,selectionKey.attachment());
//
//                    ByteBuffer allocate = ByteBuffer.allocate(10);
//                    allocate.put((byte)1);
//                    allocate.flip();
//                    channel.write(allocate);
                }
                //write
                if(selectionKey.isWritable()){
                    System.out.println(selectionKey.attachment() + "--> Writable!");
                    ByteBuffer allocate = ByteBuffer.allocate(10);
                    allocate.put((byte)1);
                    allocate.flip();
                    ((SocketChannel)selectionKey.channel()).write(allocate);
                }
                //read
                if(selectionKey.isReadable()){
                    ByteBuf buffer = Unpooled.buffer();


                    System.out.println(selectionKey.attachment() + "--> Readable!");

                }
                iterator.remove();
                System.out.println("================");
            }
            Thread.sleep(2000L);
        }
    }
}
