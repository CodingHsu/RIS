package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.Patient;
import com.bjpowernode.crm.workbench.domain.Report_wtt;
import com.bjpowernode.crm.workbench.domain.Study_info;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReportDao {
    public Report_wtt getById(String id);
    Report_wtt select3(Report_wtt r);
    int save(Report_wtt r);
    List<Report_wtt> getPart(Map<String, Object> map);

    int getTotalByCondition(Map<String, Object> map);

    List<Report_wtt> getActivityListByCondition(Map<String, Object> map);

    Report_wtt get_report(String id);

    boolean pass(String id);

    boolean reject(String id);

    Patient get_age_gender(String id);

    Study_info get_data_from_studyInfo(String id);

    boolean update(Report_wtt r);

    String searchIdByNameFromTblUser(String createUserID0);

    Patient getPatientName(String patientID);

    String getDiseaseName(String diseaseName);

    boolean updateAuditorID(@Param("id") String id, @Param("auditorID") String auditorID);

    String getPatientID(String studyID);
}
