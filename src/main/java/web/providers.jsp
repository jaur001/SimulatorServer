<%@ page import="backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Providers</title>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
    </head>
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
            <h1>Providers</h1>
            <%List<Provider> providerList = (List<Provider>) request.getAttribute("list"); %>
            <% int actualPage = (Integer) request.getAttribute("page"); %>
            <% int length = (Integer) request.getAttribute("length"); %>
            <form method="post" action="FrontControllerServlet">
                <input type="hidden" name="command" value="ShowProvidersCommand">
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
                    <th>Product</th>
                    <th>Product Price</th>
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
                    if(providerList.size()>0){
                %>
                    <%
                        for (Provider provider : providerList) {
                    %>
                    <tr>
                        <td><%=provider.getNIF()%></td>
                        <td><%=provider.getName()%></td>
                        <td><%=provider.getProduct()%></td>
                        <td><%=provider.getPrice()%></td>
                        <td><%=provider.getOwnerName()%></td>
                        <td><%=provider.getCreationDate()%></td>
                        <td><%=provider.getStreet()%></td>
                        <td><%=provider.getTelephoneNumber()%></td>
                        <td><%=provider.getFinancialData().getTreasury()%></td>
                        <td><%=provider.getFinancialData().getIncome()%></td>
                        <td><%=provider.getFinancialData().getLosses()%></td>
                        <td><%=provider.getFinancialData().getSales()%></td>
                        <td><%=provider.getFinancialData().getPurchases()%></td>
                        <td><%=provider.getFinancialData().getSocialCapital()%></td>
                        <td><%=provider.getFinancialData().getTotalActive()%></td>
                        <td><%=provider.getFinancialData().getTotalPassive()%></td>
                    </tr>
                    <% } %>
                <% } %>
            </table>
        </div>
    </body>
</html>
