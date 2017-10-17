package com.ctw.domain.resource;

import java.util.ArrayList;
import java.util.List;



/**
 * ResourceEntity<->Resource转换工具类
 *
 */
public class ResourceVoConvert {

    public static ResourceVo toVo(ResourceEntity entity) {
        if (entity == null) {
            return null;
        }
        ResourceVo vo = new ResourceVo();
        vo.setId(entity.getId());
        vo.setName(entity.getName());
        vo.setUrl(entity.getUrl());
        vo.setType(entity.getType());
        vo.setPriority(entity.getPriority());
        vo.setParentId(entity.getParentId());
        vo.setIsValid(entity.getIsValid());
        vo.setPartentsIds(entity.getPartentsIds());
        vo.setLeaf(entity.getLeaf());
        vo.setCreateNameId(entity.getCreateNameId());
        vo.setTs(entity.getTs());
        vo.setDescription(entity.getDescription());
        vo.setIcon(entity.getIcon());
        vo.setPermission(entity.getPermission());
        return vo;
    }
    
    public static ResourceEntity toEntity(ResourceVo vo) {
        if (vo == null) {
            return null;
        }
        ResourceEntity entity = new ResourceEntity();
        entity.setId(vo.getId());
        entity.setName(vo.getName());
        entity.setUrl(vo.getUrl());
        entity.setType(vo.getType());
        entity.setPriority(vo.getPriority());
        entity.setParentId(vo.getParentId());
        entity.setIsValid(vo.getIsValid());
        entity.setPartentsIds(vo.getPartentsIds());
        entity.setLeaf(vo.getLeaf());
        entity.setCreateNameId(vo.getCreateNameId());
        entity.setTs(vo.getTs());
        entity.setDescription(vo.getDescription());
        entity.setIcon(vo.getIcon());
        entity.setPermission(vo.getPermission());
        return entity;
    }

    public static List<ResourceVo> toVoList(List<ResourceEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<ResourceVo> voList = new ArrayList<ResourceVo>();
        for (ResourceEntity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<ResourceEntity> toEntityList(List<ResourceVo> voList) {
        if (voList == null) {
            return null;
        }
        List<ResourceEntity> entityList = new ArrayList<ResourceEntity>();
        for (ResourceVo vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}