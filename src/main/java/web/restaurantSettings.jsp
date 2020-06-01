<%@ page import="backend.server.EJB.dataSettings.data.RestaurantData" %>
<%@ page import="java.util.Map" %>
<%@ page import="backend.model.simulables.person.worker.Job" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Settings</title>
    <link rel="stylesheet" type="text/css" href="CSS/style.css">
    <script src="JQuery/jquery-3.4.1.min.js"></script>
    <script src="JS/restaurantSettingsAdministrator.js"></script>
</head>
<body>
    <%RestaurantData restaurantData = (RestaurantData) request.getSession(true).getAttribute(RestaurantData.class.getSimpleName());%>
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
    <h2>Restaurant Settings</h2>
    <div>
        <form method="post" action="FrontControllerServlet">
            <h3>Salary</h3>
            <div>
                <label>Initial Social Capital</label><br>
                <label for="initialSocialCapital"></label>
                <input type="text" id="initialSocialCapital" value="<%=restaurantData.getInitialSocialCapital()%>">
            </div>
            <div>
                <label>Capacity</label><br>
                <label for="capacity"></label>
                <input type="range" id="capacity" min="1" max="100" value="<%=restaurantData.getCapacity()*100.0%>">
                <label id="currentCapacity"><%=(int)(restaurantData.getCapacity()*100.0) + "%"%></label>
            </div>
            <div>
                <label>Limit To Close The Restaurant</label><br>
                <label for="closeLimit"></label>
                <input type="text" id="closeLimit" value="<%=restaurantData.getCloseLimit()%>">
            </div>
            <div>
                <label>Length Contract Min</label>
                <label for="lengthContractMin"></label>
                <input type="text" id="lengthContractMin" value="<%=restaurantData.getLengthContract().getMin()%>">
                <label>Length Contract Max</label>
                <label for="lengthContractMax"></label>
                <input type="text" id="lengthContractMax" value="<%=restaurantData.getLengthContract().getMax()%>">
            </div>
            <div>
                <label>Price Plate Change</label><br>
                <label>
                    <select id="priceChange">
                        <option value="0.005">0.5%</option>
                        <option value="0.01">1%</option>
                        <option value="0.02" selected>2%</option>
                        <option value="0.03">3%</option>
                        <option value="0.04">4%</option>
                        <option value="0.05">5%</option>
                        <option value="0.1">10%</option>
                    </select>
                    <label>Current: </label>
                    <label id="currentPriceChange" ><%=(restaurantData.getPriceChange()*100.0) + "%"%></label>
                </label>
            </div>
            <label>Jobs Initial Salary</label>
            <table id="jobSalary" style="width:100%">
                <tr>
                    <th>Job</th>
                    <th>Salary</th>
                </tr>
                <%
                    for (Map.Entry<Job, Integer> entry : restaurantData.getWorkerSalaryTable().entrySet()) {
                %>
                    <tr>
                        <td><%=entry.getKey().toString()%></td>
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
