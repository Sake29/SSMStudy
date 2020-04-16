<%--
  Created by IntelliJ IDEA.
  User: WSY
  Date: 2020/3/8
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--
<form action="/param/saveAccount" method="post">



    ------把数据封装到Account类中------
     <!- 此处name的值必须和你的实体类的属性相对应 -->
    <!-- 才能帮你调用该实体类的set方法进行赋值
    姓名：<input type="text" name="username"/><br>
    密码：<input type="password" name="password"/><br>
    金额：<input type="text" name="money"/><br>
    用户姓名：<input type="text" name="user.uname"/><br>
    用户年龄：<input type="text"  name="user.age"/><br>

    <input type="submit" value="提交"/>
</form>
-->
<!-- 把数据封装到Account类中,类中存在list和map集合 -->
<form action="/param/saveAccount" method="post">
    姓名：<input type="text" name="username"/><br>
    密码：<input type="password" name="password"/><br>
    金额：<input type="text" name="money"/><br>

    用户姓名：<input type="text" name="list[0].uname"/><br>
    用户年龄：<input type="text"  name="list[0].age"/><br>

    用户姓名：<input type="text" name="map['one'].uname"/><br>
    用户年龄：<input type="text"  name="map['one'].age"/><br>

    <input type="submit" value="提交"/>


</form>

<!--获取servlet-->
<a href="/param/getServlet">获取servlet</a>
</body>
</html>
