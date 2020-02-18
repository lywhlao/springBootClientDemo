package com.example.demo;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-18 11:01
 * @Description:
 */
public class UrlEncodeTest {

    @Test
    public void test1() throws UnsupportedEncodingException {
        String a="+/";
        String encode = URLEncoder.encode(a, "utf-8");
        System.out.println(encode);
    }
}
