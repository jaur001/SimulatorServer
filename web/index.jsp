<%@ page import="backend.model.bill.generator.CFDIBillGenerator" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Restaurant Simulator</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  </head>
  <body>
  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <form method="post" action="FrontControllerServlet">
          <input type="hidden" name="command" value="ClientsCommand">
          <input type="submit"  value="Clients">
        </form>
      </div>
      <div class="navbar-header">
        <form method="post" action="FrontControllerServlet">
          <input type="hidden" name="command" value="ProvidersCommand">
          <input type="submit"  value="Providers">
        </form>
      </div>
      <div class="navbar-header">
        <form method="post" action="FrontControllerServlet">
          <input type="hidden" name="command" value="RestaurantsCommand">
          <input type="submit"  value="Restaurants">
        </form>
      </div>
    </div>
  </nav>
  Welcome to my Simulator
  <form method="post" action="FrontControllerServlet">
    <label>
      <input type="text" name="clientRowNumber">
    </label>
    <input type="hidden" name="command" value="StartCommand">
    <input type="submit"  value="Start">
  </form>
  </body>
</html>
