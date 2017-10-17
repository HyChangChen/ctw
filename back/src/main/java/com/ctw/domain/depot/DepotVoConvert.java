package com.ctw.domain.depot;

import java.util.ArrayList;
import java.util.List;



/**
 * DepotEntity<->Depot转换工具类
 *
 */
public class DepotVoConvert {

    public static Depot toVo(DepotEntity entity) {
        if (entity == null) {
            return null;
        }
        Depot vo = new Depot();
        vo.setId(entity.getId());
        vo.setDepotNo(entity.getDepotNo());
        vo.setDepotName(entity.getDepotName());
        vo.setDepotManager(entity.getDepotManager());
        vo.setAddress(entity.getAddress());
        vo.setIsValid(entity.getIsValid());
        vo.setIsDefault(entity.getIsDefault());
        return vo;
    }

    public static DepotEntity toEntity(Depot vo) {
        if (vo == null) {
            return null;
        }
        DepotEntity entity = new DepotEntity();
        entity.setId(vo.getId());
        entity.setDepotNo(vo.getDepotNo());
        entity.setDepotName(vo.getDepotName());
        entity.setDepotManager(vo.getDepotManager());
        entity.setAddress(vo.getAddress());
        entity.setIsValid(vo.getIsValid());
        entity.setIsDefault(vo.getIsDefault());
        return entity;
    }

    public static List<Depot> toVoList(List<DepotEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<Depot> voList = new ArrayList<Depot>();
        for (DepotEntity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<DepotEntity> toEntityList(List<Depot> voList) {
        if (voList == null) {
            return null;
        }
        List<DepotEntity> entityList = new ArrayList<DepotEntity>();
        for (Depot vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}