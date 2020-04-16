<%--
  Created by IntelliJ IDEA.
  User: WSY
  Date: 2020/3/10
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.1.1.min.js"></script>
    <script>
        //页面加载，绑定单击事件
        $(function () {
            $("#btn").click(function () {
                //alert("hello btn");
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"user/testAjax",
                    contentType:"application/json;charset=utf-8",
                    data:'{"username":"hehe","password":"123","age":30}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        //data 服务器端响应的json数据，进行解析
                        alert(data);
                        alert(data.username);
                        alert(data.password);
                        alert(data.age);
                    }
                });
            })
        })
    </script>
</head>
<body>
<a href="user/testString">返回String类型</a><br>
<a href="user/testVoid">返回void类型</a><br>
<a href="user/testModelAndView">返回ModelAndView类型</a><br>
<a href="user/testForwardOrRedirect">ForwardOrRedirect</a><br>

<button id="btn">发送ajax请求</button>
</body>
</html>
