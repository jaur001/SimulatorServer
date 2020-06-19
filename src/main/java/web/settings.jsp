<%@ page import="backend.model.simulation.settings.settingsData.data.GeneralData" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Settings</title>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
        <script src="JQuery/jquery-3.4.1.min.js"></script>
        <script src="JS/settingsAdministrator.js"></script>
    </head>
    <body>
        <%GeneralData generalData = (GeneralData) request.getSession(true).getAttribute(GeneralData.class.getSimpleName());%>
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
        <h2>General Settings</h2>
        <div>
            <form method="post" action="FrontControllerServlet">
                <div>
                    <h3>Number of Simulables</h3>
                    <div>
                        <label>Number of Clients</label><br>
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
                        <label>Number of Restaurants</label><br>
                        <label>
                            <select id="restaurants">
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="25" selected>25</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                                <option value="250">250</option>
                                <option value="500">500</option>
                            </select>
                            <label>Current: </label>
                            <label id="currentRestaurant"><%=generalData.getRestaurantCount()%></label>
                        </label>
                    </div>
                    <div>
                        <label>Number of Providers</label><br>
                        <label>
                            <select id="providers">
                                <option value="50">50</option>
                                <option value="100">100</option>
                                <option value="500" selected>500</option>
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
