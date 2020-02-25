package com.example.demo.service.exam;

import com.alibaba.fastjson.JSON;
import com.example.demo.service.exam.model.BuildingEntity;
import com.example.demo.service.exam.model.FloorEntity;
import com.example.demo.service.exam.model.LiftEntity;
import com.example.demo.service.exam.model.PersonEntity;
import com.example.demo.service.exam.model.enmu.LiftState;
import com.example.demo.service.exam.service.BuildService;
import com.example.demo.service.exam.service.InitService;
import com.example.demo.service.exam.service.LiftService;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-19 21:26
 * @Description:
 */
public class LiftTest {


    /**
     * @param args
     */
    public static void main(String[] args) {
        //初始化房屋状态
        BuildingEntity building = InitService.initBuildState();

        //初始化电梯状态
        List<LiftEntity> liftEntities = InitService.initLiftState();

        //初始化按电梯按钮顺序，假设楼层中List<PersonEntity>首个元素为该楼层最先按按钮
        Collections.shuffle(building.getFloorEntities());

        List<FloorEntity> floorEntities = building.getFloorEntities();

        Iterator<FloorEntity> iterator = floorEntities.iterator();

        while (true) {
            if (BuildService.checkIsEmpty(building)) {
                System.out.println("已运送完成");
                break;
            }
            List<FloorEntity> floors = building.getFloorEntities();

            for (int i = 1; i < floors.size(); i++) {
                FloorEntity currentFloor = floors.get(i);
                List<PersonEntity> personEntityList = currentFloor.getPersonEntityList();
                //若楼层中没有人，则考虑下一个楼层
                if (personEntityList == null || personEntityList.isEmpty()) {
                    continue;
                }
                int liftIndex = LiftService.chooseLift(currentFloor, liftEntities);
                LiftEntity liftEntity = liftEntities.get(liftIndex);

                do {
                    FloorEntity targetFloor = currentFloor;
                    LiftService.moveToFloor(liftEntity, targetFloor);
                    LiftService.stayInFloor(liftEntity, targetFloor);
                    targetFloor = LiftService.updateCurrentTargetFloorEntity(liftEntity, floorEntities);
                    if (targetFloor == null) {
                        break;
                    }
                } while (liftEntity.getState() != LiftState.STOP);
            }
            System.out.println(String.format("%s", JSON.toJSONString(floors)));
        }
    }


}
