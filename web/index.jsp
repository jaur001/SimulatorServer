<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Restaurant Simulator</title>
    <link rel="stylesheet" type="text/css" href="CSS/general.css">
  </head>
  <body>
  <h1 class="header">Bill Data Generator</h1>
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <form method="post" action="FrontControllerServlet">
            <input type="hidden" name="command" value="ShowClientsCommand">
            <input type="submit"  value="Clients">
          </form>
        </div>
        <div class="navbar-header">
          <form method="post" action="FrontControllerServlet">
            <input type="hidden" name="command" value="ShowProvidersCommand">
            <input type="submit"  value="Providers">
          </form>
        </div>
        <div class="navbar-header">
          <form method="post" action="FrontControllerServlet">
            <input type="hidden" name="command" value="ShowRestaurantsCommand">
            <input type="submit"  value="Restaurants">
          </form>
        </div>
        <div class="navbar-header">
          <form method="post" action="FrontControllerServlet">
            <input type="hidden" name="command" value="ShowBillsCommand">
            <input type="submit"  value="Bills">
          </form>
        </div>
        <div class="navbar-header">
          <form method="post" action="FrontControllerServlet">
            <input type="hidden" name="command" value="ShowSettingsCommand">
            <input type="submit"  value="Settings">
          </form>
        </div>
      </div>
    </nav>
    Welcome to my Simulator
    <form method="post" action="FrontControllerServlet">
      <input type="hidden" name="command" value="StartCommand">
      <input type="submit"  value="Start/Stop">
    </form>
    <form method="post" action="FrontControllerServlet">
      <input type="hidden" name="command" value="RestartCommand">
      <input type="submit"  value="Restart">
    </form>
  </body>
  <footer>
    <div class="footer">
      <div class="container">Author: Juan Alberto Ureña Rodriguez</div>
    </div>
  </footer>
</html>
