package com.ctw.domain.post;

import java.util.ArrayList;
import java.util.List;



/**
 * PostEntity<->Post转换工具类
 *
 */
public class PostVoConvert {

    public static Post toVo(PostEntity entity) {
        if (entity == null) {
            return null;
        }
        Post vo = new Post();
        vo.setId(entity.getId());
        vo.setPostName(entity.getPostName());
        vo.setDescription(entity.getDescription());
        vo.setIsValid(entity.getIsValid());
        vo.setIcon(entity.getIcon());
        vo.setCreatDate(entity.getCreatDate());
        vo.setCreateNameId(entity.getCreateNameId());
        vo.setOrgId(entity.getOrgId());
        return vo;
    }
    
    public static PostEntity toEntity(Post vo) {
        if (vo == null) {
            return null;
        }
        PostEntity entity = new PostEntity();
        entity.setId(vo.getId());
        entity.setPostName(vo.getPostName());
        entity.setDescription(vo.getDescription());
        entity.setIsValid(vo.getIsValid());
        entity.setIcon(vo.getIcon());
        entity.setCreatDate(vo.getCreatDate());
        entity.setCreateNameId(vo.getCreateNameId());
        entity.setOrgId(vo.getOrgId());
        return entity;
    }

    public static List<Post> toVoList(List<PostEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<Post> voList = new ArrayList<Post>();
        for (PostEntity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<PostEntity> toEntityList(List<Post> voList) {
        if (voList == null) {
            return null;
        }
        List<PostEntity> entityList = new ArrayList<PostEntity>();
        for (Post vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}