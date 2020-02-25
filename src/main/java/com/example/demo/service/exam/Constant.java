package com.example.demo.service.exam;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-19 21:51
 * @Description:
 */
public class Constant {

    public static final int STOP = 1;

    public static final int UP = 1 << 1;

    public static final int DOWN = 1 << 2;


    public static final int MIN_FLOOR = 1;


    /**
     * 最大楼层数
     */
    public static final int MAX_FLOOR = 20;

    /**
     * 电梯总数
     */
    public static final int LIFT_NUM = 4;

    /**
     * 随机的人数目
     */
    public static final int RANDOM_PEOPLE_NUM = 5;

    /**
     * 电梯最大承载量
     */
    public static final int LIFT_MAX_CAPACITY = 11;

    /**
     * 电梯停下需要的时间
     */
    public static final int LIFT_STOP_COST_TIME = 1;

    /**
     * 电梯移动一层需要的时间
     */
    public static final int LIFT_MOVE_COST_TIME = 2;


    /**
     * 区分高低电梯类型
     */
    public static final int SEPARATOR_LIFT_FLOOR =10;


    /**
     * 使用电梯成功
     */
    public static final int USE_LIFT_SUCCESS = 1;

    /**
     * 使用电梯失败
     */
    public static final int USE_LIFT_FAIL = -1;
}
