package com.ctw.domain.role;

import java.util.ArrayList;
import java.util.List;



/**
 * RoleEntity<->Role转换工具类
 *
 */
public class RoleVoConvert {

    public static Role toVo(RoleEntity entity) {
        if (entity == null) {
            return null;
        }
        Role vo = new Role();
        vo.setId(entity.getId());
        vo.setRoleName(entity.getRoleName());
        vo.setDescription(entity.getDescription());
        vo.setIsValid(entity.getIsValid());
        vo.setCreateDate(entity.getCreateDate());
        vo.setCreateNameId(entity.getCreateNameId());
        return vo;
    }
    
    public static RoleEntity toEntity(Role vo) {
        if (vo == null) {
            return null;
        }
        RoleEntity entity = new RoleEntity();
        entity.setId(vo.getId());
        entity.setRoleName(vo.getRoleName());
        entity.setDescription(vo.getDescription());
        entity.setIsValid(vo.getIsValid());
        entity.setCreateDate(vo.getCreateDate());
        entity.setCreateNameId(vo.getCreateNameId());
        return entity;
    }

    public static List<Role> toVoList(List<RoleEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<Role> voList = new ArrayList<Role>();
        for (RoleEntity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<RoleEntity> toEntityList(List<Role> voList) {
        if (voList == null) {
            return null;
        }
        List<RoleEntity> entityList = new ArrayList<RoleEntity>();
        for (Role vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}