<%@ page import="java.util.List" %>
<%@ page import="backend.model.simulables.person.worker.Worker" %>
<%@ page import="backend.model.simulation.timeLine.TimeLine" %>
<%@ page import="backend.utils.EuroFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Workers</title>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
    </head>
    <script src="JS/pagination.js"></script>
    <body>
        <div class="main-header">
            <div class="main-header__container">
                <h1 class="header">Bill Data Generator</h1>
            </div>
            <nav class="nav">
                <a class="main-a" href="index.jsp"><button>Home</button></a>
                <form class="myForm" method="post" action="FrontControllerServlet">
                    <input type="hidden" name="command" value="ShowClientsCommand">
                    <input type="submit" value="Clients">
                </form>
                <form class="myForm" method="post" action="FrontControllerServlet">
                    <input type="hidden" name="command" value="ShowRestaurantsCommand">
                    <input type="submit" value="Restaurants">
                </form>
                <form class="myForm" method="post" action="FrontControllerServlet">
                    <input type="hidden" name="command" value="ShowProvidersCommand">
                    <input type="submit" value="Providers">
                </form>
                <form class="myForm" method="post" action="FrontControllerServlet">
                    <input type="hidden" name="command" value="ShowServicesCommand">
                    <input type="submit" value="Service Companies">
                </form>
                <form class="myForm" method="post" action="FrontControllerServlet">
                    <input type="hidden" name="command" value="ShowWorkersCommand">
                    <input type="submit" value="Workers">
                </form>
                <form class="myForm" method="post" action="FrontControllerServlet">
                    <input type="hidden" name="command" value="ShowBillsCommand">
                    <input type="submit" value="Bills">
                </form>
                <form class="myForm" method="post" action="FrontControllerServlet">
                    <input type="hidden" name="command" value="ShowSettingsCommand">
                    <input type="submit" value="Settings">
                </form>
            </nav>
        </div>
        <div class="container">
            <h2>Workers</h2>
            <%List<Worker> workerList = (List<Worker>) request.getAttribute("list"); %>
            <% int actualPage = (Integer) request.getAttribute("page"); %>
            <% int length = (Integer) request.getAttribute("length"); %>
            <h3><%="Current Date: " + TimeLine.getDate().toString()%></h3>
            <form class="inline-button" id="pageForm" method="post" action="FrontControllerServlet">
                <input type="hidden" name="command" value="ShowWorkersCommand">
                <label>Page</label>
                <label>
                    <select onchange="updatePage()" id="page" name="page">
                        <%for (int i = 1; i <= length; i++) {
                        %>
                        <%if(actualPage==i){
                        %>
                        <option value="<%=i%>" selected><%=i%></option>
                        <%} else {%>
                        <option value="<%=i%>"><%=i%></option>
                        <%}%>
                        <%}%>
                    </select>
                </label>
            </form>
            <table style="width:100%">
                <tr>
                    <th>NIF</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Birth Date</th>
                    <th>Gender</th>
                    <th>Job</th>
                    <th>Salary</th>
                    <th>Contract's Expire Date</th>
                    <th>Restaurant</th>
                    <th>Quality</th>
                    <th>Country</th>
                    <th>Telephone Number</th>
                    <th>Email</th>
                    <th>Card Number</th>
                </tr>
                <%
                    if(workerList.size()>0){
                %>
                <%
                    for (Worker worker : workerList) {
                %>
                <tr>
                    <td><%=worker.getNIF()%></td>
                    <td><%=worker.getFirstName()%></td>
                    <td><%=worker.getLastName()%></td>
                    <td><%=worker.getBirthDate()%></td>
                    <td><%=worker.getGender()%></td>
                    <td><%=worker.getJob()%></td>
                    <%
                        if(worker.isWorking()){
                    %>
                        <td><%=EuroFormatter.formatEuro(worker.getSalary())%></td>
                        <td><%=worker.getContractExpireDate().toString()%></td>
                        <td><%=worker.getCompany().getName()%></td>
                    <% } else {%>
                        <td>Unemployed</td>
                        <td>Unemployed</td>
                        <td>Unemployed</td>
                    <% } %>
                    <td><%=worker.getQuality()%></td>
                    <td><%=worker.getCountry()%></td>
                    <td><%=worker.getTelephoneNumber()%></td>
                    <td><%=worker.getEmail()%></td>
                    <td><%=worker.getCardNumber()%></td>
                </tr>
                <% } %>
                <% } %>
            </table>
        </div>
    </body>
</html>