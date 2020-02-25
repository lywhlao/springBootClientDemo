package com.example.demo.service.exam.model.bo;

import com.example.demo.service.exam.model.enmu.LiftState;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-20 07:25
 * @Description:
 */
public class UseLiftContext {


    /**
     * 当前所在楼层
     */
    int currentFloor;

    /**
     * 乘客最大目标楼层
     */
    int maxTargetFloor;

    /**
     * 乘坐人数
     */
    int personNum;


    /**
     * 所需电梯方向状态
     */
    LiftState liftState;


    public LiftState getLiftState() {
        return liftState;
    }

    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getMaxTargetFloor() {
        return maxTargetFloor;
    }

    public void setMaxTargetFloor(int maxTargetFloor) {
        this.maxTargetFloor = maxTargetFloor;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }
}
