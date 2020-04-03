<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Restaurant Simulator</title>
    <link rel="stylesheet" type="text/css" href="CSS/general.css">
    <script src="JQuery/jquery-3.4.1.min.js"></script>
    <script src="JS/index.js"></script>
  </head>
  <script>
    let isRunning = false;
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
    <form>
      <input type="hidden" id="startCommand" value="StartCommand">
      <input type="button" id="start" value="Start/Stop">
    </form>
    <form>
      <input type="hidden" id="restartCommand" name="command" value="RestartCommand">
      <input type="button"  id="restart" value="Restart">
    </form>
    <form>
      <label for="speed"></label>
      <input type="range" id="speed" name="speed" min="100" max="5000" step="10">
      <input type="hidden" id="speedCommand" value="ChangeSpeedCommand">
      <input type="button" id="changeSpeed"  value="Change Speed">
    </form>
    <label for="text-area"></label>
    <textarea id="text-area" rows="10" cols="50">

    </textarea>
  </body>
  <footer>
    <div class="footer">
      <div class="container">Author: Juan Alberto Ureña Rodriguez</div>
    </div>
  </footer>
</html>
