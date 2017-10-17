package com.ctw.domain.orgrole;

import java.util.ArrayList;
import java.util.List;



/**
 * OrgRoleEntity<->OrgRole转换工具类
 *
 */
public class OrgRoleVoConvert {

    public static OrgRole toVo(OrgRoleEntity entity) {
        if (entity == null) {
            return null;
        }
        OrgRole vo = new OrgRole();
        vo.setId(entity.getId());
        vo.setOrgId(entity.getOrgId());
        vo.setRoleId(entity.getRoleId());
        vo.setTs(entity.getTs());
        return vo;
    }
    
    public static OrgRoleEntity toEntity(OrgRole vo) {
        if (vo == null) {
            return null;
        }
        OrgRoleEntity entity = new OrgRoleEntity();
        entity.setId(vo.getId());
        entity.setOrgId(vo.getOrgId());
        entity.setRoleId(vo.getRoleId());
        entity.setTs(vo.getTs());
        return entity;
    }

    public static List<OrgRole> toVoList(List<OrgRoleEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<OrgRole> voList = new ArrayList<OrgRole>();
        for (OrgRoleEntity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<OrgRoleEntity> toEntityList(List<OrgRole> voList) {
        if (voList == null) {
            return null;
        }
        List<OrgRoleEntity> entityList = new ArrayList<OrgRoleEntity>();
        for (OrgRole vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}