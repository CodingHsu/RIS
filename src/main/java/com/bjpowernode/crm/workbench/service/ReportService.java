package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.vo.PaginationVO;
import com.bjpowernode.crm.workbench.domain.Patient;
import com.bjpowernode.crm.workbench.domain.Report_wtt;
import com.bjpowernode.crm.workbench.domain.Study_info;

import java.util.Map;

public interface ReportService {

    public Report_wtt getById(String id);
    public boolean save(Report_wtt r);

    PaginationVO<Study_info> pageList(Map<String, Object> map);
    PaginationVO<Report_wtt> getdata(Map<String, Object> map);

    void update_status(String studyID);

    PaginationVO<Report_wtt> pageList_review(Map<String, Object> map);

    Report_wtt get_report(String id);

    boolean pass(String id);

    boolean reject(String id);

    //更改study_info表的status状态为待修改即4
    boolean update_status2(String id);

    boolean update_status3(String id_info);

    //获取病人的年龄和性别
    Patient get_age_gender(String patientID);

    Study_info get_data_from_studyInfo(String id);

    boolean update(Report_wtt r);

    String searchIdByNameFromTblUser(String createUserID0);

    Patient getPatientName(String patientID);

    String getDiseaseName(String diseaseName);

    boolean updateAuditorID(String id, String auditorID);

    String getPatientID(String studyID);
}
