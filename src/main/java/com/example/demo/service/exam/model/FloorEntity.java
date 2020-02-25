package com.example.demo.service.exam.model;

import java.util.List;

/**
 * 楼层实体
 */
public class FloorEntity {

    /**
     * 楼层
     */
    public int floor;

    /**
     * 人员
     */
    List<PersonEntity> personEntityList;


    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public List<PersonEntity> getPersonEntityList() {
        return personEntityList;
    }

    public void setPersonEntityList(List<PersonEntity> personEntityList) {
        this.personEntityList = personEntityList;
    }
}
