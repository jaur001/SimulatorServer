<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Restaurant Simulator</title>
    <link rel="stylesheet" type="text/css" href="CSS/general.css">
    <script src="JQuery/jquery-3.4.1.min.js"></script>
    <script src="JS/frontAdministrator.js"></script>
  </head>
  <script>
    let isRunning = false;
    function changeOptions(){
      let val = $('#simulableOptions').val();
      if (val === "person") {
        $("#searchOptions").html("<option value='NIF'>NIF</option>" +
                "<option value='Name'>Name</option>" +
                "<option value='Job'>Job</option>");
      } else {
        $("#searchOptions").html("<option value='NIF'>NIF</option>" +
                "<option value='Name'>Name</option>");
      }
    }
  </script>
  <body>
    <h1 class="header">Bill Data Generator</h1>
    <nav>
      <form method="post" action="FrontControllerServlet">
        <input type="hidden" name="command" value="ShowClientsCommand">
        <input type="submit"  value="Clients">
      </form>
      <form method="post" action="FrontControllerServlet">
        <input type="hidden" name="command" value="ShowProvidersCommand">
        <input type="submit"  value="Providers">
      </form>
      <form method="post" action="FrontControllerServlet">
        <input type="hidden" name="command" value="ShowRestaurantsCommand">
        <input type="submit"  value="Restaurants">
      </form>
      <form method="post" action="FrontControllerServlet">
        <input type="hidden" name="command" value="ShowBillsCommand">
        <input type="submit"  value="Bills">
      </form>
      <form method="post" action="FrontControllerServlet">
        <input type="hidden" name="command" value="ShowWorkersCommand">
        <input type="submit"  value="Workers">
      </form>
      <form method="post" action="FrontControllerServlet">
        <input type="hidden" name="command" value="ShowSettingsCommand">
        <input type="submit"  value="Settings">
      </form>
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
    <h3>Search Simulable</h3>
    <form>
      <br>
      <label for="simulableOptions">Search</label>
      <select onchange="changeOptions()" id="simulableOptions">
        <option value="person">Person</option>
        <option value="company">Company</option>
      </select>
      <br>
      <label for="searchText">Text to search</label>
      <input type="text" id="searchText" value="">
      <br>
      <label>
        <label for="searchOptions">Search by</label>
        <select id="searchOptions">
          <option value="NIF">NIF</option>
          <option value="Name">Name</option>
          <option value="Job">Job</option>
        </select>
      </label>
      <br>
      <input type="hidden" id="searchCommand" value="SearchCommand">
      <input type="button" id="search"  value="Search">
      <input type="button" id="deleteSearch"  value="End Search">
    </form>
    <div id="divSearchTable">
    </div>
    <br>
    <div id="divPersonTable">
      <table id="personTable">
        <tr>
          <th>NIF</th>
          <th>Full Name</th>
          <th>Age</th>
          <th>Job</th>
          <th>Salary</th>
          <th>Budget</th>
        </tr>
      </table>
    </div>
    <br>
    <div id="divCompanyTable">
      <table id="companyTable">
        <tr>
          <th>NIF</th>
          <th>Company Name</th>
          <th>Company Type</th>
          <th>Treasury</th>
          <th>Benefits</th>
        </tr>
      </table>
    </div>
  </body>
  <footer>
    <div class="footer">
      <div class="container">Author: Juan Alberto Ureña Rodriguez</div>
    </div>
  </footer>
</html>
