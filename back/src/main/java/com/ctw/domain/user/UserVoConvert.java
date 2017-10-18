package com.ctw.domain.user;

import java.util.ArrayList;
import java.util.List;



/**
 * UserEntity<->User转换工具类
 *
 */
public class UserVoConvert {

    public static User toVo(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        User vo = new User();
        vo.setId(entity.getId());
        vo.setLoginName(entity.getLoginName());
        vo.setPassWord(entity.getPassWord());
        vo.setFullName(entity.getFullName());
        vo.setChinaName(entity.getChinaName());
        vo.setSalt(entity.getSalt());
        vo.setMobile(entity.getMobile());
        vo.setTel(entity.getTel());
        vo.setAddress(entity.getAddress());
        vo.setEmail(entity.getEmail());
        vo.setAge(entity.getAge());
        vo.setGenter(entity.getGenter());
        vo.setRegTime(entity.getRegTime());
        vo.setOrgId(entity.getOrgId());
        vo.setPostId(entity.getPostId());
        vo.setStatus(entity.getStatus());
        vo.setQq(entity.getQq());
        vo.setIsValid(entity.getIsValid());
        return vo;
    }
    
    public static UserEntity toEntity(User vo) {
        if (vo == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setId(vo.getId());
        entity.setLoginName(vo.getLoginName());
        entity.setPassWord(vo.getPassWord());
        entity.setFullName(vo.getFullName());
        entity.setChinaName(vo.getChinaName());
        entity.setSalt(vo.getSalt());
        entity.setMobile(vo.getMobile());
        entity.setTel(vo.getTel());
        entity.setAddress(vo.getAddress());
        entity.setEmail(vo.getEmail());
        entity.setAge(vo.getAge());
        entity.setGenter(vo.getGenter());
        entity.setRegTime(vo.getRegTime());
        entity.setOrgId(vo.getOrgId());
        entity.setPostId(vo.getPostId());
        entity.setStatus(vo.getStatus());
        entity.setQq(vo.getQq());
        entity.setIsValid(vo.getIsValid());
        return entity;
    }

    public static List<User> toVoList(List<UserEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<User> voList = new ArrayList<User>();
        for (UserEntity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<UserEntity> toEntityList(List<User> voList) {
        if (voList == null) {
            return null;
        }
        List<UserEntity> entityList = new ArrayList<UserEntity>();
        for (User vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}