<%@ page import="java.util.List" %>
<%@ page import="backend.model.simulables.person.worker.Worker" %>
<%@ page import="backend.model.simulation.timeLine.TimeLine" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Workers</title>
    <link rel="stylesheet" type="text/css" href="CSS/general.css">
</head>
<body>
<h1 class="header">Bill Data Generator</h1>
<h1>Workers</h1>
<%List<Worker> workerList = (List<Worker>) request.getAttribute("list"); %>
<% int actualPage = (Integer) request.getAttribute("page"); %>
<% int length = (Integer) request.getAttribute("length"); %>
<form method="post" action="FrontControllerServlet">
    <input type="hidden" name="command" value="ShowWorkersCommand">
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
        <th>Contract's Expire Date</th>
        <th>Quality</th>
        <th>Restaurant</th>
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
                <td>Unemployed</td>
            <% } else {%>
                <td><%=worker.getSalary()%></td>
            <% } %>
            <%
                if(worker.isWorking()){
            %>
                <td>Unemployed</td>
            <% } else {%>
                <td><%=worker.getContractExpireDate().toString() + " (current Date: " + TimeLine.getDate().toString() + ")"%></td>
            <% } %>
            <td><%=worker.getQuality()%></td>
            <%
                if(worker.getCompany() == null){
            %>
            <td>Unemployed</td>
            <% } else {%>
            <td><%=worker.getCompany().getName()%></td>
            <% } %>
            <td><%=worker.getCountry()%></td>
            <td><%=worker.getTelephoneNumber()%></td>
            <td><%=worker.getEmail()%></td>
            <td><%=worker.getCardNumber()%></td>
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