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
            <form:form action="${ctx}/data/findPost" method="post">
                <div class="form-group">
                    <label for="company">Company:</label>
                    <input type="text" class="form-control" id="company" name="company" placeholder="Enter Company:"/>
                    <div class="popdiv">
                        <table id="content_table_1" >
                            <tbody id="content_table_body_1" class="content_table">

                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="form-group">
                    <label for="league">League:</label>
                    <input type="text" class="form-control" id="league" name="league" placeholder="Enter League:"/>
                    <div class="popdiv">
                        <table id="content_table_2" >
                            <tbody id="content_table_body_2" class="content_table">

                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="form-group">
                    <label for="year">Year:</label>
                    <input type="text" class="form-control" id="year" name="year" placeholder="Enter Year:"/>
                    <div class="popdiv">
                        <table id="content_table_3" >
                            <tbody id="content_table_body_3" class="content_table">

                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="form-group">
                    <label for="match">Match:</label>
                    <input type="text" class="form-control" id="match" name="match" placeholder="Enter Match:"/>
                    <div class="popdiv">
                        <table id="content_table_4" >
                            <tbody id="content_table_body_4" class="content_table">

                            </tbody>
                        </table>
                    </div>
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
        <!--数据加载函数调用-->
        <!-- <script src="${pageContext.request.contextPath}/assets/js/script.js"></script> -->
        <script type="text/javascript">
            $(document).ready(function () {
                loadAllData();
            });

            function cleardata() {
                var companytable = document.getElementById("content_table_body_1");
                var leaguetable = document.getElementById("content_table_body_2");
                var yeartable = document.getElementById("content_table_body_3");
                var matchtable = document.getElementById("content_table_body_4");
                for (var i = $('#content_table_body_1').children().length - 1; i >= 0; i--) {
                    companytable.removeChild(companytable.childNodes[i]);
                }
                for (var i = $('#content_table_body_2').children().length - 1; i >= 0; i--) {
                    leaguetable.removeChild(leaguetable.childNodes[i]);
                }
                for (var i = $('#content_table_body_3').children().length - 1; i >= 0; i--) {
                    yeartable.removeChild(yeartable.childNodes[i]);
                }
                for (var i = $('#content_table_body_4').children().length - 1; i >= 0; i--) {
                    matchtable.removeChild(matchtable.childNodes[i]);
                }
            }

            /*function addtr(data, appendparent,content_table) {
             var tr = $("<tr/>");
             var td = $("<td/>");
             td.html(data).appendTo(tr);
             td.hover(function () {
             $(this).css({background: '#cdcdcd'});
             });
             td.mouseleave(function (event) {
             $(this).css({background: '#ffffff'});
             });
             td.click(function (event) {
             $(appendparent).val($(this).text());
             cleardata();
             $('#league').focus();
             });
             $(content_table).append(tr);
             }*/

            $("#company").bind('keyup foucus', function (event) {
                cleardata();
                var company = $(this).context.value;
                setTimeout(function () {
                    if ($('#company').val() === "") {
                        cleardata();
                    } else {
                        $.post('${pageContext.request.contextPath}/data/getlikecompany', {company_name: company}, function (data) {

                            $.each(data, function () {
                                /*var that = this;
                                 addtr(that,"#company","#content_table_body_1");*/
                                var tr = $("<tr/>");
                                var td = $("<td/>");
                                td.html(this).appendTo(tr);
                                td.hover(function () {
                                    $(this).css({background: '#cdcdcd'});
                                });
                                td.mouseleave(function (event) {
                                    $(this).css({background: '#ffffff'});
                                });
                                td.click(function (event) {
                                    $('#company').val($(this).text());
                                    cleardata();
                                    $('#league').focus();
                                });
                                $('#content_table_body_1').append(tr);
                            });
                        }, "json");
                    }
                }, 500);
            });

            $("#league").bind('focus', function (event) {
                cleardata();
                var company_name = $('#company').val();
                $.post('${pageContext.request.contextPath}/data/getdata', {company: company_name}, function (data) {
                    $.each(data, function () {
                        //console.log(this);
                        var tr = $("<tr/>");
                        var td = $("<td/>");
                        td.html(this.league).appendTo(tr);
                        td.hover(function () {
                            $(this).css({background: '#cdcdcd'});
                        });
                        td.mouseleave(function (event) {
                            $(this).css({background: '#ffffff'});
                        });
                        td.click(function (event) {
                            $('#league').val($(this).text());
                            cleardata();
                            $('#year').focus();
                        });
                        $('#content_table_body_2').append(tr);
                    });
                }, "json");
            });

            $('#year').bind('focus',function(event) {
                cleardata();
                var company_name = $('#company').val();
                var league_name  = $('#league').val();
                $.post('${pageContext.request.contextPath}/data/getdata', {company: company_name,league:league_name}, function(data) {
                    $.each(data,function() {
                        var tr = $("<tr/>");
                        var td = $("<td/>");
                        td.html(this.year).appendTo(tr);
                        td.hover(function () {
                            $(this).css({background: '#cdcdcd'});
                        });
                        td.mouseleave(function (event) {
                            $(this).css({background: '#ffffff'});
                        });
                        td.click(function (event) {
                            $('#year').val($(this).text());
                            cleardata();
                            $('#match').focus();
                        });
                        $('#content_table_body_3').append(tr);
                    });
                },"json");
            });

            $('#match').bind('focus',function(event) {
                cleardata();
                var company_name = $('#company').val();
                var league_name  = $('#league').val();
                var year         = $('#year').val();
                $.post('${pageContext.request.contextPath}/data/getdata', {company: company_name,league:league_name,year:year}, function(data) {
                    $.each(data,function() {
                        var tr = $("<tr/>");
                        var td = $("<td/>");
                        td.html(this.match).appendTo(tr);
                        td.hover(function () {
                            $(this).css({background: '#cdcdcd'});
                        });
                        td.mouseleave(function (event) {
                            $(this).css({background: '#ffffff'});
                        });
                        td.click(function (event) {
                            $('#match').val($(this).text());
                            cleardata();
                        });
                        $('#content_table_body_4').append(tr);
                    });
                },"json");
            });

            function loadAllData() {
                var i = 0;
                $.post("${pageContext.request.contextPath}/data/getalldata", null, function (data) {
                    $.each(data, function () {
                        var tr = $("<tr align='center'/>");
                        i += 1;
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
