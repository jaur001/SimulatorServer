<%@ page import="backend.server.EJB.dataSettings.data.ClientData" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Settings</title>
    <link rel="stylesheet" type="text/css" href="CSS/style.css">
    <script src="JQuery/jquery-3.4.1.min.js"></script>
    <script src="JS/clientSettingsAdministrator.js"></script>
</head>
<body>
    <%ClientData clientData = (ClientData) request.getSession(true).getAttribute(ClientData.class.getSimpleName());%>
    <h1 class="header">Bill Data Generator</h1>
    <br>
    <nav>
        <a href="settings.jsp"><button id="general">General Settings</button></a>
        <a href="clientSettings.jsp"><button id="client">Client Settings</button></a>
        <a href="restaurantSettings.jsp"><button id="restaurant">Restaurant Settings</button></a>
        <a href="providerSettings.jsp"><button id="provider">Provider Settings</button></a>
        <a href="serviceSettings.jsp"><button id="service">Service Settings</button></a>
        <a href="workerSettings.jsp"><button id="worker">Worker Settings</button></a>
    </nav>
    <br>
    <h2>Client Settings</h2>
    <div>
        <form method="post" action="FrontControllerServlet">
            <h3>Salary</h3>
            <div>
                <label>Mean</label><br>
                <label for="salaryMean"></label>
                <input type="text" id="salaryMean" value="<%=clientData.getSalaryMean()%>">
            </div>
            <div>
                <label>Standard Deviation</label><br>
                <label for="salarySd"></label>
                <input type="range" id="salarySd" min="1" max="100" value="<%=(clientData.getSalarySd()/clientData.getSalaryMean())*100.0%>">
                <label id="currentSalarySd"><%=(int)((clientData.getSalarySd()/clientData.getSalaryMean())*100.0) + "%"%></label>
            </div>
            <div>
                <label>Salary Min</label><br>
                <label for="minSalary"></label>
                <input type="text" id="minSalary" value="<%=clientData.getMinSalary()%>">
            </div>
            <div>
                <label>Invited People Min</label>
                <label for="invitedPeopleMin"></label>
                <input type="text" id="invitedPeopleMin" value="<%=clientData.getInvitedPeopleMin()%>">
                <label>Invited People Max</label>
                <label for="invitedPeopleMax"></label>
                <input type="text" id="invitedPeopleMax" value="<%=clientData.getInvitedPeopleMax()%>">
            </div>
            <div>
                <label>Number of Restaurant Min</label>
                <label for="numOfRestaurantMin"></label>
                <input type="text" id="numOfRestaurantMin" value="<%=clientData.getNumOfRestaurantMin()%>">
                <label>Number of Restaurant Max</label>
                <label for="numOfRestaurantMax"></label>
                <input type="text" id="numOfRestaurantMax" value="<%=clientData.getNumOfRestaurantMax()%>">
            </div>
            <h3>Number of Plates per Client</h3>
            <div>
                <label>Mean</label><br>
                <label for="plateMean"></label>
                <input type="text" id="plateMean" value="<%=clientData.getPlateNumberMean()%>">
            </div>
            <div>
                <label>Standard Deviation</label><br>
                <label for="plateSd"></label>
                <input type="range" id="plateSd" min="1" max="100" value="<%=(clientData.getPlateNumberSd()/clientData.getPlateNumberMean())*100.0%>">
                <label id="currentPlateSd"><%=(int)((clientData.getPlateNumberSd()/clientData.getPlateNumberMean())*100.0) + "%"%></label>
            </div>
            <label>Salary Groups</label>
            <table id="restaurantGroups" style="width:100%">
                <tr>
                    <th>Salary Options</th>
                    <th>Max Price for Plate</th>
                </tr>
                <%
                    for (Map.Entry<Integer, Integer> entry : clientData.getRestaurantGroup().entrySet()) {
                %>
                        <tr>
                            <td onkeyup="updateDataWithControl()" contenteditable='true'><%=entry.getKey()%></td>
                            <td onkeyup="updateDataWithControl()" contenteditable='true'><%=entry.getValue()%></td>
                        </tr>
                <% } %>
            </table>
            <div>
                <input onclick="updateData()" type="hidden" name="command" value="SaveSettingsCommand">
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
