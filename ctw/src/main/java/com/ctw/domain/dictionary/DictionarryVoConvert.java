package com.ctw.domain.dictionary;

import java.util.ArrayList;
import java.util.List;



/**
 * DictionarryEntity<->Dictionarry转换工具类
 *
 */
public class DictionarryVoConvert {

    public static Dictionarry toVo(DictionarryEntity entity) {
        if (entity == null) {
            return null;
        }
        Dictionarry vo = new Dictionarry();
        vo.setId(entity.getId());
        vo.setRemark(entity.getRemark());
        vo.setDescription(entity.getDescription());
        vo.setActive(entity.getActive());
        vo.setChangedate(entity.getChangedate());
        vo.setChangeby(entity.getChangeby());
        vo.setCurrencyid(entity.getCurrencyid());
        vo.setType(entity.getType());
        vo.setOrgid(entity.getOrgid());
        vo.setSiteid(entity.getSiteid());
        vo.setValue(entity.getValue());
        vo.setText(entity.getText());
        return vo;
    }

    public static DictionarryEntity toEntity(Dictionarry vo) {
        if (vo == null) {
            return null;
        }
        DictionarryEntity entity = new DictionarryEntity();
        entity.setId(vo.getId());
        entity.setRemark(vo.getRemark());
        entity.setDescription(vo.getDescription());
        entity.setActive(vo.getActive());
        entity.setChangedate(vo.getChangedate());
        entity.setChangeby(vo.getChangeby());
        entity.setCurrencyid(vo.getCurrencyid());
        entity.setType(vo.getType());
        entity.setOrgid(vo.getOrgid());
        entity.setSiteid(vo.getSiteid());
        entity.setValue(vo.getValue());
        entity.setText(vo.getText());
        return entity;
    }

    public static List<Dictionarry> toVoList(List<DictionarryEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<Dictionarry> voList = new ArrayList<Dictionarry>();
        for (DictionarryEntity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<DictionarryEntity> toEntityList(List<Dictionarry> voList) {
        if (voList == null) {
            return null;
        }
        List<DictionarryEntity> entityList = new ArrayList<DictionarryEntity>();
        for (Dictionarry vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}