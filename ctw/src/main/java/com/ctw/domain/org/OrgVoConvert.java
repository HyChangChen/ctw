package com.ctw.domain.org;

import java.util.ArrayList;
import java.util.List;



/**
 * OrgEntity<->Org转换工具类
 *
 */
public class OrgVoConvert {

    public static Org toVo(OrgEntity entity) {
        if (entity == null) {
            return null;
        }
        Org vo = new Org();
        vo.setId(entity.getId());
        vo.setOrgName(entity.getOrgName());
        vo.setPartentId(entity.getPartentId());
        vo.setLeaf(entity.getLeaf());
        vo.setIsValid(entity.getIsValid());
        vo.setTs(entity.getTs());
        vo.setCreateNameId(entity.getCreateNameId());
        vo.setIcon(entity.getIcon());
        return vo;
    }
    
    public static OrgEntity toEntity(Org vo) {
        if (vo == null) {
            return null;
        }
        OrgEntity entity = new OrgEntity();
        entity.setId(vo.getId());
        entity.setOrgName(vo.getOrgName());
        entity.setPartentId(vo.getPartentId());
        entity.setLeaf(vo.getLeaf());
        entity.setIsValid(vo.getIsValid());
        entity.setTs(vo.getTs());
        entity.setCreateNameId(vo.getCreateNameId());
        entity.setIcon(vo.getIcon());
        return entity;
    }

    public static List<Org> toVoList(List<OrgEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<Org> voList = new ArrayList<Org>();
        for (OrgEntity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<OrgEntity> toEntityList(List<Org> voList) {
        if (voList == null) {
            return null;
        }
        List<OrgEntity> entityList = new ArrayList<OrgEntity>();
        for (Org vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}