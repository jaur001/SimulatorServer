<%@ page import="backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant" %>
<%@ page import="java.util.List" %>
<%@ page import="backend.utils.EuroFormatter" %>
<%@ page import="backend.model.simulation.timeLine.TimeLine" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Restaurants</title>
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
            <h2>Restaurants</h2>
            <%List<Restaurant> restaurantList = (List<Restaurant>) request.getAttribute("list"); %>
            <% int actualPage = (Integer) request.getAttribute("page"); %>
            <% int length = (Integer) request.getAttribute("length"); %>
            <h3><%="Current Date: " + TimeLine.getDate().toString()%></h3>
            <form class="inline-button" id="pageForm" method="post" action="FrontControllerServlet">
                <input type="hidden" name="command" value="ShowRestaurantsCommand">
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
                    <th>Company NIF</th>
                    <th>Company Name</th>
                    <th>Score</th>
                    <th>Table Number</th>
                    <th>Minimum Price Per Plate</th>
                    <th>Maximum Price Per Plate</th>
                    <th>Number Of Workers</th>
                    <th>Zipcode</th>
                    <th>Telephone Number</th>
                    <th>Profits</th>
                    <th>Losses</th>
                    <th>Sales</th>
                    <th>Social Capital</th>
                    <th>Total Active</th>
                    <th>Total Passive</th>
                    <th>Treasury</th>
                </tr>
                <%
                    if(restaurantList.size()>0){
                %>
                    <%
                        for (Restaurant restaurant : restaurantList) {
                    %>
                    <tr>
                        <td><%=restaurant.getNIF()%></td>
                        <td><%=restaurant.getName()%></td>
                        <td><%=EuroFormatter.format(restaurant.getScore())%></td>
                        <td><%=restaurant.getTables()%></td>
                        <td><%=EuroFormatter.formatEuro(restaurant.getMinPricePlate())%></td>
                        <td><%=EuroFormatter.formatEuro(restaurant.getMaxPricePlate())%></td>
                        <td><%=restaurant.getNumberOfWorkers()%></td>
                        <td><%=restaurant.getStreet()%></td>
                        <td><%=restaurant.getTelephoneNumber()%></td>
                        <td><%=EuroFormatter.formatEuro(restaurant.getFinancialData().getIncome())%></td>
                        <td><%=EuroFormatter.formatEuro(restaurant.getFinancialData().getLosses())%></td>
                        <td><%=EuroFormatter.formatEuro(restaurant.getFinancialData().getSales())%></td>
                        <td><%=EuroFormatter.formatEuro(restaurant.getFinancialData().getSocialCapital())%></td>
                        <td><%=EuroFormatter.formatEuro(restaurant.getFinancialData().getTotalActive())%></td>
                        <td><%=EuroFormatter.formatEuro(restaurant.getFinancialData().getTotalPassive())%></td>
                        <td><%=EuroFormatter.formatEuro(restaurant.getFinancialData().getTreasury())%></td>
                    </tr>
                    <% } %>
                <% } %>
            </table>
        </div>
    </body>
</html>
