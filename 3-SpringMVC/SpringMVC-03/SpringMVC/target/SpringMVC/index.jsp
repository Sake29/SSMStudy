<%--
  Created by IntelliJ IDEA.
  User: WSY
  Date: 2020/3/12
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>传统文件上传</h3>
<form action="/user/fileupload1" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/><br>
    <input type="submit" value="上传"/><br>
</form>
<hr>
<h3>Springmvc文件上传</h3>
<form action="/user/fileupload2" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/><br><!-- 此处的name必须与处理器方法中的MultipartFile对象名称相同 -->
    <input type="submit" value="上传"/><br>
</form>
<hr>
<h3>Springmvc跨服务器文件上传</h3>
<form action="/user/fileupload3" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/><br><!-- 此处的name必须与处理器方法中的MultipartFile对象名称相同 -->
    <input type="submit" value="上传"/><br>
</form>



</body>
</html>
