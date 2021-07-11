package com.bjpowernode.crm.workbench.domain;

public class Report_wtt {

//    由于类重名的原因，所以Report_wtt对应的是数据库中的tbl_report表
    private String id;//报告号
    private String studyID;//检查号
    private String patientID;//病人号
    private String reportStatus;//报告状态
    private String createUserID;//报告医生id
    private String auditorID;//审核医生id
    private String imagingFindings;//影像学所见
    private String diagnosticOpinion;//诊断意见
    private String bodyPart;//疾病部位
    private String diseaseName;//疾病名称
    private String diseaseDescription;//疾病描述
    private String createTime;//报告日期
    private String positive;//是否阳性

    public Report_wtt() {
    }

    public Report_wtt(String id, String studyID, String patientID, String reportStatus, String createUserID, String auditorID, String imagingFindings, String diagnosticOpinion, String bodyPart, String diseaseName, String diseaseDescription, String createTime, String positive) {
        this.id = id;
        this.studyID = studyID;
        this.patientID = patientID;
        this.reportStatus = reportStatus;
        this.createUserID = createUserID;
        this.auditorID = auditorID;
        this.imagingFindings = imagingFindings;
        this.diagnosticOpinion = diagnosticOpinion;
        this.bodyPart = bodyPart;
        this.diseaseName = diseaseName;
        this.diseaseDescription = diseaseDescription;
        this.createTime = createTime;
        this.positive = positive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudyID() {
        return studyID;
    }

    public void setStudyID(String studyID) {
        this.studyID = studyID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getCreateUserID() {
        return createUserID;
    }

    public void setCreateUserID(String createUserID) {
        this.createUserID = createUserID;
    }

    public String getAuditorID() {
        return auditorID;
    }

    public void setAuditorID(String auditorID) {
        this.auditorID = auditorID;
    }

    public String getImagingFindings() {
        return imagingFindings;
    }

    public void setImagingFindings(String imagingFindings) {
        this.imagingFindings = imagingFindings;
    }

    public String getDiagnosticOpinion() {
        return diagnosticOpinion;
    }

    public void setDiagnosticOpinion(String diagnosticOpinion) {
        this.diagnosticOpinion = diagnosticOpinion;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseDescription() {
        return diseaseDescription;
    }

    public void setDiseaseDescription(String diseaseDescription) {
        this.diseaseDescription = diseaseDescription;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String toString() {
        return "Report{" +
                "id='" + id + '\'' +
                ", studyID='" + studyID + '\'' +
                ", patientID=" + patientID +
                ", reportStatus=" + reportStatus +
                ", patientID=" + patientID +
                ", createUserID=" + createUserID +
                ", auditorID=" + auditorID +
                ", imagingFindings=" + imagingFindings +
                ", diagnosticOpinion=" + diagnosticOpinion +
                ", bodyPart=" + bodyPart +
                ", diseaseName=" + diseaseName +
                ", diseaseDescription=" + diseaseDescription +
                ", createTime=" + createTime +
                ", positive=" + positive +
                '}';
    }


}
