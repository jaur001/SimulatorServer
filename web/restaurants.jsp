<%@ page import="backend.model.simulables.restaurant.Restaurant" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Restaurants</title>
        <link rel="stylesheet" type="text/css" href="CSS/general.css">
    </head>
    <body>
        <h1 class="header">Bill Data Generator</h1>
        <h1>Restaurants</h1>
        <%List<Restaurant> restaurantList = (List<Restaurant>) request.getAttribute("list"); %>
        <% int actualPage = (Integer) request.getAttribute("page"); %>
        <% int length = (Integer) request.getAttribute("length"); %>
        <form method="post" action="FrontControllerServlet">
            <input type="hidden" name="command" value="ShowRestaurantsCommand">
            <label>
                <select name="page">
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
            <input type="submit"  value="Change">
        </form>
        <table style="width:100%">
            <tr>
                <th>Company NIF</th>
                <th>Company Name</th>
                <th>Table Number</th>
                <th>Minimum Price Per Plate</th>
                <th>Maximum Price Per Plate</th>
                <th>Number Of Workers</th>
                <th>Street</th>
                <th>Telephone Number</th>
                <th>Social Capital</th>
                <th>Total Active</th>
                <th>Total Passive</th>
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
                    <td><%=restaurant.getTables()%></td>
                    <td><%=restaurant.getMinPricePlate()%></td>
                    <td><%=restaurant.getMaxPricePlate()%></td>
                    <td><%=restaurant.getNumberOfWorkers()%></td>
                    <td><%=restaurant.getStreet()%></td>
                    <td><%=restaurant.getTelephoneNumber()%></td>
                    <td><%=restaurant.getFinancialData().getSocialCapital()%></td>
                    <td><%=restaurant.getFinancialData().getTotalActive()%></td>
                    <td><%=restaurant.getFinancialData().getTotalPassive()%></td>
                </tr>
                <% } %>
            <% } %>
        </table>
    </body>
    <footer>
        <div class="footer">
            <div class="container">Author: Juan Alberto Ureña Rodriguez</div>
        </div>
    </footer>
</html>
