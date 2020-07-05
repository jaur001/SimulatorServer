<%@ page import="backend.model.simulation.settings.settingsData.data.ClientData" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Settings</title>
        <link rel="stylesheet" type="text/css" href="CSS/frontStyle.css">
        <script src="JQuery/jquery-3.4.1.min.js"></script>
        <script src="JS/clientSettingsAdministrator.js"></script>
    </head>
    <body>
        <%ClientData clientData = (ClientData) request.getSession(true).getAttribute(ClientData.class.getSimpleName());%>
        <div class="main-header">
            <div class="main-header__container">
                <h1 class="header">Invoice Generator</h1>
            </div>
            <nav class="nav nav-settings">
                <a class="main-a" href="settings.jsp"><button id="general">General Settings</button></a>
                <a class="main-a" href="clientSettings.jsp"><button id="client">Client Settings</button></a>
                <a class="main-a" href="restaurantSettings.jsp"><button id="restaurant">Restaurant Settings</button></a>
                <a class="main-a" href="providerSettings.jsp"><button id="provider">Provider Settings</button></a>
                <a class="main-a" href="serviceSettings.jsp"><button id="service">Service Settings</button></a>
                <a class="main-a" href="workerSettings.jsp"><button id="worker">Worker Settings</button></a>
            </nav>
        </div>
        <div class="container align-center">
            <h2>Client Settings</h2>
            <div>
                <form id="settings" class="inline-button" method="post" action="FrontControllerServlet">
                    <div class="settings-container">
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
                        <h3>Invited People</h3>
                        <div>
                            <label>Invited People Min</label><br>
                            <label for="invitedPeopleMin"></label>
                            <input type="text" id="invitedPeopleMin" value="<%=clientData.getInvitedPeopleMin()%>">
                            <br><label>Invited People Max</label><br>
                            <label for="invitedPeopleMax"></label>
                            <input type="text" id="invitedPeopleMax" value="<%=clientData.getInvitedPeopleMax()%>">
                        </div>
                        <h3>Number of Restaurants that Client goes</h3>
                        <div>
                            <label>Number of Restaurant Min</label><br>
                            <label for="numOfRestaurantMin"></label>
                            <input type="text" id="numOfRestaurantMin" value="<%=clientData.getNumOfRestaurantMin()%>">
                            <br><label>Number of Restaurant Max</label><br>
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
                        <h3>Salary Groups</h3>
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
                    </div>
                </form>
                <div class="button-group">
                    <div class="inline-button">
                        <input type="hidden" name="command" value="SaveSettingsCommand" form="settings">
                        <input type="submit"  value="Save" form="settings">
                    </div>
                    <form class="inline-button" method="post" action="FrontControllerServlet">
                        <input type="hidden" name="command" value="CancelCommand">
                        <input type="submit"  value="Cancel">
                    </form>
                    <form class="inline-button" method="post" action="FrontControllerServlet">
                        <input type="hidden" name="command" value="SetDefaultSettingsCommand">
                        <input type="submit"  value="Set Default">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
