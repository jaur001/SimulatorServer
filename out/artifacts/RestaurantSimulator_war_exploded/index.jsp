<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Restaurant Simulator</title>
    <link rel="stylesheet" type="text/css" href="CSS/general.css">
    <script src="JQuery/jquery-3.4.1.min.js"></script>
  </head>
  <script>
    $(document).ready(function() {
      $('#submit').click(function(event) {
        let speed = $('#speed').val();
        let command = $('#command').val();
        $.post('FrontControllerServlet', {
          speed : speed,
          "command": command
        });
      });
    });
  </script>
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
    <form>
      <input type="range" id="speed" name="speed" min="100" max="5000" step="10">
      <input type="hidden" id="command" name="command" value="ChangeSpeedCommand">
      <input type="button" id="submit"  value="Change Speed">
    </form>
  </body>
  <footer>
    <div class="footer">
      <div class="container">Author: Juan Alberto Ureña Rodriguez</div>
    </div>
  </footer>
</html>
