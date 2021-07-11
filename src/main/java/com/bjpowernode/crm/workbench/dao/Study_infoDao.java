package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.Study_info;

import java.util.List;
import java.util.Map;

public interface Study_infoDao {
    //static int getTotalByCondition(Map<String, Object> map);

    List<Study_info> getActivityListByCondition(Map<String, Object> map);

    int getTotalByCondition(Map<String, Object> map);

    void update_status(String studyID);

    boolean update_status2(String id);

    boolean update_status3(String id_info);
}
