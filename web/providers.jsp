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
            <%
                if(providerList.size()>0){
            %>
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
            <% } %>
        </table>
    </body>
</html>
