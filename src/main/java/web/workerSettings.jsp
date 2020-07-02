<%@ page import="backend.model.simulation.settings.settingsData.data.WorkerData" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Settings</title>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
        <script src="JQuery/jquery-3.4.1.min.js"></script>
        <script src="JS/workerSettingsAdministrator.js"></script>
    </head>
    <body>
        <%WorkerData workerData = (WorkerData) request.getSession(true).getAttribute(WorkerData.class.getSimpleName());%>
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
            <h2>Worker Settings</h2>
            <div>
                <form id="settings" class="inline-button" method="post" action="FrontControllerServlet">
                    <div class="settings-container">
                        <h3>Salary</h3>
                        <div>
                            <label>Salary Min</label><br>
                            <label for="minSalary"></label>
                            <input type="text" id="minSalary" value="<%=workerData.getMinSalary()%>">
                        </div>
                        <div>
                            <label>Salary Variation</label><br>
                            <label for="salaryChange"></label>
                            <input type="range" id="salaryChange" min="1" max="100" value="<%=workerData.getSalaryChange()*100.0%>">
                            <label id="currentSalaryChange"><%=(int)(workerData.getSalaryChange()*100.0) + "%"%></label>
                        </div>
                        <div>
                            <label>Salary Desire Variation</label><br>
                            <label for="salaryDesiredChange"></label>
                            <input type="range" id="salaryDesiredChange" min="1" max="1000" value="<%=workerData.getSalaryDesiredChange()*1000.0%>">
                            <label id="currentSalaryDesiredChange"><%=(int)(workerData.getSalaryDesiredChange()*1000.0) + "‰"%></label>
                        </div>
                        <div>
                            <label>Retire Age</label><br>
                            <label for="retireAge"></label>
                            <input type="text" id="retireAge" value="<%=workerData.getRetireAge()%>">
                        </div>
                        <div>
                            <label>Percentage of Salary for Retirement</label><br>
                            <label for="percentageRetirement"></label>
                            <input type="range" id="percentageRetirement" min="1" max="100" value="<%=workerData.getPercentageRetirement()*100.0%>">
                            <label id="currentPercentageRetirement"><%=(int)(workerData.getPercentageRetirement()*100.0) + "%"%></label>
                        </div>
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
