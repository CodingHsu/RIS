package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.Dicvalue;
import com.bjpowernode.crm.workbench.domain.DiseaseDictionary;

import java.util.List;

public interface DicvalueDao {

    public List<Dicvalue> get_dictionary();

    List<DiseaseDictionary> get_disease_dictionary();
}
