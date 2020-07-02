<%@ page import="backend.model.bill.generator.XMLBill" %>
<%@ page import="java.util.List" %>
<%@ page import="backend.utils.EuroFormatter" %>
<%@ page import="backend.model.simulation.timeLine.TimeLine" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Bills</title>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
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
            <h2>Bills</h2>
            <% List<XMLBill> billList = (List<XMLBill>) request.getAttribute("list"); %>
            <% int actualPage = (Integer) request.getAttribute("page"); %>
            <% int length = (Integer) request.getAttribute("length"); %>
            <h3><%="Current Date: " + TimeLine.getDate().toString()%></h3>
            <form class="inline-button" id="pageForm" method="post" action="FrontControllerServlet">
                <input type="hidden" name="command" value="ShowBillsCommand">
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
                    <th>UUID</th>
                    <th>Issuer Name</th>
                    <th>Issuer RFC</th>
                    <th>Receiver Name</th>
                    <th>Receiver RFC</th>
                    <th>Date</th>
                    <th>Type</th>
                    <th>Currency</th>
                    <th>Subtotal</th>
                    <th>Tax Rate</th>
                    <th>Total</th>
                    <th>Street</th>
                    <th>Concept</th>
                    <th>CFDI File</th>
                </tr>
                <%
                    if(billList.size()>0){
                %>
                    <%
                        for (XMLBill bill : billList) {
                    %>
                        <tr>
                            <td><%=bill.getUUID()%></td>
                            <td><%=bill.getIssuerName()%></td>
                            <td><%=bill.getIssuerRFC()%></td>
                            <td><%=bill.getReceiverName()%></td>
                            <td><%=bill.getReceiverRFC()%></td>
                            <td><%=bill.getDate()%></td>
                            <td><%=bill.getType()%></td>
                            <td><%=bill.getCurrency()%></td>
                            <td><%=EuroFormatter.formatEuro(bill.getSubtotal())%></td>
                            <td><%=bill.getTaxRate() +" %"%></td>
                            <td><%=EuroFormatter.formatEuro(bill.getTotal())%></td>
                            <td><%=bill.getStreet()%></td>
                            <td><%=bill.getConcept()%></td>
                            <td>
                                <form class="inline-button" method="post" action="FrontControllerServlet">
                                    <input type="hidden" name="command" value="DownloadCommand">
                                    <input type="hidden" name="ID" value="<%=bill.getUUID()%>">
                                    <input type="submit"  value="Download">
                                </form>
                            </td>
                        </tr>
                    <% } %>
                <%}%>
            </table>
        </div>
    </body>
</html>
