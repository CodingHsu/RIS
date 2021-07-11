package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.vo.PaginationVO;
import com.bjpowernode.crm.workbench.domain.*;
import com.bjpowernode.crm.workbench.service.Impl.ReportServiceImpl;
import com.bjpowernode.crm.workbench.service.Impl.dic_valueServiceImpl;
import com.bjpowernode.crm.workbench.service.ReportService;
import com.bjpowernode.crm.workbench.service.dic_valueService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//一个模块一个servlet
public class ReportController extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入到报告控制器");
        String path = request.getServletPath();

        if("/workbench/Report_wtt/save.do".equals(path)){
            System.out.println("正在保存");
            save(request,response);
            System.out.println("保存成功");

        }else if("/workbench/Report_wtt/get_data.do".equals(path)){

            getdata(request,response);

        }
        else if("/workbench/Report_wtt/pageList.do".equals(path)){
            pageList(request,response);
        }

        else if("/workbench/Report_wtt/get_datalist.do".equals(path)){
            get_datalist(request,response);
        }

        else if("/workbench/Report_wtt/get_datalist2.do".equals(path)){
            get_datalist2(request,response);
        }
        else if("/workbench/Report_wtt/get_dictionary.do".equals(path)){
            get_dictionary(request,response);
        }
        else if("/workbench/Report_wtt/pageList_review.do".equals(path)){
            pageList_review(request,response);
        }
        else if("/workbench/Report_wtt/get_report.do".equals(path)){
            get_report(request,response);
        }
        else if("/workbench/Report_wtt/pass.do".equals(path)){
            pass(request,response);
        }
        else if("/workbench/Report_wtt/reject.do".equals(path)){
            reject(request,response);
        }
        else if("/workbench/Report_wtt/get_age_gender.do".equals(path)){
            get_age_gender(request,response);
        }
        else if("/workbench/Report_wtt/get_data_from_studyInfo.do".equals(path)){
            get_data_from_studyInfo(request,response);
        }
        else if("/workbench/Report_wtt/get_disease_dictionary.do".equals(path)){
            get_disease_dictionary(request,response);
        }
        else if("/workbench/Report_wtt/get_report0.do".equals(path)){
            get_report0(request,response);
        }
    }

    private void get_disease_dictionary(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("取得疾病字典名称信息列表");
        dic_valueService us = (dic_valueService) ServiceFactory.getService(new dic_valueServiceImpl());
        List<DiseaseDictionary> uList = us.get_disease_dictionary();
        PrintJson.printJsonObj(response,uList);
    }

    private void get_data_from_studyInfo(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入查询投照方式，使用耗材，计划的程序步骤的相关描述。");
        String id= request.getParameter("creat-studyID");
        System.out.println(id);
        ReportService re = (ReportService) ServiceFactory.getService(new ReportServiceImpl());
        Study_info st = re.get_data_from_studyInfo(id);
        PrintJson.printJsonObj(response,st);
    }

    private void get_age_gender(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("病人信息查询模块");
        String studyID= request.getParameter("studyID");
        System.out.println("检查号："+studyID);

        //通过检查号获得病人号
        ReportService re = (ReportService) ServiceFactory.getService(new ReportServiceImpl());
        String patientID = re.getPatientID(studyID);
        System.out.println(patientID);
        ReportService re1 = (ReportService) ServiceFactory.getService(new ReportServiceImpl());
        Patient pa = re1.get_age_gender(patientID);
        System.out.println(pa);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("patientID", pa.getId());//病人ID
        map.put("name", pa.getName());//病人名称
        map.put("age", pa.getAge());//病人年龄
        map.put("gender", pa.getGender());//病人性别
        PrintJson.printJsonObj(response,map);
    }

    //pass和reject进行成功之后，都需要将tbl_report表中的审核医生ID加进去
    private void reject(HttpServletRequest request, HttpServletResponse response) {

        String id= request.getParameter("id");
        ReportService re = (ReportService) ServiceFactory.getService(new ReportServiceImpl());
        boolean flag = false;
        boolean flag1 = re.reject(id);

        ReportService re1 = (ReportService)ServiceFactory.getService(new ReportServiceImpl());
        String studyID= request.getParameter("studyID");
        boolean flag2 =re1.update_status2(studyID);
        System.out.println("flag1="+flag1+"flag2="+flag2);

        String auditorName= request.getParameter("auditorID");
        System.out.println("审核医生姓名: "+auditorName);
        ReportService re3 = (ReportService)ServiceFactory.getService(new ReportServiceImpl());
        String auditorID = re3.searchIdByNameFromTblUser(auditorName);
        ReportService re2 = (ReportService)ServiceFactory.getService(new ReportServiceImpl());
        boolean flag3 = re2.updateAuditorID(id,auditorID);
        System.out.println("flag3: "+flag3);

        if(flag1 && flag2 &&flag3) flag = true;
        PrintJson.printJsonFlag(response,flag);
    }

    private void pass(HttpServletRequest request, HttpServletResponse response) {

        String id= request.getParameter("id");
        ReportService re = (ReportService)ServiceFactory.getService(new ReportServiceImpl());
        boolean flag1 = re.pass(id);
        String studyID=request.getParameter("studyID");
        ReportService reportService = (ReportService) ServiceFactory.getService(new ReportServiceImpl());
        //将study_info表中的状态更改为7
        boolean flag2 = reportService.update_status3(studyID);
        boolean flag = false;

        String auditorName= request.getParameter("auditorID");
        System.out.println("审核医生姓名: "+auditorName);
        ReportService re3 = (ReportService)ServiceFactory.getService(new ReportServiceImpl());
        String auditorID = re3.searchIdByNameFromTblUser(auditorName);
        ReportService re2 = (ReportService)ServiceFactory.getService(new ReportServiceImpl());
        boolean flag3 = re2.updateAuditorID(id,auditorID);
        System.out.println("flag3: "+flag3);

        if(flag1&&flag2&&flag3) flag = true;
        PrintJson.printJsonFlag(response,flag);

    }

    private void get_report0(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("根据id查找这条报告的详细信息");
        String studyID= request.getParameter("studyID");
        ReportService re = (ReportService) ServiceFactory.getService(new ReportServiceImpl());
        Report_wtt report = re.get_report(studyID);
        System.out.println(report);

        //把上面的数据封装到一个map中
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id", report.getId());//报告号
        System.out.println("id到底是啥："+report.getId());
        //diseaseDescription,imagingFindings,diagnosticOpinion疾病描述，影像学所见，诊断意见
        map.put("diseaseDescription",report.getDiseaseDescription());//
        map.put("imagingFindings",report.getImagingFindings());//
        map.put("diagnosticOpinion",report.getDiagnosticOpinion());//

        PrintJson.printJsonObj(response,map);
    }
    private void get_report(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("根据id查找这条报告的详细信息");
        String studyID= request.getParameter("studyID");
        ReportService re = (ReportService) ServiceFactory.getService(new ReportServiceImpl());
        Report_wtt report = re.get_report(studyID);
        System.out.println(report);

        System.out.println("将病人号转化成为病人名，并获得病人年龄和性别");
        String patientID= request.getParameter("patientID");
        System.out.println(patientID);
        ReportService re1 = (ReportService) ServiceFactory.getService(new ReportServiceImpl());
        Patient p =  re1.getPatientName(patientID);
        System.out.println(p);

        System.out.println("将疾病名称ID转化为名称");
        String DiseaseName = report.getDiseaseName();
        System.out.println(DiseaseName);
        ReportService re3 = (ReportService) ServiceFactory.getService(new ReportServiceImpl());
        String DiseaseName1 =  re3.getDiseaseName(DiseaseName);
        System.out.println(DiseaseName1);

        //获得三个参数，投照方式....
        System.out.println("进入查询投照方式，使用耗材，计划的程序步骤的相关描述。");
        String id2= request.getParameter("studyID");
        System.out.println(id2);
        ReportService re2 = (ReportService) ServiceFactory.getService(new ReportServiceImpl());
        Study_info st = re2.get_data_from_studyInfo(id2);
        System.out.println(st);

        //把上面的数据封装到一个map中
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id", report.getId());//报告号
        System.out.println("id到底是啥："+report.getId());
        map.put("studyID", report.getStudyID());//检查号
        map.put("createUserID",report.getCreateUserID());//报告医生ID

        map.put("patientID", p.getName());//病人名称
        map.put("age", p.getAge());//病人年龄
        map.put("gender", p.getGender());//病人性别

        map.put("bodyPart",report.getBodyPart());//疾病部位
        map.put("diseaseName",DiseaseName1);//疾病名称
        map.put("positive",report.getPositive());//是否阳性

        //id="projection"使用耗材useConsumables,scheduledProcedureStepDescription
        map.put("projection",st.getProjection());//投照方式
        map.put("useConsumables",st.getUseConsumables());//使用耗材
        map.put("scheduledProcedureStepDescription",st.getScheduledProcedureStepDescription());//计划程序的相关步骤

        //diseaseDescription,imagingFindings,diagnosticOpinion疾病描述，影像学所见，诊断意见
        map.put("diseaseDescription",report.getDiseaseDescription());//
        map.put("imagingFindings",report.getImagingFindings());//
        map.put("diagnosticOpinion",report.getDiagnosticOpinion());//

        PrintJson.printJsonObj(response,map);
    }

    private void pageList_review(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("进入到审核报告信息列表的操作");

        String id = request.getParameter("id");
        String name = request.getParameter("id");
        String patientID = request.getParameter("patientID");
        String studyID = request.getParameter("studyID");
        String reportStatus = request.getParameter("reportStatus");

        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        //每页展现的记录数
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);
        //计算出略过的记录数
        int skipCount = (pageNo-1)*pageSize;

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id", id);
        map.put("name",name);
        map.put("patientID", patientID);
        map.put("studyID",studyID);
        map.put("reportStatus",reportStatus);
        //需要将状态6转换成待填写。
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);
        ReportService reportService = (ReportService) ServiceFactory.getService(new ReportServiceImpl());

        //vo位于src/main/java/com/bjpowernode/crm/vo/PaginationVO.java
        //里面有详细用法简述
        PaginationVO<Report_wtt> paginationVO = reportService.pageList_review(map);
        System.out.println("输出成功");

        //vo--> {"total":100,"dataList":[{待写报告1},{2},{3}]}
        PrintJson.printJsonObj(response, paginationVO);

    }

    private void get_dictionary(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("取得疾病字典信息列表");
        dic_valueService us = (dic_valueService) ServiceFactory.getService(new dic_valueServiceImpl());
        List<Dicvalue> uList = us.get_dictionary();
        PrintJson.printJsonObj(response,uList);
    }

    private void get_datalist2(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("取得用户信息列表");
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> uList = us.getUserList2();
        PrintJson.printJsonObj(response,uList);
    }

    private void get_datalist(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("取得用户信息列表");
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> uList = us.getUserList();
        PrintJson.printJsonObj(response,uList);

    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("进入到待写报告信息列表的操作（结合条件查询+分页查询）");

        //"studyID" :
        //"patientID"
        //"patientName
        //"status" :
        String studyID = request.getParameter("studyID");
        String patientID = request.getParameter("patientID");
        String patientName = request.getParameter("patientName");
        String status = request.getParameter("status");

        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        //每页展现的记录数
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);
        //计算出略过的记录数
        int skipCount = (pageNo-1)*pageSize;

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("studyID", studyID);
        map.put("patientID", patientID);
        map.put("patientName",patientName);
        //需要将状态6转换成待填写。
        map.put("status",status);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);
        ReportService reportService = (ReportService) ServiceFactory.getService(new ReportServiceImpl());

        //vo位于src/main/java/com/bjpowernode/crm/vo/PaginationVO.java
        //里面有详细用法简述
        PaginationVO<Study_info> paginationVO = reportService.pageList(map);
        System.out.println("输出成功");

        //vo--> {"total":100,"dataList":[{待写报告1},{2},{3}]}
        PrintJson.printJsonObj(response, paginationVO);
    }

    private void getdata(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行填写报告的从后端向前端传值操作");
        String id = UUIDUtil.getUUID();
        System.out.println("id:"+id);
        try {
            response.getWriter().print(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*//参考 03 System.out.println("进入到servlet03");
        System.out.println("进入到后台向前端传输数据功能模块");
        String studyID = UUIDUtil.getUUID();

        //从后端获取的数据
        String patientID = request.getParameter("patientID");
        String reportStatus = request.getParameter("reportStatus");
        String createUserID = request.getParameter("createUserID");
        String auditorID = request.getParameter("auditorID");

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("studyID", studyID);
        map.put("reportStatus", reportStatus);
        map.put("patientID", patientID);
        map.put("createUserID",createUserID);
        map.put("auditorID",auditorID);
        ReportService reportService = (ReportService) ServiceFactory.getService(new ReportServiceImpl());

        //vo位于src/main/java/com/bjpowernode/crm/vo/PaginationVO.java
        //里面有详细用法简述
        PaginationVO<Report_wtt> paginationVO = reportService.getdata(map);
        System.out.println("输出成功");

        //vo--> {"total":100,"dataList":[{待写报告1},{2},{3}]}
        PrintJson.printJsonObj(response, paginationVO);*/

    }

    private void save(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行填写报告的添加操作");

        String studyID = request.getParameter("studyID");
        Boolean flag1 = Boolean.valueOf(request.getParameter("flag"));
        //从后端获取的数据
        String patientID = request.getParameter("patientID");
        String reportStatus = "1";
        String createUserID0 = request.getParameter("createUserID");
        //在这里将李四转化为他的ID（重名了该怎么办呢？）
        ReportService re0 = (ReportService)ServiceFactory.getService(new ReportServiceImpl());
        String createUserID1 = re0.searchIdByNameFromTblUser(createUserID0);

        String auditorID = request.getParameter("auditorID");

        //从前端获取的数据

        String id = request.getParameter("id");
                //"125";
        String imagingFindings = request.getParameter("imagingFindings");
        String diagnosticOpinion = request.getParameter("diagnosticOpinion");
        String bodyPart = request.getParameter("bodyPart");
        String diseaseName = request.getParameter("diseaseName");
        String diseaseDescription = request.getParameter("diseaseDescription");

        //时间是自动生成的当前时间
        String createTime = DateTimeUtil.getSysTime();
        //从前端获取
        String positive = request.getParameter("positive");
        Report_wtt r = new Report_wtt();
        r.setId(id);
        r.setAuditorID(auditorID);
        r.setBodyPart(bodyPart);
        r.setReportStatus(reportStatus);
        r.setCreateTime(createTime);
        r.setBodyPart(bodyPart);
        r.setCreateUserID(createUserID1);
        r.setDiagnosticOpinion(diagnosticOpinion);
        r.setStudyID(studyID);
        r.setPatientID(patientID);
        r.setImagingFindings(imagingFindings);
        r.setDiseaseName(diseaseName);
        r.setDiseaseDescription(diseaseDescription);
        r.setPositive(positive);

        System.out.println(r.toString());
        ReportService re = (ReportService)ServiceFactory.getService(new ReportServiceImpl());

        boolean flag = false;
        System.out.println(flag1);
        if(flag1==false)
         flag = re.save(r);
        else if(flag1)
            flag = re.update(r);

        if(flag==true)
        {
            //用检查号去遍历study_info表，将这条数据的状态更改为
            ReportService reportService = (ReportService) ServiceFactory.getService(new ReportServiceImpl());
            reportService.update_status(studyID);
        }

        PrintJson.printJsonFlag(response,flag);

    }

}


