<%--
  Created by IntelliJ IDEA.
  User: PROPIETARIO
  Date: 25/03/2020
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Settings</title>
</head>
<body>
    <form method="post" action="FrontControllerServlet">
        <label>
            <input type="text" name="clientRowNumber">
        </label>
        <input type="hidden" name="command" value="SaveSettingsCommand">
        <input type="submit"  value="Save">
    </form>
    <form method="post" action="FrontControllerServlet">
        <input type="hidden" name="command" value="CancelCommand">
        <input type="submit"  value="Cancel">
    </form>
</body>
</html>
