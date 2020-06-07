<%@ page import="java.util.List" %>
<%@ page import="backend.model.simulables.company.secondaryCompany.monthlyCompanies.service.ServiceCompany" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Service Companies</title>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
    </head>
    <script>
        function updatePage (){
            document.getElementById('pageForm').submit();
        }
    </script>
    <body>
        <div class="main-header container">
            <div class="main-header__container">
                <h1 class="header">Bill Data Generator</h1>
            </div>
            <nav class="nav">
                <a class="main-a" href="index.jsp"><button>Home</button></a>
                <form class="myForm" method="post" action="FrontControllerServlet">
                    <input type="hidden" name="command" value="ShowClientsCommand">
                    <input type="submit"  value="Clients">
                </form>
                <form class="myForm" method="post" action="FrontControllerServlet">
                    <input type="hidden" name="command" value="ShowRestaurantsCommand">
                    <input type="submit"  value="Restaurants">
                </form>
                <form class="myForm" method="post" action="FrontControllerServlet">
                    <input type="hidden" name="command" value="ShowProvidersCommand">
                    <input type="submit"  value="Providers">
                </form>
                <form class="myForm" method="post" action="FrontControllerServlet">
                    <input type="hidden" name="command" value="ShowServicesCommand">
                    <input type="submit"  value="Service Companies">
                </form>
                <form class="myForm" method="post" action="FrontControllerServlet">
                    <input type="hidden" name="command" value="ShowWorkersCommand">
                    <input type="submit"  value="Workers">
                </form>
                <form class="myForm" method="post" action="FrontControllerServlet">
                    <input type="hidden" name="command" value="ShowBillsCommand">
                    <input type="submit"  value="Bills">
                </form>
                <form class="myForm" method="post" action="FrontControllerServlet">
                    <input type="hidden" name="command" value="ShowSettingsCommand">
                    <input type="submit"  value="Settings">
                </form>
            </nav>
        </div>
        <div class="container">
            <h1>Service Companies</h1>
            <%List<ServiceCompany> serviceList = (List<ServiceCompany>) request.getAttribute("list"); %>
            <% int actualPage = (Integer) request.getAttribute("page"); %>
            <% int length = (Integer) request.getAttribute("length"); %>
            <form id="pageForm" method="post" action="FrontControllerServlet">
                <input type="hidden" name="command" value="ShowServicesCommand">
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
                    <th>Service</th>
                    <th>Service Price</th>
                    <th>Owner Name</th>
                    <th>Creation Date</th>
                    <th>Street</th>
                    <th>Telephone Number</th>
                    <th>Treasury</th>
                    <th>Profits</th>
                    <th>Losses</th>
                    <th>Sales</th>
                    <th>Purchases</th>
                    <th>Social Capital</th>
                    <th>Total Active</th>
                    <th>Total Passive</th>
                </tr>
                <%
                    if(serviceList.size()>0){
                %>
                    <%
                        for (ServiceCompany serviceCompany : serviceList) {
                    %>
                    <tr>
                        <td><%=serviceCompany.getNIF()%></td>
                        <td><%=serviceCompany.getName()%></td>
                        <td><%=serviceCompany.getProduct()%></td>
                        <td><%=serviceCompany.getPrice()%></td>
                        <td><%=serviceCompany.getOwnerName()%></td>
                        <td><%=serviceCompany.getCreationDate()%></td>
                        <td><%=serviceCompany.getStreet()%></td>
                        <td><%=serviceCompany.getTelephoneNumber()%></td>
                        <td><%=serviceCompany.getFinancialData().getTreasury()%></td>
                        <td><%=serviceCompany.getFinancialData().getIncome()%></td>
                        <td><%=serviceCompany.getFinancialData().getLosses()%></td>
                        <td><%=serviceCompany.getFinancialData().getSales()%></td>
                        <td><%=serviceCompany.getFinancialData().getPurchases()%></td>
                        <td><%=serviceCompany.getFinancialData().getSocialCapital()%></td>
                        <td><%=serviceCompany.getFinancialData().getTotalActive()%></td>
                        <td><%=serviceCompany.getFinancialData().getTotalPassive()%></td>
                    </tr>
                    <% } %>
                <% } %>
            </table>
        </div>
    </body>
</html>
