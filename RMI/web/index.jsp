<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/3/4
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.lang.*" %>
<%@page import="java.io.*" %>
<%@page import="cn.shyshetxwh.rmi.*" %>
<html>
<head>
    <title>RMI测试</title>
</head>
<body>
<%
    InputStream is = new FileInputStream("d:/TestClass.class");
    byte[] b = new byte[is.available()];
    is.read(b);
    is.close();

    //out.println("sssss");
    out.println("<textarea style='width:1000;height:800'>");
    out.println(JavaClassExecuter.execute(b));
    out.println("</textarea>");
%>


</body>
</html>
