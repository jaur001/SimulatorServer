<%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 05/03/2020
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Restaurant Simulator</title>
  </head>
  <body>
  Welcome to my Simulator
  <form method="get" action="FrontServlet">
    <input type="hidden" name="command" value="StartCommand">
    <input type="submit"  name="Start">
  </form>
  </body>
</html>
