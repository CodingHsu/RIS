<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">

    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
    <link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
    <script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination/en.js"></script>

    <script type="text/javascript">

        function transfer(wtt) {
            var str=wtt.split(',');
            const db = window.localStorage;
            db.setItem("id", str[0]);
            db.setItem("patientID",str[2]);
            db.setItem("studyID",str[1]);
            //跳转
            window.location.href = "workbench/report_review/report_review.jsp";
        }

        function pageList(pageNo, pageSize) {
            //将全选的复选框的√干掉,复选框失败了
            $("#qx").prop("checked", false);

            var html = "";
            $.ajax({
                url: "workbench/Report_wtt/pageList_review.do",
                data: {
                    "pageNo": pageNo,
                    "pageSize": pageSize,
                    // 有时间可能会做查询。
                    "id": $.trim($("#id").val()),//报告号
                    "studyID": $.trim($("#accessionNumber").val()),//检查号
                    "patientID": $.trim($("#patientID").val()),//病人号
                    "reportStatus": $.trim($("#status").val())//报告状态
                },
                type: "get",
                dataType: "json",
                success: function (data) {
                    $.each(data.dataList, function (i, wtt) {

                            //alert("显示status为6的数据");
                        const wait01 = "等待审核";
                        const wait02 = "审核完成";
                        const wait03 = "等待修改";
                        const wait04 = "已锁定";
                        html += "<tr class=\"active\">";
                        html += "<td><input type=\"checkbox\" name=\"xz\" value=\"" + wtt.id + "\"/></td>";                                                                                                      /* \';">*/
                        html += "<td><a id='id' style=\"text-decoration: none; cursor: pointer;\" onclick=\"transfer(";
                        var temp = "\'"+wtt.id+","+wtt.studyID+","+wtt.patientID+"\'";
                        html += temp + ")\">" + wtt.id + "</a></td>";
                        html += "<td id='studyID'>" + wtt.studyID + "</td>";
                        html += "<td id='patientID'>" + wtt.patientID + "</td>";
                        if (wtt.reportStatus == "1") {
                            html += "<td>" + wait01 + "</td>";
                        }
                        else if(wtt.reportStatus == "3") {
                            html += "<td>" + wait02 + "</td>";
                        }
                        else if(wtt.reportStatus == "2") {
                            html += "<td>" + wait03 + "</td>";
                        }
                        else if(wtt.reportStatus == "4") {
                            html += "<td>" + wait04 + "</td>";
                        }
                            html += "</tr>";

                    })

                    $("#report_body").html(html);

                    //计算总页数
                    var totalPages = data.total % pageSize == 0 ? data.total / pageSize : parseInt(data.total / pageSize) + 1;
                    //数据处理完毕后，结合分页查询，对前端展现分页信息
                    $("#ReportPage").bs_pagination({
                        currentPage: pageNo, // 页码
                        rowsPerPage: pageSize, // 每页显示的记录条数
                        maxRowsPerPage: 20, // 每页最多显示的记录条数
                        totalPages: totalPages, // 总页数
                        totalRows: data.total, // 总记录条数
                        visiblePageLinks: 3, // 显示几个卡片
                        showGoToPage: true,
                        showRowsPerPage: true,
                        showRowsInfo: true,
                        showRowsDefaultInfo: true,

                        //该回调函数是在，点击分页组件的时候触发的
                        onChangePage: function (event, data) {
                            pageList(data.currentPage, data.rowsPerPage);
                        }
                    });

                }
            })
        }

        $(document).ready(function () {
            //加载报告数据
            pageList(1, 2);
        });

        $(function(){

            $("#qx").click(function () {

                $("input[name=xz]").prop("checked",this.checked);

            })
            $("#report_body").on("click",$("input[name=xz]"),function () {

                $("#qx").prop("checked",$("input[name=xz]").length==$("input[name=xz]:checked").length);

            })

        });

    </script>
</head>
<body>
<div>
    <div style="position: relative; left: 10px; top: -10px;">
        <div class="page-header">
            <h3>待审核报告列表</h3>
        </div>
    </div>
</div>

<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">

    <div style="width: 100%; position: absolute;top: 5px; left: 10px;">

        <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 10px;">
            <div class="btn-group" style="position: relative; top: 18%;">
                <button type="button" class="btn btn-primary" onclick="window.location.href='report_edit.html';"><span class="glyphicon glyphicon-plus"></span>打印</button>
            </div>
        </div>
        <div style="position: relative;top: 10px;">
            <table class="table table-hover">
                <thead>
                <tr style="color: #B3B3B3;">
                    <td><input type="checkbox" id="qx"/></td>
                    <td>报告号</td>
                    <td>检查号</td>
                    <td>病人号</td>
                    <td>报告状态</td>
                    <!--报告状态包括：待填写和待修改；待审核和审核状态显示在报告审批界面-->
                </tr>
                </thead>
                <tbody id="report_body">

                </tbody>
            </table>
        </div>

        <div style="height: 50px; position: relative;top: 20px;">

                <div id = "ReportPage"></div>

        </div>

    </div>

</div>
</body>
</html>