<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">

    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

    <script type="text/javascript" src="jquery/bs_typeahead/bootstrap3-typeahead.min.js"></script>


<script>
    $(function () {
        $("#create-patientname").typeahead({
            source: function (query, process) {
                $.get(
                    "workbench/register/getPatientNameList.do",
                    { "name" : query },
                    function (data) {
                        //alert(data);
                        process(data);
                    },
                    "json"
                );
            },
            afterSelect: function (data) {
                //选择项之后的事件 ，data是当前选中的。
                $.post(
                    "workbench/register/getPatientByName.do",
                    {"name":data},
                    function(data){
                        $("#create-patientnamePinYin").val(data.namePinYin);
                        $("#create-patientType").val(data.patientType);
                        $("#create-inpatientNumber").val(data.inpatientNumber);
                        $("#create-inpatientDepartment").val(data.inpatientDepartment);
                        $("#create-inpatientBedNumber").val(data.inpatientBedNumber);
                        $("#create-ageType").val(data.ageType);
                        $("#create-age").val(data.age);
                        $("#create-birthDate").val(data.birthDate);
                        $("#create-address").val(data.address);
                        $("#create-gender").val(data.gender);
                        $("#create-pregnancy").val(data.pregnancy);
                        $("#create-IDType").val(data.idtype);//傻逼idea怎么把IDType字段改成了idtype，我透李奶奶的
                        $("#create-IDNumber").val(data.idnumber);
                        $("#create-phoneNumber").val(data.phoneNumber);
                        $("#create-healthCareType").val(data.healthCareType);
                    },
                    "json"
                )
            },
            delay: 1500
        });
        $(".time1").datetimepicker({
            minView: "month",
            language:  'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayBtn: true,
            pickerPosition: "top-left"
        });
        //重要信息不填则保存按钮失效（姓名）
        //为保存按钮绑定事件，执行交易添加操作
        $("#saveBtn").click(function () {
            //发出传统请求，提交表单
            $("#registerForm").submit();
        })
    })
</script>

</head>
<body>

<div style="position:  relative; left: 30px;">
    <h3>创建登记信息</h3>
    <div style="position: relative; top: -40px; left: 70%;">
        <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
        <button type="button" class="btn btn-default" onclick="window.location.href='workbench/register/index.jsp';">取消</button>
    </div>
    <hr style="position: relative; top: -40px;">
