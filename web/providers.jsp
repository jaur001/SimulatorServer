<%@ page import="backend.model.simulables.provider.Provider" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Providers</title>
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
<h1>Providers</h1>
<table style="width:100%">
    <tr>
        <th>Company Name</th>
        <th>Product</th>
        <th>Product Price</th>
        <th>Owner Name</th>
        <th>Creation Date</th>
        <th>Street</th>
        <th>Telephone Number</th>
        <th>Social Capital</th>
        <th>Total Active</th>
        <th>Total Passive</th>
    </tr>
    <%List<Provider> providerList = (List<Provider>) request.getAttribute("providerList"); %>
    <%
        for (Provider provider : providerList) {
    %>
    <tr>
        <td><%=provider.getCompanyName()%></td>
        <td><%=provider.getProduct()%></td>
        <td><%=provider.getProductPrice()%></td>
        <td><%=provider.getOwnerName()%></td>
        <td><%=provider.getCreationDate()%></td>
        <td><%=provider.getStreet()%></td>
        <td><%=provider.getTelephoneNumber()%></td>
        <td><%=provider.getFinancialData().getSocialCapital()%></td>
        <td><%=provider.getFinancialData().getTotalActive()%></td>
        <td><%=provider.getFinancialData().getTotalPassive()%></td>
    </tr>
    <% } %>
</table>
</body>
</html>
