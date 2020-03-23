<%@ page import="backend.model.bill.generator.XMLBill" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
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
    <div style="display: inline-block">
        <h1>Bills</h1>
        <% List<XMLBill> billList = (List<XMLBill>) request.getAttribute("billList"); %>
        <% String pageText = (String) request.getAttribute("page"); %>
        <% int pageCount = Integer.parseInt(pageText); %>
        <% String lengthText = (String) request.getAttribute("length"); %>
        <% int length = Integer.parseInt(lengthText); %>
        <form method="post" action="FrontControllerServlet">
            <input type="hidden" name="command" value="ShowBillsCommand">
            <label>
                <select name="page">
                    <%
                        for (int i = 1; i <= length; i++) {
                    %>
                    <%
                        if(pageCount==i){
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

    </div>
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
                    <td><%=bill.getSubtotal()%></td>
                    <td><%=bill.getTaxRate()%></td>
                    <td><%=bill.getTotal()%></td>
                    <td><%=bill.getStreet()%></td>
                    <td><%=bill.getConcept()%></td>
                    <td>
                        <form method="post" action="FrontControllerServlet">
                            <input type="hidden" name="command" value="DownloadCommand">
                            <input type="hidden" name="ID" value="<%=bill.getUUID()%>">
                            <input type="submit"  value="Download">
                        </form>
                    </td>
                </tr>
            <% } %>
        <%}%>
    </table>
</body>
</html>
