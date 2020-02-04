package com.example.demo.service.designPartner;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-02 10:58
 * @Description:
 */
public class Decorator {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedInputStream aa = new BufferedInputStream(new FileInputStream(new File("aa")));

    }
}
