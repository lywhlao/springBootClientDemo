package com.example.demo.service.designPartner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-02 10:44
 * @Description:
 */
public class Strategy {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        Collections.sort(list,String::compareTo);
    }
}
