<%@ page import="java.util.List" %>
<%@ page import="backend.model.simulables.person.client.Client" %>
<%@ page import="backend.model.simulation.timeLine.TimeLine" %>
<%@ page import="backend.utils.EuroFormatter" %>
<%@ page import="backend.model.simulation.administration.centralControl.Simulation" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Clients</title>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
    </head>
    <script src="JS/pagination.js"></script>
    <body>
        <div class="main-header">
            <div class="main-header__container">
                <h1 class="header">Invoice Generator</h1>
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
            <h2>Clients</h2>
            <%List<Client> clientList = (List<Client>) request.getAttribute("list"); %>
            <% int actualPage = (Integer) request.getAttribute("page"); %>
            <% int length = (Integer) request.getAttribute("length"); %>
            <h3><%="Current Date: " + TimeLine.getDate().toString()%></h3>
            <form class="inline-button" id="pageForm" method="post" action="FrontControllerServlet">
                <input type="hidden" name="command" value="ShowClientsCommand">
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
                    <th>Salary Spent</th>
                    <th>Country</th>
                    <th>Telephone Number</th>
                    <th>Email</th>
                    <th>Card Number</th>
                </tr>
                <%
                    if(clientList.size()>0){
                %>
                    <%
                        for (Client client : clientList) {
                    %>
                    <tr>
                        <td><%=client.getNIF()%></td>
                        <td><%=client.getFirstName()%></td>
                        <td><%=client.getLastName()%></td>
                        <td><%=client.getBirthDate()%></td>
                        <td><%=client.getGender()%></td>
                        <td><%=client.getJob()%></td>
                        <td><%=client.getCurrencySalary()%></td>
                        <td><%=EuroFormatter.formatEuro(client.getSalarySpent())%></td>
                        <td><%=client.getCountry()%></td>
                        <td><%=client.getTelephoneNumber()%></td>
                        <td><%=client.getEmail()%></td>
                        <td><%=client.getCardNumber()%></td>
                    </tr>
                    <% } %>
                <% } %>
            </table>
        </div>
    </body>
</html>
