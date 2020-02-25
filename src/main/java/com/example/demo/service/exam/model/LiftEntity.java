package com.example.demo.service.exam.model;

import com.example.demo.service.exam.Constant;
import com.example.demo.service.exam.model.enmu.LiftState;
import com.example.demo.service.exam.model.enmu.LiftType;

import java.util.ArrayList;
import java.util.List;

/**
 * 电梯实体
 */
public class LiftEntity {
    /**
     * 运行状态
     */
    public LiftState state;
    /**
     * 所在楼层
     */
    public int currentFloor;


    /**
     * 目标楼层
     */
    public int targetFloor;

    /**
     * 可用承载人数
     */
    public int availableCapacity;


    /**
     * 从开始到结束期望花费的时间
     */
    public int expectCostTime;


    /**
     * 运行的类型
     */
    public LiftState liftState;


    /**
     * 电梯类型
     */
    public LiftType liftType;


    /**
     * 电梯标识
     */
    public int liftTag;


    /**
     * 所在电梯的人数
     */
    public List<PersonEntity> inLiftPerson=new ArrayList<>();


    /**
     * 更新可用人数
     */
    public void updateAvailableCapacity() {
        if (inLiftPerson == null) {
            this.availableCapacity = Constant.LIFT_MAX_CAPACITY;
        } else {
            this.availableCapacity = Constant.LIFT_MAX_CAPACITY - inLiftPerson.size();
        }
    }


    public List<PersonEntity> getInLiftPerson() {
        return inLiftPerson;
    }

    public void setInLiftPerson(List<PersonEntity> inLiftPerson) {
        this.inLiftPerson = inLiftPerson;
    }

    public int getLiftTag() {
        return liftTag;
    }

    public void setLiftTag(int liftTag) {
        this.liftTag = liftTag;
    }

    public LiftType getLiftType() {
        return liftType;
    }

    public void setLiftType(LiftType liftType) {
        this.liftType = liftType;
    }

    public LiftState getLiftState() {
        return liftState;
    }

    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
    }

    public int getExpectCostTime() {
        return expectCostTime;
    }

    public void setExpectCostTime(int expectCostTime) {
        this.expectCostTime = expectCostTime;
    }

    public LiftState getState() {
        return state;
    }

    public void setState(LiftState state) {
        this.state = state;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }
}
