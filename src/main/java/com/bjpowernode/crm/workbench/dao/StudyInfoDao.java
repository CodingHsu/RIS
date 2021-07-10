package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.StudyInfo;

import java.util.List;
import java.util.Map;

public interface StudyInfoDao {

    int getTotalByCondition(Map<String, Object> map);

    List<StudyInfo> getStudyInfoDaoListByCondition(Map<String, Object> map);

    int save(StudyInfo s);

    int delete(String[] ids);

    StudyInfo getStudyInfoByaccessionNumber(String accessionNumber);

    int update(StudyInfo s);

    int updatescheduledProcedureStepStart(StudyInfo s);

    int cancel(StudyInfo s);

    int signin(StudyInfo s);
}
