package com.example.demo.service.exam.service;

import com.example.demo.service.exam.Constant;
import com.example.demo.service.exam.model.FloorEntity;
import com.example.demo.service.exam.model.LiftEntity;
import com.example.demo.service.exam.model.PersonEntity;
import com.example.demo.service.exam.model.bo.UseLiftContext;
import com.example.demo.service.exam.model.enmu.LiftState;
import com.example.demo.service.exam.model.enmu.LiftType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 电梯调度Service
 * <p>
 * 电梯分为两种类型 HIGH,服务楼层(10,20]）；LOW,服务楼层[1,10]，每种类型有2台。
 * 跨类型调度：需要首先返回第一层，例如从5到15层，需要到1层乘坐HIGH类型的电梯。
 * 同类型调度：无需返回第一层，按需分配
 *
 * @Author: laojiaqi
 * @Date: 2020-02-20 06:35
 * @Description:
 */
public class LiftService {


    /**
     * 选择空闲或者顺路的电梯
     *
     * @param entity
     * @param liftEntities
     * @return 返回电梯索引
     */
    public static int chooseLift(FloorEntity entity, List<LiftEntity> liftEntities) {
        List<PersonEntity> personEntityList = entity.getPersonEntityList();
        if (personEntityList == null || personEntityList.isEmpty()) {
            System.out.println(String.format("%s楼没有人", entity.getFloor()));
            return -1;
        }
        PersonEntity personEntity = personEntityList.get(0);
        int userLiftIndex = -1;
        int minGapFloor = -1;
        for (int i = 0; i < liftEntities.size(); i++) {
            LiftEntity temp = liftEntities.get(i);
            //若电梯是运行状态
            if (temp.getState() != LiftState.STOP) {
                if (temp.getState() == personEntity.getState()) {
                    if (inSameWay(temp, personEntity)) {
                        return i;
                    }
                }
                continue;
            }

            //若电梯是停止状态
            int gapFloor = (temp.getCurrentFloor() - entity.getFloor());
            if (minGapFloor == -1) {
                minGapFloor = gapFloor;
                userLiftIndex = i;
                continue;
            }
            if (gapFloor < minGapFloor) {
                minGapFloor = gapFloor;
                userLiftIndex = i;
            }
        }
        return userLiftIndex;

    }


    /**
     * 使用电梯,返回电梯的标识
     *
     * @param entity
     * @param liftEntities
     * @return
     */
    public static int useLift(FloorEntity entity, List<LiftEntity> liftEntities) {
        List<PersonEntity> personEntityList = entity.getPersonEntityList();
        if (personEntityList == null || personEntityList.isEmpty()) {
            System.out.println(String.format("%s楼没有人", entity.getFloor()));
            return -1;
        }

        //这里做了简化，第一个人的状态就是所有人的状态
        PersonEntity personEntity = personEntityList.get(0);
        LiftState wantLiftState = personEntity.getState();

        UseLiftContext liftContext = generateUseLiftContext(wantLiftState, personEntityList);
        liftContext.setCurrentFloor(entity.getFloor());


        //高层只能做高层的电梯
        LiftType liftTypeByFloor = LiftType.getLiftTypeByFloor(liftContext.getCurrentFloor());
        List<LiftEntity> canUseLifts = liftEntities.stream()
                .filter(item -> item.getLiftType() == liftTypeByFloor)
                .collect(Collectors.toList());


        int useLiftTag = -1;
        int minGapFloor = -1;
        for (int i = 0; i < canUseLifts.size(); i++) {
            LiftEntity temp = canUseLifts.get(i);
            //若电梯是运行状态
            if (temp.getState() != LiftState.STOP) {
                if (temp.getState() == personEntity.getState()) {
                    if (inSameWay(temp, personEntity)) {
//                        int availableCapacity = temp.getAvailableCapacity();
//                        int remain = 0;
//                        int minusFloorPersonNum = availableCapacity >= liftContext.getPersonNum() ? liftContext.getPersonNum() : availableCapacity;
//                        minusFloorPersonByState(minusFloorPersonNum, liftContext, personEntityList);
//                        addPersonToLift(minusFloorPersonNum, temp);
                        stayInFloor(temp, entity);

                        return Constant.USE_LIFT_SUCCESS;
                    }
                }
                continue;
            }

            //若电梯是停止状态
            int gapFloor = (temp.getCurrentFloor() - liftContext.getCurrentFloor());
            if (minGapFloor == -1) {
                minGapFloor = gapFloor;
                useLiftTag = temp.getLiftTag();
                continue;
            }
            if (gapFloor < minGapFloor) {
                minGapFloor = gapFloor;
                useLiftTag = temp.getLiftTag();
            }
        }
        return useLiftTag;
    }


    public static FloorEntity updateCurrentTargetFloorEntity(LiftEntity liftEntity, List<FloorEntity> floorEntityList) {
        List<PersonEntity> personEntityList = liftEntity.getInLiftPerson();
        if (personEntityList != null) {
            PersonEntity max = personEntityList.stream().max(Comparator.comparing(PersonEntity::getTargetFloor)).orElse(null);
            if (max != null) {
                int targetFloor = max.getTargetFloor();
                return floorEntityList.get(targetFloor);
            }
        }
        return null;
    }


