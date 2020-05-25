<%@ page import="java.util.List" %>
<%@ page import="backend.model.simulables.person.client.Client" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Clients</title>
    <link rel="stylesheet" type="text/css" href="CSS/general.css">
</head>
<body>
    <h1 class="header">Bill Data Generator</h1>
    <h1>Clients</h1>
        <%List<Client> clientList = (List<Client>) request.getAttribute("list"); %>
        <% int actualPage = (Integer) request.getAttribute("page"); %>
        <% int length = (Integer) request.getAttribute("length"); %>
        <form method="post" action="FrontControllerServlet">
            <input type="hidden" name="command" value="ShowClientsCommand">
            <label>
                <select name="page">
                    <%
                        for (int i = 1; i <= length; i++) {
                    %>
                    <%
                        if(actualPage ==i){
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
                <th>NIF</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>birthDate</th>
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
                    <td><%=client.getSalary()%></td>
                    <td><%=client.getSalarySpent()%></td>
                    <td><%=client.getCountry()%></td>
                    <td><%=client.getTelephoneNumber()%></td>
                    <td><%=client.getEmail()%></td>
                    <td><%=client.getCardNumber()%></td>
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
