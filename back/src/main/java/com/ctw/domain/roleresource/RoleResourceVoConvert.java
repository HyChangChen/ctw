package com.ctw.domain.roleresource;

import java.util.ArrayList;
import java.util.List;



/**
 * RoleResourceEntity<->RoleResource转换工具类
 *
 */
public class RoleResourceVoConvert {

    public static RoleResource toVo(RoleResourceEntity entity) {
        if (entity == null) {
            return null;
        }
        RoleResource vo = new RoleResource();
        vo.setId(entity.getId());
        vo.setRoleId(entity.getRoleId());
        vo.setResourceId(entity.getResourceId());
        vo.setTs(entity.getTs());
        return vo;
    }
    
    public static RoleResourceEntity toEntity(RoleResource vo) {
        if (vo == null) {
            return null;
        }
        RoleResourceEntity entity = new RoleResourceEntity();
        entity.setId(vo.getId());
        entity.setRoleId(vo.getRoleId());
        entity.setResourceId(vo.getResourceId());
        entity.setTs(vo.getTs());
        return entity;
    }

    public static List<RoleResource> toVoList(List<RoleResourceEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<RoleResource> voList = new ArrayList<RoleResource>();
        for (RoleResourceEntity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<RoleResourceEntity> toEntityList(List<RoleResource> voList) {
        if (voList == null) {
            return null;
        }
        List<RoleResourceEntity> entityList = new ArrayList<RoleResourceEntity>();
        for (RoleResource vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}