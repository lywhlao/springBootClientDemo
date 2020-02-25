package com.example.demo.service.exam.service;

import com.example.demo.service.exam.model.BuildingEntity;
import com.example.demo.service.exam.model.FloorEntity;

import java.util.List;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-20 07:58
 * @Description:
 */
public class BuildService {


    public static boolean checkIsEmpty(BuildingEntity buildingEntity) {
        List<FloorEntity> floorEntityList = buildingEntity.getFloorEntities();
        if (floorEntityList == null || floorEntityList.isEmpty()) {
            return true;
        }
        return false;
    }
}
