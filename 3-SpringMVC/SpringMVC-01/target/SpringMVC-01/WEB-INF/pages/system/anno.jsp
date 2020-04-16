<%--
  Created by IntelliJ IDEA.
  User: WSY
  Date: 2020/3/9
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- 常用的注解 -->
<a href="/anno/requestParam?name=哈哈">requestParam注解</a><br>
<a href="/anno/pathVariable/10">pathVariable注解</a><br>
<a href="/anno/requestHeader">requestHeader注解</a><br>
<a href="/anno/cookievalue">cookievalue注解</a><br>
<a href="/anno/sessionAttributes">sessionAttributes注解</a>
<a href="/anno/getSessionAttributes">从session中获取值</a>
<a href="/anno/delSessionAttributes">从session中删除值</a>
<br>
<hr>
<form action="/anno/requestBody" method="post">
    requestBody注解<br>
    用户姓名：<input type="text" name="username"><br>
    用户年龄：<input type="text" name="age"><br>
    <input type="submit" value="提交">
</form>
<br>
<form action="/anno/modelAttribute" method="post">
    modelAttribute注解<br>
    用户姓名：<input type="text" name="uname"><br>
    用户年龄：<input type="text" name="age"><br>
    <input type="submit" value="提交">
</form>

</body>
</html>
