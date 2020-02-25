package com.example.demo.service.exam.model;

import com.example.demo.service.exam.model.enmu.LiftState;

/**
 * 人物实体
 * @Author: laojiaqi
 * @Date: 2020-02-19 22:12
 * @Description:
 */
public class PersonEntity {

    /**
     * 人物id
     */
    int id;
    /**
     * 当前所在楼层
     */
    int currentFloor;

    /**
     * 目标楼层
     *
     */
    int targetFloor;


    /**
     * 所需要的电梯状态
     */
    LiftState state;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LiftState getState() {
        return state;
    }

    public void setState(LiftState state) {
        this.state = state;
    }

    public PersonEntity(int currentFloor, int targetFloor) {
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
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
}
