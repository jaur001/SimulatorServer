<%@ page import="backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Provider" %>
<%@ page import="java.util.List" %>
<%@ page import="backend.utils.EuroFormatter" %>
<%@ page import="backend.model.simulation.timeLine.TimeLine" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Providers</title>
        <link rel="stylesheet" type="text/css" href="CSS/frontStyle.css">
    </head>
    <script src="JS/pagination.js"></script>
    <body>
        <div class="main-header">
            <div class="main-header__container">
                <h1 class="header">Invoice Generator</h1>
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
            <h2>Providers</h2>
            <%List<Provider> providerList = (List<Provider>) request.getAttribute("list"); %>
            <% int actualPage = (Integer) request.getAttribute("page"); %>
            <% int length = (Integer) request.getAttribute("length"); %>
            <h3><%="Current Date: " + TimeLine.getDate().toString()%></h3>
            <div class="bottom">
                <form class="inline-button half-space" method="post" action="FrontControllerServlet">
                    <div class="searcher full-space">
                        <div class="block-left">
                            <label for="searchText">Search Provider</label>
                            <input type="text" id="searchText" name="searchText" value="">
                        </div>
                        <div class="block-right">
                            <label class="no-padding" for="searchOptions">Search by</label>
                            <select id="searchOptions" name="searchBy">
                                <option value="NIF">NIF</option>
                                <option value="Name">Name</option>
                                <option value="Benefits">Benefits</option>
                                <option value="Treasury">Treasury</option>
                            </select>
                        </div>
                        <input type="hidden" name="command" value="SearchProvidersCommand">
                        <input class="margin-top" type="submit" id="search" value="Search">
                    </div>
                </form>
                <form class="inline-button end-search" method="post" action="FrontControllerServlet">
                    <input type="hidden" name="command" value="ShowProvidersCommand">
                    <input type="submit" value="End Search">
                </form>
            </div>
            <form class="inline-button" id="pageForm" method="post" action="FrontControllerServlet">
                <input type="hidden" name="command" value="ShowProvidersCommand">
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
                    <th>Product</th>
                    <th>Product Price</th>
                    <th>Owner Name</th>
                    <th>Creation Date</th>
                    <th>Zipcode</th>
                    <th>Telephone Number</th>
                    <th>Number of Clients</th>
                    <th>Profits</th>
                    <th>Losses</th>
                    <th>Total Active</th>
                    <th>Total Passive</th>
                    <th>Treasury</th>
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
                        <td><%=EuroFormatter.formatEuro(provider.getPrice())%></td>
                        <td><%=provider.getOwnerName()%></td>
                        <td><%=provider.getCreationDate()%></td>
                        <td><%=provider.getStreet()%></td>
                        <td><%=provider.getTelephoneNumber()%></td>
                        <td><%=provider.getCompanyClientList().size()%></td>
                        <td><%=EuroFormatter.formatEuro(provider.getFinancialData().getIncome())%></td>
                        <td><%=EuroFormatter.formatEuro(provider.getFinancialData().getLosses())%></td>
                        <td><%=EuroFormatter.formatEuro(provider.getFinancialData().getTotalActive())%></td>
                        <td><%=EuroFormatter.formatEuro(provider.getFinancialData().getTotalPassive())%></td>
                        <td><%=EuroFormatter.formatEuro(provider.getFinancialData().getTreasury())%></td>
                    </tr>
                    <% } %>
                <% } %>
            </table>
        </div>
    </body>
</html>
