package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.DiseaseDictionary;
import com.bjpowernode.crm.workbench.domain.Dicvalue;

import java.util.List;

public interface dic_valueService {
    List<Dicvalue> get_dictionary();

    List<DiseaseDictionary> get_disease_dictionary();
}
