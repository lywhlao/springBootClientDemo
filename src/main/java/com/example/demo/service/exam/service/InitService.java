package com.example.demo.service.exam.service;

import com.example.demo.service.exam.Constant;
import com.example.demo.service.exam.model.BuildingEntity;
import com.example.demo.service.exam.model.FloorEntity;
import com.example.demo.service.exam.model.LiftEntity;
import com.example.demo.service.exam.model.PersonEntity;
import com.example.demo.service.exam.model.enmu.LiftState;
import com.example.demo.service.exam.model.enmu.LiftType;
import com.example.demo.service.exam.util.RandomUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.demo.service.exam.Constant.RANDOM_PEOPLE_NUM;

/**
 * 状态初始化类
 *
 * @Author: laojiaqi
 * @Date: 2020-02-20 06:47
 * @Description:
 */
public class InitService {


    public static AtomicInteger PERSON_ID = new AtomicInteger(1);

    /**
     * 初始化楼层状态
     *
     * @return
     */
    public static BuildingEntity initBuildState() {
        BuildingEntity buildingEntity = new BuildingEntity();
        List<FloorEntity> floorEntities = new ArrayList<>();
        for (int i = 0; i <= Constant.MAX_FLOOR; i++) {
            if (i == 0) {
                floorEntities.add(new FloorEntity());
                continue;
            }
            FloorEntity entity = new FloorEntity();
            entity.setFloor(getRandomFloor());
            entity.setPersonEntityList(getRandomPersonList());
            floorEntities.add(entity);
        }
        buildingEntity.setFloorEntities(floorEntities);
        return buildingEntity;
    }

    /**
     * 初始化电梯状态
     *
     * @return
     */
    public static List<LiftEntity> initLiftState() {
        List<LiftEntity> liftEntities = new LinkedList<>();
        for (int i = 1; i <= Constant.LIFT_NUM; i++) {
            LiftEntity liftEntity = new LiftEntity();
            liftEntity.setState(LiftState.STOP);
            liftEntity.setAvailableCapacity(Constant.LIFT_MAX_CAPACITY);
            if (i % 2 == 0) {
                liftEntity.setLiftType(LiftType.HIGH);
                liftEntity.setCurrentFloor(RandomUtil.nextInt(Constant.SEPARATOR_LIFT_FLOOR + 1, Constant.MAX_FLOOR + 1));
            } else {
                liftEntity.setLiftType(LiftType.LOW);
                liftEntity.setCurrentFloor(RandomUtil.nextInt(1, Constant.SEPARATOR_LIFT_FLOOR + 1));
            }
            liftEntity.setLiftTag(i);
            liftEntities.add(liftEntity);
        }
        return liftEntities;
    }


    /**
     * @return
     */
    private static int getRandomFloor() {
        return RandomUtil.nextInt(1, Constant.MAX_FLOOR + 1);
    }

    /**
     * 获取随机乘客数据
     *
     * @return
     */
    private static List<PersonEntity> getRandomPersonList() {
        List<PersonEntity> personEntities = new LinkedList<>();
        int i = RandomUtil.nextInt(1, RANDOM_PEOPLE_NUM);
        //todo 简化 每层人乘坐的方向一致,所到楼层一致
        LiftState state = null;
        if (i % 2 == 0) {
            state = LiftState.DOWN;
        } else {
            state = LiftState.UP;
        }
        int current = getRandomFloor();
        int target = current;
        if (state == LiftState.UP) {
            target = RandomUtil.nextInt(current, Constant.MAX_FLOOR + 1);
        }else{
            target = RandomUtil.nextInt(1, current+1);
        }
        while (i-- > 0) {
            PersonEntity entity = new PersonEntity(current, target);
            entity.setId(PERSON_ID.getAndIncrement());
            personEntities.add(entity);
        }
        return personEntities;
    }
}
