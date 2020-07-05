<%@ page import="java.util.Map" %>
<%@ page import="backend.model.simulation.settings.settingsData.data.ServiceData" %>
<%@ page import="backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.Service" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Settings</title>
        <link rel="stylesheet" type="text/css" href="CSS/frontStyle.css">
        <script src="JQuery/jquery-3.4.1.min.js"></script>
        <script src="JS/serviceSettingsAdministrator.js"></script>
    </head>
    <body>
        <%ServiceData serviceData = (ServiceData) request.getSession(true).getAttribute(ServiceData.class.getSimpleName());%>
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
            <h2>Service Settings</h2>
            <div>
                <form id="settings" class="inline-button" method="post" action="FrontControllerServlet">
                    <div class="settings-container">
                        <div>
                            <label>Initial Social Capital</label><br>
                            <label for="initialSocialCapital"></label>
                            <input type="text" id="initialSocialCapital" value="<%=serviceData.getInitialSocialCapital()%>">
                        </div>
                        <div>
                            <label>Limit To Close The Restaurant</label><br>
                            <label for="closeLimit"></label>
                            <input type="text" id="closeLimit" value="<%=serviceData.getCloseLimit()%>">
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
                                <label id="currentPriceChange" ><%=(serviceData.getPriceChange()*100.0) + "%"%></label>
                            </label>
                        </div>
                        <label>Service Initial Sale Price</label>
                        <table id="serviceSalePrice" style="width:100%">
                            <tr>
                                <th>Service</th>
                                <th>Sale Price</th>
                            </tr>
                            <%
                                for (Map.Entry<Service, Double> entry : serviceData.getServicePriceTable().entrySet()) {
                            %>
                            <tr>
                                <td><%=entry.getKey().toString()%></td>
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