</div>
<form action="workbench/register/save.do" id="registerForm" method="post" class="form-horizontal" role="form" style="position: relative; top: -30px;">

    <div class="form-group">
        <label for="create-registrarID" class="col-sm-2 control-label">登记员</label>
        <div class="col-sm-10" style="width: 300px;">
            <input class="form-control" id="create-registrarname" name="registrarname" value="${user.name}" disabled>
        </div>
        <div class="col-sm-10" style="width: 300px;">
            <input type="hidden" class="form-control" id="create-registrarID" name="registrarID" value="${user.id}">
        </div>
    </div>

    <div style="position:  relative; left: 30px;">
        <h4>创建病人信息</h4>
        <hr style="position: relative; top: -40px;">
    </div>

    <div class="form-group">
        <label for="create-patientname" class="col-sm-2 control-label">病人姓名<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" autocomplete="off" id="create-patientname" name="patientname" placeholder="支持自动补全(不存在则新建)">
        </div>
        <label for="create-patientnamePinYin" class="col-sm-2 control-label">姓名拼音<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-patientnamePinYin" name="patientnamePinYin">
        </div>
    </div>

    <div class="form-group">
        <label for="create-IDType" class="col-sm-2 control-label">证件类型<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <select class="form-control" id="create-IDType" name="IDType">
                <option></option>
                <option>身份证</option>
                <option>护照</option>
                <option>港澳通行证</option>
            </select>
        </div>
        <label for="create-IDNumber" class="col-sm-2 control-label">证件号<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-IDNumber" name="IDNumber">
        </div>
    </div>

    <div class="form-group">
        <label for="create-inpatientDepartment" class="col-sm-2 control-label">住院科室<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-inpatientDepartment" name="inpatientDepartment">
        </div>
        <label for="create-patientType" class="col-sm-2 control-label">病人类型<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <select class="form-control" id="create-patientType" name="patientType">
                <option></option>
                <option>急诊</option>
                <option>门诊</option>
                <option>住院</option>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label for="create-inpatientNumber" class="col-sm-2 control-label">住院号</label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-inpatientNumber" name="inpatientNumber">
        </div>
        <label for="create-inpatientBedNumber" class="col-sm-2 control-label">住院床号</label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-inpatientBedNumber" name="inpatientBedNumber">
        </div>
    </div>

    <div class="form-group">
        <label for="create-ageType" class="col-sm-2 control-label">年龄类型<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <select class="form-control" id="create-ageType" name="ageType">
                <option></option>
                <option>年</option>
                <option>月</option>
                <option>日</option>
            </select>
        </div>
        <label for="create-healthCareType" class="col-sm-2 control-label">医保类型<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <select class="form-control" id="create-healthCareType" name="healthCareType">
                <option></option>
                <option>城镇职工医疗保险</option>
                <option>城乡居民医疗保险</option>
                <option>无</option>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label for="create-age" class="col-sm-2 control-label">年龄<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-age" name="age">
        </div>
        <label for="create-birthDate" class="col-sm-2 control-label">出生日期</label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control time1" autocomplete="off" id="create-birthDate" name="birthDate">
        </div>
    </div>

    <div class="form-group">
        <label for="create-gender" class="col-sm-2 control-label">性别<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <select class="form-control" id="create-gender" name="gender">
                <option></option>
                <option>M</option>
                <option>F</option>
            </select>
        </div>
        <label for="create-pregnancy" class="col-sm-2 control-label">怀孕情况<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <select class="form-control" id="create-pregnancy" name="pregnancy">
                <option></option>
                <option>是</option>
                <option>否</option>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label for="create-phoneNumber" class="col-sm-2 control-label">手机号<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-phoneNumber" name="phoneNumber">
        </div>
        <label for="create-address" class="col-sm-2 control-label">地址</label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-address" name="address">
        </div>
    </div>

    <div style="position:  relative; left: 30px;">
        <h4>创建检查信息</h4>
        <hr style="position: relative; top: -40px;">
    </div>

    <div class="form-group">
        <label for="create-emergency" class="col-sm-2 control-label">是否急诊<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <select class="form-control" id="create-emergency" name="emergency">
                <option></option>
                <option>是</option>
                <option>否</option>
            </select>
        </div>
        <label for="create-department" class="col-sm-2 control-label">检查科室<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-department" name="department">
        </div>
    </div>

    <div class="form-group">
        <label for="create-clinicianID" class="col-sm-2 control-label">临床医生工号<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-clinicianID" name="clinicianID">
        </div>
        <label for="create-bodyParts" class="col-sm-2 control-label">检查位置<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <select class="form-control" id="create-bodyParts" name="bodyParts">
                <option></option>
                <c:forEach items="${bodyPartsList}" var="t">
                    <option value="${t.value}">${t.text}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label for="create-modality" class="col-sm-2 control-label">模式(检查设备类型)<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <select class="form-control" id="create-modality" name="modality">
                <option></option>
                <c:forEach items="${modalityList}" var="t">
                    <option value="${t.value}">${t.text}</option>
                </c:forEach>
            </select>
        </div>
        <label for="create-studyDevice" class="col-sm-2 control-label">检查设备<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <select class="form-control" id="create-studyDevice" name="studyDevice">
                <option></option>
                <c:forEach items="${deviceList}" var="t">
                    <option value="${t.id}">${t.name}(${t.room})</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label for="create-requestedProcedureDescription" class="col-sm-2 control-label">请求过程描述</label>
        <div class="col-sm-10" style="width: 64%;">
            <textarea class="form-control" rows="5" id="create-requestedProcedureDescription" name="requestedProcedureDescription"></textarea>
        </div>
    </div>

</form>
</body>
</html>