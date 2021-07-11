package com.bjpowernode.crm.workbench.domain;

public class StudyInfo {

     private String accessionNumber;//检查号
     private String status;//检查状态
     private String patientID;//病人号
     private String department;//科室
     private String emergency;//是否急诊
     private String clinicianID;//临床医生工号
     private String registrarID;//登记员工号
     private String technicianID;//技师工号
     private String bodyParts;//检查位置
     private String modality;//模式（类似于检查设备，但又不是具体的检查设备，可以理解为检查设备的类型）
     private String studyDevice;//检查设备
     private String studyInstanceUID;//检查UID
     private String requestedProcedureDescription;//请求过程描述
     private String scheduledProcedureStepStartDate;//预约日期
     private String scheduledProcedureStepStartTime;//预约时间
     private String scheduledProcedureStepDescription;//计划的程序步骤的相关描述
     private String scheduledProcedureStepID;//计划的程序步骤相关ID
     private String requestedProcedureID;//必要的程序ID
     private String projection;//投照方式
     private String cancellationReason;//取消原因
     private String cancellationTime;//取消时间
     private String cancellationUser;//取消人员
     private String useConsumables;//使用耗材

     private String name;//多表查询时代表的病人名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Study_info{" +
                "accessionNumber='" + accessionNumber + '\'' +
                ", status='" + status + '\'' +
                ", patientID='" + patientID + '\'' +
                ", department='" + department + '\'' +
                ", emergency='" + emergency + '\'' +
                ", clinicianID='" + clinicianID + '\'' +
                ", registrarID='" + registrarID + '\'' +
                ", technicianID='" + technicianID + '\'' +
                ", bodyParts='" + bodyParts + '\'' +
                ", modality='" + modality + '\'' +
                ", studyDevice='" + studyDevice + '\'' +
                ", studyInstanceUID='" + studyInstanceUID + '\'' +
                ", requestedProcedureDescription='" + requestedProcedureDescription + '\'' +
                ", scheduledProcedureStepStartDate='" + scheduledProcedureStepStartDate + '\'' +
                ", scheduledProcedureStepStartTime='" + scheduledProcedureStepStartTime + '\'' +
                ", scheduledProcedureStepDescription='" + scheduledProcedureStepDescription + '\'' +
                ", scheduledProcedureStepID='" + scheduledProcedureStepID + '\'' +
                ", requestedProcedureID='" + requestedProcedureID + '\'' +
                ", projection='" + projection + '\'' +
                ", cancellationReason='" + cancellationReason + '\'' +
                ", cancellationTime='" + cancellationTime + '\'' +
                ", cancellationUser='" + cancellationUser + '\'' +
                ", useConsumables='" + useConsumables + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getClinicianID() {
        return clinicianID;
    }

    public void setClinicianID(String clinicianID) {
        this.clinicianID = clinicianID;
    }

    public String getRegistrarID() {
        return registrarID;
    }

    public void setRegistrarID(String registrarID) {
        this.registrarID = registrarID;
    }

    public String getTechnicianID() {
        return technicianID;
    }

    public void setTechnicianID(String technicianID) {
        this.technicianID = technicianID;
    }

    public String getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(String bodyParts) {
        this.bodyParts = bodyParts;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getStudyDevice() {
        return studyDevice;
    }

    public void setStudyDevice(String studyDevice) {
        this.studyDevice = studyDevice;
    }

    public String getStudyInstanceUID() {
        return studyInstanceUID;
    }

    public void setStudyInstanceUID(String studyInstanceUID) {
        this.studyInstanceUID = studyInstanceUID;
    }

    public String getRequestedProcedureDescription() {
        return requestedProcedureDescription;
    }

    public void setRequestedProcedureDescription(String requestedProcedureDescription) {
        this.requestedProcedureDescription = requestedProcedureDescription;
    }

    public String getScheduledProcedureStepStartDate() {
        return scheduledProcedureStepStartDate;
    }

    public void setScheduledProcedureStepStartDate(String scheduledProcedureStepStartDate) {
        this.scheduledProcedureStepStartDate = scheduledProcedureStepStartDate;
    }

    public String getScheduledProcedureStepStartTime() {
        return scheduledProcedureStepStartTime;
    }

    public void setScheduledProcedureStepStartTime(String scheduledProcedureStepStartTime) {
        this.scheduledProcedureStepStartTime = scheduledProcedureStepStartTime;
    }

    public String getScheduledProcedureStepDescription() {
        return scheduledProcedureStepDescription;
    }

    public void setScheduledProcedureStepDescription(String scheduledProcedureStepDescription) {
        this.scheduledProcedureStepDescription = scheduledProcedureStepDescription;
    }

    public String getScheduledProcedureStepID() {
        return scheduledProcedureStepID;
    }

    public void setScheduledProcedureStepID(String scheduledProcedureStepID) {
        this.scheduledProcedureStepID = scheduledProcedureStepID;
    }

    public String getRequestedProcedureID() {
        return requestedProcedureID;
    }

    public void setRequestedProcedureID(String requestedProcedureID) {
        this.requestedProcedureID = requestedProcedureID;
    }

    public String getProjection() {
        return projection;
    }

    public void setProjection(String projection) {
        this.projection = projection;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public String getCancellationTime() {
        return cancellationTime;
    }

    public void setCancellationTime(String cancellationTime) {
        this.cancellationTime = cancellationTime;
    }

    public String getCancellationUser() {
        return cancellationUser;
    }

    public void setCancellationUser(String cancellationUser) {
        this.cancellationUser = cancellationUser;
    }

    public String getUseConsumables() {
        return useConsumables;
    }

    public void setUseConsumables(String useConsumables) {
        this.useConsumables = useConsumables;
    }
}
