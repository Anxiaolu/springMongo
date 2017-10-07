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
        <link rel="stylesheet" href="${ctx}/assets/bootstrap/css/bootstrap.min.css">
        <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
        <script src="${ctx}/assets/js/jquery-1.11.1.min.js"></script>
        <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
        <script src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
    
</style>
    </head>
    <body>
        <div class="container">
            <h1>Data管理</h1>
            <hr/>
            <form:form action="${ctx}/data/download" method="post">
                <div class="form-group">
                    <label for="match">Match:</label>
                    <input type="text" class="form-control" id="match" name="match" placeholder="Enter Match:"/>
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
                    <label for="company">Company:</label>
                    <input type="text" class="form-control" id="company" name="company" placeholder="Enter Company:"/>
                    <div class="popdiv">
                        <table id="content_table_4" >
                            <tbody id="content_table_body_4" class="content_table">

                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="box">
                    <h4>开始时间</h4>
                    <input type="datetime-local" id="starttime" name="starttime"/>
                </div>
                <div class="box">
                    <h4>结束时间</h4>
                    <input type="datetime-local" id="endtime" name="endtime"/>
                </div>
                <div class="box">
                    <h4>下载记录数</h4>
                    <input type="text" id="num" name="datanum"/>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-sm btn-success" id="submit">下载查询结果</button>
                </div>
            </form:form>

            <div>
              <!-- Nav tabs -->
              <ul class="nav nav-tabs" role="tablist" id="nav-tabs">
                
              </ul>

              <!-- Tab panes -->
              <div class="tab-content" id="tab-content">
                
              </div>

            </div>
        </div>
        <!--数据加载函数调用-->
        <script src="../assets/js/script.js"></script>
        <script type="text/javascript">
            $('#myTabs a').click(function (e) {
              e.preventDefault()
              $(this).tab('show')
            })
        </script>
    </body>
</html>