package com.ctw.domain.vendor;

import java.util.ArrayList;
import java.util.List;



/**
 * VendorEntity<->Vendor转换工具类
 *
 */
public class VendorVoConvert {

    public static Vendor toVo(VendorEntity entity) {
        if (entity == null) {
            return null;
        }
        Vendor vo = new Vendor();
        vo.setId(entity.getId());
        vo.setVendorNum(entity.getVendorNum());
        vo.setVendorName(entity.getVendorName());
        vo.setType(entity.getType());
        vo.setContact(entity.getContact());
        vo.setPhone(entity.getPhone());
        vo.setAddress(entity.getAddress());
        vo.setPaymentTerms(entity.getPaymentTerms());
        vo.setRemitContact(entity.getRemitContact());
        vo.setStatuss(entity.getStatuss());
        vo.setChangeby(entity.getChangeby());
        vo.setDatetime(entity.getDatetime());
        vo.setBankaccount(entity.getBankaccount());
        vo.setFax(entity.getFax());
        vo.setCurrencyCode(entity.getCurrencyCode());
        vo.setShipvia(entity.getShipvia());
        vo.setTaxrate(entity.getTaxrate());
        vo.setNote(entity.getNote());
        vo.setRemitaDdress(entity.getRemitaDdress());
        vo.setRegIstration(entity.getRegIstration());
        vo.setGroupid(entity.getGroupid());
        vo.setOpenAccount(entity.getOpenAccount());
        vo.setMail(entity.getMail());
        vo.setScope(entity.getScope());
        vo.setOrgid(entity.getOrgid());
        vo.setSiteid(entity.getSiteid());
        return vo;
    }

    public static VendorEntity toEntity(Vendor vo) {
        if (vo == null) {
            return null;
        }
        VendorEntity entity = new VendorEntity();
        entity.setId(vo.getId());
        entity.setVendorNum(vo.getVendorNum());
        entity.setVendorName(vo.getVendorName());
        entity.setType(vo.getType());
        entity.setContact(vo.getContact());
        entity.setPhone(vo.getPhone());
        entity.setAddress(vo.getAddress());
        entity.setPaymentTerms(vo.getPaymentTerms());
        entity.setRemitContact(vo.getRemitContact());
        entity.setStatuss(vo.getStatuss());
        entity.setChangeby(vo.getChangeby());
        entity.setDatetime(vo.getDatetime());
        entity.setBankaccount(vo.getBankaccount());
        entity.setFax(vo.getFax());
        entity.setCurrencyCode(vo.getCurrencyCode());
        entity.setShipvia(vo.getShipvia());
        entity.setTaxrate(vo.getTaxrate());
        entity.setNote(vo.getNote());
        entity.setRemitaDdress(vo.getRemitaDdress());
        entity.setRegIstration(vo.getRegIstration());
        entity.setGroupid(vo.getGroupid());
        entity.setOpenAccount(vo.getOpenAccount());
        entity.setMail(vo.getMail());
        entity.setScope(vo.getScope());
        entity.setOrgid(vo.getOrgid());
        entity.setSiteid(vo.getSiteid());
        return entity;
    }

    public static List<Vendor> toVoList(List<VendorEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<Vendor> voList = new ArrayList<Vendor>();
        for (VendorEntity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<VendorEntity> toEntityList(List<Vendor> voList) {
        if (voList == null) {
            return null;
        }
        List<VendorEntity> entityList = new ArrayList<VendorEntity>();
        for (Vendor vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}