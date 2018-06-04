package com.example.demo.service;

import com.example.demo.dao.WebCouponWinnerDao;
import com.example.demo.meta.po.WebCouponWinner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CouponService {

    @Autowired
    WebCouponWinnerDao dao;



    @Transactional
    public void addWinner(){
        WebCouponWinner webCouponWinner = new WebCouponWinner();
        webCouponWinner.setUid("hello"+2);
        dao.insert(webCouponWinner);
//        throw new RuntimeException("error");
    }


}
