package com.ctw.domain.apps;

import java.util.ArrayList;
import java.util.List;



/**
 * AppsEntity<->Apps转换工具类
 *
 */
public class AppsVoConvert {

    public static Apps toVo(AppsEntity entity) {
        if (entity == null) {
            return null;
        }
        Apps vo = new Apps();
        vo.setAppsid(entity.getAppsid());
        vo.setApp(entity.getApp());
        vo.setApptype(entity.getApptype());
        vo.setCustapptype(entity.getCustapptype());
        vo.setDeletee(entity.getDeletee());
        vo.setDescription(entity.getDescription());
        vo.setGroupname(entity.getGroupname());
        vo.setInsertt(entity.getInsertt());
        vo.setIsmobile(entity.getIsmobile());
        vo.setKeyattribute(entity.getKeyattribute());
        vo.setObjectname(entity.getObjectname());
        vo.setMaxappsid(entity.getMaxappsid());
        vo.setModule(entity.getModule());
        vo.setOrderby(entity.getOrderby());
        vo.setOriginalapp(entity.getOriginalapp());
        vo.setReadd(entity.getReadd());
        vo.setRestrictions(entity.getRestrictions());
        vo.setSave(entity.getSave());
        vo.setViewport(entity.getViewport());
        vo.setDatasrc(entity.getDatasrc());
        return vo;
    }

    public static AppsEntity toEntity(Apps vo) {
        if (vo == null) {
            return null;
        }
        AppsEntity entity = new AppsEntity();
        entity.setAppsid(vo.getAppsid());
        entity.setApp(vo.getApp());
        entity.setApptype(vo.getApptype());
        entity.setCustapptype(vo.getCustapptype());
        entity.setDeletee(vo.getDeletee());
        entity.setDescription(vo.getDescription());
        entity.setGroupname(vo.getGroupname());
        entity.setInsertt(vo.getInsertt());
        entity.setIsmobile(vo.getIsmobile());
        entity.setKeyattribute(vo.getKeyattribute());
        entity.setObjectname(vo.getObjectname());
        entity.setMaxappsid(vo.getMaxappsid());
        entity.setModule(vo.getModule());
        entity.setOrderby(vo.getOrderby());
        entity.setOriginalapp(vo.getOriginalapp());
        entity.setReadd(vo.getReadd());
        entity.setRestrictions(vo.getRestrictions());
        entity.setSave(vo.getSave());
        entity.setViewport(vo.getViewport());
        entity.setDatasrc(vo.getDatasrc());
        return entity;
    }

    public static List<Apps> toVoList(List<AppsEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<Apps> voList = new ArrayList<Apps>();
        for (AppsEntity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<AppsEntity> toEntityList(List<Apps> voList) {
        if (voList == null) {
            return null;
        }
        List<AppsEntity> entityList = new ArrayList<AppsEntity>();
        for (Apps vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}