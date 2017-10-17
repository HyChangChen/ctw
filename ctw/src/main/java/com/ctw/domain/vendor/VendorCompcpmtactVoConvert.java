package com.ctw.domain.vendor;

import java.util.ArrayList;
import java.util.List;



/**
 * VendorCompcpmtactEntity<->VendorCompcpmtact转换工具类
 *
 */
public class VendorCompcpmtactVoConvert {

    public static VendorCompcpmtact toVo(VendorCompcpmtactEntity entity) {
        if (entity == null) {
            return null;
        }
        VendorCompcpmtact vo = new VendorCompcpmtact();
        vo.setId(entity.getId());
        vo.setCompcontactnum(entity.getCompcontactnum());
        vo.setVendorId(entity.getVendorId());
        vo.setCompcontactid(entity.getCompcontactid());
        vo.setCompany(entity.getCompany());
        vo.setContact(entity.getContact());
        vo.setPosition(entity.getPosition());
        vo.setVoicePhone(entity.getVoicePhone());
        vo.setFaxPhone(entity.getFaxPhone());
        vo.setEmail(entity.getEmail());
        vo.setHomePhone(entity.getHomePhone());
        vo.setCellPhone(entity.getCellPhone());
        vo.setVendorNum(entity.getVendorNum());
        vo.setDescription(entity.getDescription());
        vo.setStatus(entity.getStatus());
        vo.setOrgid(entity.getOrgid());
        vo.setSiteid(entity.getSiteid());
        return vo;
    }

    public static VendorCompcpmtactEntity toEntity(VendorCompcpmtact vo) {
        if (vo == null) {
            return null;
        }
        VendorCompcpmtactEntity entity = new VendorCompcpmtactEntity();
        entity.setId(vo.getId());
        entity.setCompcontactnum(vo.getCompcontactnum());
        entity.setVendorId(vo.getVendorId());
        entity.setCompcontactid(vo.getCompcontactid());
        entity.setCompany(vo.getCompany());
        entity.setContact(vo.getContact());
        entity.setPosition(vo.getPosition());
        entity.setVoicePhone(vo.getVoicePhone());
        entity.setFaxPhone(vo.getFaxPhone());
        entity.setEmail(vo.getEmail());
        entity.setHomePhone(vo.getHomePhone());
        entity.setCellPhone(vo.getCellPhone());
        entity.setVendorNum(vo.getVendorNum());
        entity.setDescription(vo.getDescription());
        entity.setStatus(vo.getStatus());
        entity.setOrgid(vo.getOrgid());
        entity.setSiteid(vo.getSiteid());
        return entity;
    }

    public static List<VendorCompcpmtact> toVoList(List<VendorCompcpmtactEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<VendorCompcpmtact> voList = new ArrayList<VendorCompcpmtact>();
        for (VendorCompcpmtactEntity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<VendorCompcpmtactEntity> toEntityList(List<VendorCompcpmtact> voList) {
        if (voList == null) {
            return null;
        }
        List<VendorCompcpmtactEntity> entityList = new ArrayList<VendorCompcpmtactEntity>();
        for (VendorCompcpmtact vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}