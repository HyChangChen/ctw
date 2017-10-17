package com.ctw.domain.postrole;

import java.util.ArrayList;
import java.util.List;



/**
 * PostRoleEntity<->PostRole转换工具类
 *
 */
public class PostRoleVoConvert {

    public static PostRole toVo(PostRoleEntity entity) {
        if (entity == null) {
            return null;
        }
        PostRole vo = new PostRole();
        vo.setId(entity.getId());
        vo.setPsotId(entity.getPsotId());
        vo.setRoleId(entity.getRoleId());
        vo.setTs(entity.getTs());
        return vo;
    }
    
    public static PostRoleEntity toEntity(PostRole vo) {
        if (vo == null) {
            return null;
        }
        PostRoleEntity entity = new PostRoleEntity();
        entity.setId(vo.getId());
        entity.setPsotId(vo.getPsotId());
        entity.setRoleId(vo.getRoleId());
        entity.setTs(vo.getTs());
        return entity;
    }

    public static List<PostRole> toVoList(List<PostRoleEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<PostRole> voList = new ArrayList<PostRole>();
        for (PostRoleEntity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<PostRoleEntity> toEntityList(List<PostRole> voList) {
        if (voList == null) {
            return null;
        }
        List<PostRoleEntity> entityList = new ArrayList<PostRoleEntity>();
        for (PostRole vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}