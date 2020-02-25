package com.example.demo.service.exam.model;

import java.util.List;

/**
 * 建筑实体
 * @Author: laojiaqi
 * @Date: 2020-02-19 21:50
 * @Description:
 */
public class BuildingEntity {


    /**
     *  包含的楼层
     */
    List<FloorEntity> floorEntities;


    public List<FloorEntity> getFloorEntities() {
        return floorEntities;
    }

    public void setFloorEntities(List<FloorEntity> floorEntities) {
        this.floorEntities = floorEntities;
    }
}
