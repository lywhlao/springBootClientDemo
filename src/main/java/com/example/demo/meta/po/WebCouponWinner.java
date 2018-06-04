package com.example.demo.meta.po;

import lombok.Data;

/**
 * Created by hzlaojiaqi on 2016/12/21.
 */
@Data
public class WebCouponWinner {
   private Long id;

    private String uid;

    private Integer type;

    private String couponId;

    private Long dayBaseTime;

    private Long insertTime;
}
