<%@ page import="backend.model.restaurant.Bill" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 10/03/2020
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bills</title>
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
<h1>Bills</h1>
<table style="width:100%">
    <tr>
        <th>NIF</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>birthDate</th>
        <th>Gender</th>
        <th>Job</th>
        <th>Country</th>
        <th>Telephone Number</th>
        <th>Email</th>
        <th>Card Number</th>
    </tr>
    <%List<Bill> billList = (List<Bill>) request.getAttribute("billList"); %>
    <%
        for (Bill plateBill : billList) {
    %>
    <tr>
        <td><%=plateBill.getNIF()%></td>
        <td><%=plateBill.getFirstName()%></td>
        <td><%=plateBill.getLastName()%></td>
        <td><%=plateBill.getBirthDate()%></td>
        <td><%=plateBill.getGender()%></td>
        <td><%=plateBill.getJob()%></td>
        <td><%=plateBill.getCountry()%></td>
        <td><%=plateBill.getTelephoneNumber()%></td>
        <td><%=plateBill.getEmail()%></td>
        <td><%=plateBill.getCardNumber()%></td>
    </tr>
    <% } %>
</table>
</body>
</html>
