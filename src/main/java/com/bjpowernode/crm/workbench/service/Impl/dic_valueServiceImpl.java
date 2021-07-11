package com.bjpowernode.crm.workbench.service.Impl;

import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.workbench.dao.DicvalueDao;
import com.bjpowernode.crm.workbench.domain.Dicvalue;
import com.bjpowernode.crm.workbench.domain.DiseaseDictionary;
import com.bjpowernode.crm.workbench.service.dic_valueService;

import java.util.List;

//这里包括报告模块所有对于数据字典（...）的操作
public class dic_valueServiceImpl implements dic_valueService {

    private DicvalueDao dic = SqlSessionUtil.getSqlSession().getMapper(DicvalueDao.class);

    public List<Dicvalue> get_dictionary() {
            List<Dicvalue> dList = dic.get_dictionary();
            return dList;

    }

    public List<DiseaseDictionary> get_disease_dictionary() {
        List<DiseaseDictionary> dList = dic.get_disease_dictionary();
        return dList;
    }
}