    public static FloorEntity getTargetFloorByFloorEntity(FloorEntity sourceFloorEntity, List<FloorEntity> floorEntityList) {
        List<PersonEntity> personEntities = sourceFloorEntity.getPersonEntityList();
        int maxFloor = -1;
        for (PersonEntity temp : personEntities) {
            if (temp.getTargetFloor() > maxFloor) {
                maxFloor = temp.getTargetFloor();
            }
        }
        return floorEntityList.get(maxFloor);
    }


    /**
     * 移动到某个楼层
     *
     * @param liftEntity
     * @param targetFloorEntity
     */
    public static void moveToFloor(LiftEntity liftEntity, FloorEntity targetFloorEntity) {
        //todo 需要检查中间楼层状态
        if (liftEntity.getCurrentFloor() > targetFloorEntity.getFloor()) {
            liftEntity.setLiftState(LiftState.DOWN);
            return;
        }
        if (liftEntity.getCurrentFloor() == targetFloorEntity.getFloor()) {
            liftEntity.setLiftState(LiftState.STOP);
            return;
        }

        if (liftEntity.getCurrentFloor() < targetFloorEntity.getFloor()) {
            liftEntity.setLiftState(LiftState.UP);
            return;
        }
    }

    /**
     * 停止在某个楼层
     *
     * @param liftEntity
     * @param floorEntity
     */
    public static void stayInFloor(LiftEntity liftEntity, FloorEntity floorEntity) {
        liftEntity.setLiftState(LiftState.STOP);
        comeOutLift(liftEntity, floorEntity.getFloor());
        comeInLift(liftEntity, floorEntity);
    }


    /**
     * 进入电梯
     *
     * @param liftEntity
     * @param floorEntity
     */
    private static void comeInLift(LiftEntity liftEntity, FloorEntity floorEntity) {
        List<PersonEntity> personEntities = floorEntity.getPersonEntityList();
        if (personEntities == null) {
            return;
        }
        int availableCapacity = liftEntity.getAvailableCapacity();
        if (availableCapacity <= personEntities.size()) {
            List<PersonEntity> subList = personEntities.subList(0, availableCapacity);
            liftEntity.getInLiftPerson().addAll(subList);
            floorEntity.setPersonEntityList(personEntities.subList(availableCapacity,personEntities.size()));
        } else {
            liftEntity.getInLiftPerson().addAll(personEntities);
            floorEntity.setPersonEntityList(null);
        }
        liftEntity.updateAvailableCapacity();
    }

    /**
     * 出去电梯
     *
     * @param liftEntity
     * @param floor
     */
    private static void comeOutLift(LiftEntity liftEntity, int floor) {
        List<PersonEntity> inLiftPerson = liftEntity.getInLiftPerson();
        if (inLiftPerson != null) {
            Iterator<PersonEntity> iterator = inLiftPerson.iterator();
            while (iterator.hasNext()) {
                PersonEntity next = iterator.next();
                if (next.getTargetFloor() == floor) {
                    iterator.remove();
                }
            }
            //更新可用人数
            liftEntity.updateAvailableCapacity();
        }
    }


//    /**
//     * 电梯内进人
//     *
//     * @param num
//     * @param liftEntity
//     */
//    private static void addPersonToLift(int num, LiftEntity liftEntity) {
//        int availableCapacity = liftEntity.getAvailableCapacity();
//        if (availableCapacity < num) {
//            throw new RuntimeException("add num is larger then availableCapacity");
//        }
//        liftEntity.setAvailableCapacity(availableCapacity - num);
//    }
//
//
//    /**
//     * 楼层人进入电梯
//     *
//     * @param num
//     * @param useLiftContext
//     * @param personEntityList
//     */
//    private static void minusFloorPersonByState(int num, UseLiftContext useLiftContext, List<PersonEntity> personEntityList) {
//        Iterator<PersonEntity> iterator = personEntityList.iterator();
//        while (iterator.hasNext() && num > 0) {
//            PersonEntity next = iterator.next();
//            if (next.getState() == useLiftContext.getLiftState()) {
//                iterator.remove();
//                num--;
//            }
//        }
//    }


    /**
     * 与乘客同方向
     *
     * @param entity
     * @param personEntity
     * @return
     */
    private static boolean inSameWay(LiftEntity entity, PersonEntity personEntity) {
        if (entity.getState() == personEntity.getState()) {
            //向上电梯
            if (entity.getCurrentFloor() <= personEntity.getCurrentFloor() && personEntity.getCurrentFloor() <= entity.getTargetFloor()) {
                return true;
            }
            //向下电梯
            if (entity.getCurrentFloor() >= personEntity.getCurrentFloor() && personEntity.getCurrentFloor() >= entity.getTargetFloor()) {
                return true;
            }
        }
        return false;
    }


    /**
     * 构建乘坐电梯上下文
     *
     * @param stat
     * @param personEntities
     * @return
     */
    public static UseLiftContext generateUseLiftContext(LiftState stat, List<PersonEntity> personEntities) {
        if (personEntities == null || personEntities.isEmpty()) {
            return null;
        }
        UseLiftContext liftContext = new UseLiftContext();
        int maxFloor = -1;
        int num = 0;
        for (PersonEntity temp : personEntities) {
            if (temp.getState() == stat) {
                if (temp.getTargetFloor() > maxFloor) {
                    maxFloor = temp.getTargetFloor();
                }
                num++;
            }
        }
        liftContext.setMaxTargetFloor(maxFloor);
        liftContext.setPersonNum(num);
        return liftContext;
    }

}
