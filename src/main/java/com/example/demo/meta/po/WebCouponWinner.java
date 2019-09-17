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

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getUid() {
  return uid;
 }

 public void setUid(String uid) {
  this.uid = uid;
 }

 public Integer getType() {
  return type;
 }

 public void setType(Integer type) {
  this.type = type;
 }

 public String getCouponId() {
  return couponId;
 }

 public void setCouponId(String couponId) {
  this.couponId = couponId;
 }

 public Long getDayBaseTime() {
  return dayBaseTime;
 }

 public void setDayBaseTime(Long dayBaseTime) {
  this.dayBaseTime = dayBaseTime;
 }

 public Long getInsertTime() {
  return insertTime;
 }

 public void setInsertTime(Long insertTime) {
  this.insertTime = insertTime;
 }
}
