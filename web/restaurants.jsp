<%@ page import="backend.model.simulables.restaurant.Restaurant" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Restaurants</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 15px;
            text-align: left;
        }
    </style>
</head>
<body>
<h1>Restaurants</h1>
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
    <%List<Restaurant> restaurantList = (List<Restaurant>) request.getAttribute("restaurantList"); %>
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
</table>
</body>
</html>
