<%@ page import="java.util.List" %>
<%@ page import="backend.model.simulables.client.Client" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Clients</title>
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
    <h1>Clients</h1>
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
        <%List<Client> clientList = (List<Client>) request.getAttribute("clientList"); %>
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
            <td><%=client.getCountry()%></td>
            <td><%=client.getTelephoneNumber()%></td>
            <td><%=client.getEmail()%></td>
            <td><%=client.getCardNumber()%></td>
        </tr>
        <% } %>
    </table>
    </body>
</html>
