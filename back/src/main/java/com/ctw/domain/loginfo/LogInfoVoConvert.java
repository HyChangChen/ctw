package com.ctw.domain.loginfo;

import java.util.ArrayList;
import java.util.List;



/**
 * LogInfoEntity<->LogInfo转换工具类
 *
 */
public class LogInfoVoConvert {

    public static LogInfo toVo(LogInfoEntity entity) {
        if (entity == null) {
            return null;
        }
        LogInfo vo = new LogInfo();
        vo.setId(entity.getId());
        vo.setFunctionName(entity.getFunctionName());
        vo.setParams(entity.getParams());
        vo.setContimes(entity.getContimes());
        vo.setIpAddress(entity.getIpAddress());
        vo.setMacAddress(entity.getMacAddress());
        vo.setMessage(entity.getMessage());
        vo.setUsername(entity.getUsername());
        vo.setLogLevel(entity.getLogLevel());
        vo.setCreateTime(entity.getCreateTime());
        return vo;
    }

    public static LogInfoEntity toEntity(LogInfo vo) {
        if (vo == null) {
            return null;
        }
        LogInfoEntity entity = new LogInfoEntity();
        entity.setId(vo.getId());
        entity.setFunctionName(vo.getFunctionName());
        entity.setParams(vo.getParams());
        entity.setContimes(vo.getContimes());
        entity.setIpAddress(vo.getIpAddress());
        entity.setMacAddress(vo.getMacAddress());
        entity.setMessage(vo.getMessage());
        entity.setUsername(vo.getUsername());
        entity.setLogLevel(vo.getLogLevel());
        entity.setCreateTime(vo.getCreateTime());
        return entity;
    }

    public static List<LogInfo> toVoList(List<LogInfoEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<LogInfo> voList = new ArrayList<LogInfo>();
        for (LogInfoEntity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<LogInfoEntity> toEntityList(List<LogInfo> voList) {
        if (voList == null) {
            return null;
        }
        List<LogInfoEntity> entityList = new ArrayList<LogInfoEntity>();
        for (LogInfo vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}