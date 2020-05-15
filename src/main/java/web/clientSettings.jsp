<%@ page import="backend.server.EJB.dataSettings.data.ClientData" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Settings</title>
    <link rel="stylesheet" type="text/css" href="CSS/general.css">
    <script src="JQuery/jquery-3.4.1.min.js"></script>
    <script src="JS/settingsAdministrator.js"></script>
</head>
<body>
<script>
    $(document).ready(function() {
        updateOnWeb();
    });
</script>
<%ClientData clientData = (ClientData) request.getSession(true).getAttribute(ClientData.class.getSimpleName());%>
<h1 class="header">Bill Data Generator</h1>
<br>
<nav>
    <a href="settings.jsp"><button id="general">General Settings</button></a>
    <a href="billSettings.jsp"><button id="bill">Bill Settings</button></a>
    <a href="clientSettings.jsp"><button id="client">Client Settings</button></a>
    <a href="restaurantSettings.jsp"><button id="restaurant">Restaurant Settings</button></a>
    <a href="providerSettings.jsp"><button id="provider">Provider Settings</button></a>
</nav>
<br>
<h2>Client Settings</h2>
<div>
    <form method="post" action="FrontControllerServlet">
        <div>
            <input type="hidden" name="command" value="SaveSettingsCommand">
            <input type="submit"  value="Save">
        </div>
    </form>
    <form method="post" action="FrontControllerServlet">
        <input type="hidden" name="command" value="CancelCommand">
        <input type="submit"  value="Cancel">
    </form>
</div>
</body>
<footer>
    <div class="footer">
        <div class="container">Author: Juan Alberto Ureña Rodriguez</div>
    </div>
</footer>
</html>
