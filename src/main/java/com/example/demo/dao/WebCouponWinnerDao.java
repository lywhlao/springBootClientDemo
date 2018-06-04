package com.example.demo.dao;


import com.example.demo.meta.po.WebCouponWinner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebCouponWinnerDao {

    int insert(@Param("pojo") WebCouponWinner pojo);

    int insertSelective(@Param("pojo") WebCouponWinner pojo);

    int insertList(@Param("pojos") List<WebCouponWinner> pojo);

    int update(@Param("pojo") WebCouponWinner pojo);

    int batchUpdate(@Param("pojos") List<WebCouponWinner> pojo);
}
