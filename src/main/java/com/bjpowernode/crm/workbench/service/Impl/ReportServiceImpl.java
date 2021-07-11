package com.bjpowernode.crm.workbench.service.Impl;

import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.vo.PaginationVO;
import com.bjpowernode.crm.workbench.dao.ReportDao;
import com.bjpowernode.crm.workbench.dao.Study_infoDao;
import com.bjpowernode.crm.workbench.domain.Patient;
import com.bjpowernode.crm.workbench.domain.Report_wtt;
import com.bjpowernode.crm.workbench.domain.Study_info;
import com.bjpowernode.crm.workbench.service.ReportService;

import java.util.List;
import java.util.Map;

public class ReportServiceImpl implements ReportService {

   //③把Dao层的impl删除，全部直接用Dao层访问数据库，改变之前有用DaoImpl的，有直接访问的
   // private ReportDao reportDao = new ReportDaoImpl();
   private ReportDao reportDao = SqlSessionUtil.getSqlSession().getMapper(ReportDao.class);

   private Study_infoDao study_infoDao = SqlSessionUtil.getSqlSession().getMapper(Study_infoDao.class);

   public Report_wtt getById(String id) {

      Report_wtt r=reportDao.getById(id);
      return r;
   }

   public boolean save(Report_wtt r) {

      boolean flag = true;

      int count = reportDao.save(r);

      if(count!=1){
         flag=false;
      }
      System.out.println("flag:"+flag);

      return flag;
   }

   public PaginationVO<Study_info> pageList(Map<String, Object> map) {

      //取得total,可能会有条件
      int total = study_infoDao.getTotalByCondition(map);
      System.out.println("total:"+total);

      //取得dataList，可能会有条件
      List<Study_info> dataList = study_infoDao.getActivityListByCondition(map);
      //dataList.set("等待填写",);
      System.out.println("dataList结果:");
      for(int i=0;i< dataList.size();i++)
         System.out.println(dataList.get(i));
      //创建一个vo对象，将total和dataList封装到vo中
      PaginationVO<Study_info> vo = new PaginationVO<Study_info>();
      vo.setTotal(total);
      vo.setDataList(dataList);
      System.out.println("vo值：");
      System.out.println(vo.toString());

      //将vo返回
      return vo;
   }

   public PaginationVO<Report_wtt> getdata(Map<String, Object> map) {


      //取得dataList，可能会有条件
      //List<Report_wtt> dataList = study_infoDao.getActivityListByCondition(map);
      //dataList.set("等待填写",);

      //自己想办法取得数据
      //与其这样写，还不如一个一个数据传过来，然后再在这里封装呢。。。
      List<Report_wtt> part_data = reportDao.getPart(map);
      System.out.println(part_data);


      //创建一个vo对象，将portial_data封装到vo中
      PaginationVO<Report_wtt> vo = new PaginationVO<Report_wtt>();
      vo.setDataList(part_data);
      System.out.println("vo值：");
      System.out.println(vo.toString());

      //将vo返回
      return vo;
   }

   public void update_status(String studyID) {
      study_infoDao.update_status(studyID);
   }

   public PaginationVO<Report_wtt> pageList_review(Map<String, Object> map) {


      //取得total,可能会有条件
      int total = reportDao.getTotalByCondition(map);
      System.out.println("total:"+total);

      //取得dataList，可能会有条件
      List<Report_wtt> dataList = reportDao.getActivityListByCondition(map);
      //dataList.set("等待填写",);
      System.out.println("dataList结果:");
      for(int i=0;i< dataList.size();i++)
         System.out.println(dataList.get(i));
      //创建一个vo对象，将total和dataList封装到vo中
      PaginationVO<Report_wtt> vo = new PaginationVO<Report_wtt>();
      vo.setTotal(total);
      vo.setDataList(dataList);
      System.out.println("vo值：");
      System.out.println(vo.toString());
      //将vo返回
      return vo;

   }

   public Report_wtt get_report(String id) {

      Report_wtt re = reportDao.get_report(id);
      return re;

   }

   public boolean pass(String id) {

      int count=0;
      boolean flag =  reportDao.pass(id);

      System.out.println("flag:"+flag);

      return flag;

   }

   public boolean reject(String id) {
      boolean flag =  reportDao.reject(id);

      System.out.println("flag:"+flag);

      return flag;
   }

   public boolean update_status2(String id) {
      boolean flag;
      flag = study_infoDao.update_status2(id);
      //System.out.println("flag:"+flag);
      return flag;
   }

   public boolean update_status3(String id_info) {
      boolean flag;
      flag = study_infoDao.update_status3(id_info);
      //System.out.println("flag:"+flag);
      return flag;
   }

   public Patient get_age_gender(String patientID) {
      Patient pa=reportDao.get_age_gender(patientID);
      return pa;
   }

   public Study_info get_data_from_studyInfo(String id) {
      Study_info st=reportDao.get_data_from_studyInfo(id);
      return st;
   }

   public boolean update(Report_wtt r) {
      boolean flag;
      flag = reportDao.update(r);
      //System.out.println("flag:"+flag);
      return flag;
   }

   public String searchIdByNameFromTblUser(String createUserID0) {

      String createUserID1 = reportDao.searchIdByNameFromTblUser(createUserID0);
      return createUserID1;
   }

   public Patient getPatientName(String patientID) {
      Patient p  = reportDao.getPatientName(patientID);
      return p;
   }

   public String getDiseaseName(String diseaseName) {
      String diseaseName1 = reportDao.getDiseaseName(diseaseName);
      return diseaseName1;
   }

   public boolean updateAuditorID(String id, String auditorID) {
      boolean flag;
      flag  = reportDao.updateAuditorID(id,auditorID);
      return flag;
   }

   public String getPatientID(String studyID) {
      String PatientID = reportDao.getPatientID(studyID);
      return PatientID;
   }

}

