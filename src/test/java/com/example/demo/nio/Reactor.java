package com.example.demo.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-11 22:57
 * @Description:
 */
public class Reactor {


    @Test
    public void test() throws IOException {

        Selector open = Selector.open();

    }
}
