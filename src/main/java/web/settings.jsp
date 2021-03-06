<%@ page import="backend.model.simulation.settings.settingsData.data.GeneralData" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Settings</title>
        <link rel="stylesheet" type="text/css" href="CSS/frontStyle.css">
        <script src="JQuery/jquery-3.4.1.min.js"></script>
        <script src="JS/settingsAdministrator.js"></script>
    </head>
    <body>
        <%GeneralData generalData = (GeneralData) request.getSession(true).getAttribute(GeneralData.class.getSimpleName());%>
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
            <h2>General Settings</h2>
            <div>
                <form id="settings" class="inline-button" method="post" action="FrontControllerServlet">
                    <div class="settings-container">
                        <h3>Number of Simulables</h3>
                        <div>
                            <label>Number of Clients</label>
                            <label>
                                <select id="clients">
                                    <option value="100">100</option>
                                    <option value="500">500</option>
                                    <option value="1000" selected>1000</option>
                                    <option value="2000">2000</option>
                                    <option value="5000">5000</option>
                                    <option value="10000">10000</option>
                                    <option value="50000">50000</option>
                                </select>
                                <label>Current: </label>
                                <label id="currentClient" ><%=generalData.getClientCount()%></label>
                            </label>
                        </div>
                        <div>
                            <label>Number of Restaurants</label>
                            <label>
                                <select id="restaurants">
                                    <option value="5">5</option>
                                    <option value="10">10</option>
                                    <option value="25">25</option>
                                    <option value="50" selected>50</option>
                                    <option value="100">100</option>
                                    <option value="250">250</option>
                                    <option value="500">500</option>
                                </select>
                                <label>Current: </label>
                                <label id="currentRestaurant"><%=generalData.getRestaurantCount()%></label>
                            </label>
                        </div>
                        <div>
                            <label>Number of Providers</label>
                            <label>
                                <select id="providers">
                                    <option value="50">50</option>
                                    <option value="100" selected>100</option>
                                    <option value="500">500</option>
                                    <option value="1000">1000</option>
                                    <option value="2500">2500</option>
                                    <option value="5000">5000</option>
                                    <option value="10000">10000</option>
                                </select>
                                <label>Current: </label>
                                <label id="currentProvider"><%=generalData.getProviderCount()%></label>
                            </label>
                        </div>
                        <div>
                            <label>Number of Services Companies</label><br>
                            <label>
                                <select id="services">
                                    <option value="10">10</option>
                                    <option value="50">50</option>
                                    <option value="100" selected>100</option>
                                    <option value="500" >500</option>
                                    <option value="1000">1000</option>
                                    <option value="2500">2500</option>
                                    <option value="5000">5000</option>
                                </select>
                                <label>Current: </label>
                                <label id="currentService"><%=generalData.getServiceCount()%></label>
                            </label>
                        </div>
                        <div>
                            <label>Number of Workers</label><br>
                            <label>
                                <select id="workers">
                                    <option value="100">100</option>
                                    <option value="500">500</option>
                                    <option value="1000" selected>1000</option>
                                    <option value="2000">2000</option>
                                    <option value="5000">5000</option>
                                    <option value="10000">10000</option>
                                    <option value="50000">50000</option>
                                </select>
                                <label>Current: </label>
                                <label id="currentWorker"><%=generalData.getWorkerCount()%></label>
                            </label>
                        </div>
                    </div>
                    <br>
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
