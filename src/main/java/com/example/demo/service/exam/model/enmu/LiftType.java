package com.example.demo.service.exam.model.enmu;

import com.example.demo.service.exam.Constant;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-20 06:36
 * @Description:
 */
@Deprecated
public enum LiftType {


    /**
     * 高层
     */
    HIGH,

    /**
     * 底层
     */
    LOW;


    public static LiftType getLiftTypeByFloor(int floor) {
        if (floor > Constant.SEPARATOR_LIFT_FLOOR) {
            return HIGH;
        }
        return LOW;
    }
}
