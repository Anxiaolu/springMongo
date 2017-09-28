<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>js网页日期插件</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/laydate.js"></script>
        <style type="text/css">
        </style>
    </head>
    <body>
        <div class="box">
            <div>
                <input placeholder="请输入日期" class="laydate-icon" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
            </div>
        </div>

        <script type="text/javascript">
            !function () {
                laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
                laydate({elem: '#demo'});//绑定元素
            }();

        //日期范围限制	
            var start = {
                elem: '#start',
                format: 'YYYY-MM-DD',
                min: laydate.now(), //设定最小日期为当前日期
                max: '2099-06-16', //最大日期
                istime: true,
                istoday: false,
                choose: function (datas) {
                    end.min = datas; //开始日选好后，重置结束日的最小日期
                    end.start = datas //将结束日的初始值设定为开始日
                }
            };

            var end = {
                elem: '#end',
                format: 'YYYY-MM-DD',
                min: laydate.now(),
                max: '2099-06-16',
                istime: true,
                istoday: false,
                choose: function (datas) {
                    start.max = datas; //结束日选好后，充值开始日的最大日期
                }
            };
            laydate(start);
            laydate(end);

        //自定义日期格式
            laydate({
                elem: '#test1',
                format: 'YYYY年MM月DD日',
                festival: true, //显示节日
                choose: function (datas) { //选择日期完毕的回调
                    alert('得到：' + datas);
                }
            });

        //日期范围限定在昨天到明天
            laydate({
                elem: '#hello3',
                min: laydate.now(-1), //-1代表昨天，-2代表前天，以此类推
                max: laydate.now(+1) //+1代表明天，+2代表后天，以此类推
            });
        </script>

    </body>
</html>