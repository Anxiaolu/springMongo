<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>Data List</title>

        <!-- 新 Bootstrap 核心 CSS 文件 -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
        <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
        <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<!--        <script src="${pageContext.request.contextPath}/assets/boostrap/js/bootstrap.min.js"></script>-->

    </head>
    <body>
        <div class="container">
            <h1>Data管理</h1>
            <hr/>
            <form:form action="{ctx}/findPost" method="post">
                <div class="form-group">
                    <label for="Company">Company:</label>
                    <input type="text" class="form-control" id="Company" name="Company" placeholder="Enter Company:"/>
                </div>
                <div class="form-group">
                    <label for="League">League:</label>
                    <input type="text" class="form-control" id="League" name="League" placeholder="Enter League:"/>
                </div>
                <div class="form-group">
                    <label for="Year">Year:</label>
                    <input type="text" class="form-control" id="Year" name="Year" placeholder="Enter Year:"/>
                </div>
                <div class="form-group">
                    <label for="Match">Match:</label>
                    <input type="text" class="form-control" id="Match" name="Match" placeholder="Enter Match:"/>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-sm btn-success" id="submit">Output</button>
                </div>
            </form:form>
            <!-- 如果用户列表非空 -->
                <table class="table table-bordered table-striped" id="dataList">
                    <tr>
                        <th>Order</th>
                        <th>Company</th>
                        <th>League</th>
                        <th>Year</th>
                        <th>Match</th>
                        <th>Options</th>
                    </tr>
                </table>
        </div>
        <script>
            $(document).ready(function(){
                loadAllData(    );
            });

            $('#submit').on('click', function(){
                loadData();
            });
            function loadAllData() {
                var i = 0;
                $.post("${ctx}/data/getalldata", {param:"sanic"}, function(data) {
                $.each(data, function(){
                    var tr = $("<tr align='center'/>");
                    i+=1;
                    $("<td/>").html(i).appendTo(tr);
                    $("<td/>").html(this.company).appendTo(tr);
                    $("<td/>").html(this.league).appendTo(tr);
                    $("<td/>").html(this.year).appendTo(tr);
                    $("<td/>").html(this.match).appendTo(tr);
                    $("#dataList").append(tr)
                    console.log(this)
                    })
                }, "json");
            }
            function loadData() {
                var i = 0;
                $.post("${pageContext.request.contextPath}/data/findPost", {param:"sanic"}, function(data) {
                $.each(data, function(){
                    var tr = $("<tr align='center'/>");
                    i+=1;
                    $("<td/>").html(i).appendTo(tr);
                    $("<td/>").html(this.company).appendTo(tr);
                    $("<td/>").html(this.league).appendTo(tr);
                    $("<td/>").html(this.year).appendTo(tr);
                    $("<td/>").html(this.match).appendTo(tr);
                    $("#dataList").append(tr)
                    })
                }, "json");
            }
        </script>
        
    </body>
</html>
