package com.ctw.domain.roleuser;

import java.util.ArrayList;
import java.util.List;



/**
 * RoleUserEntity<->RoleUser转换工具类
 *
 */
public class RoleUserVoConvert {

    public static RoleUser toVo(RoleUserEntity entity) {
        if (entity == null) {
            return null;
        }
        RoleUser vo = new RoleUser();
        vo.setId(entity.getId());
        vo.setUid(entity.getUid());
        vo.setRoleId(entity.getRoleId());
        vo.setTs(entity.getTs());
        return vo;
    }
    
    public static RoleUserEntity toEntity(RoleUser vo) {
        if (vo == null) {
            return null;
        }
        RoleUserEntity entity = new RoleUserEntity();
        entity.setId(vo.getId());
        entity.setUid(vo.getUid());
        entity.setRoleId(vo.getRoleId());
        entity.setTs(vo.getTs());
        return entity;
    }

    public static List<RoleUser> toVoList(List<RoleUserEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<RoleUser> voList = new ArrayList<RoleUser>();
        for (RoleUserEntity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<RoleUserEntity> toEntityList(List<RoleUser> voList) {
        if (voList == null) {
            return null;
        }
        List<RoleUserEntity> entityList = new ArrayList<RoleUserEntity>();
        for (RoleUser vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}