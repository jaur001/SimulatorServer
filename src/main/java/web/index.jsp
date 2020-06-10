<%@ page import="backend.model.simulation.timeLine.Speed" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Restaurant Simulator</title>
    <link rel="stylesheet" type="text/css" href="CSS/style.css">
    <script src="JQuery/jquery-3.4.1.min.js"></script>
    <script src="JS/frontendAdministrator.js"></script>
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
      <form>
        <input type="hidden" id="startCommand" value="StartCommand">
        <input type="button" id="start" value="Start/Stop">
      </form>
      <form>
        <input type="hidden" id="restartCommand" value="RestartCommand">
        <input type="button"  id="restart" value="Restart">
      </form>
      <form>
        <label for="speed">Speed</label>
        <input type="range" id="speed" min="1" max="100" value="<%=request.getSession(true).getAttribute(Speed.class.getSimpleName())%>">
        <label id="currentSpeed"><%=request.getSession(true).getAttribute(Speed.class.getSimpleName()) + "%"%></label>
      </form>
      <form>
        <label for="clientProb">Client Spawn Probability</label>
        <input type="range" id="clientProb" min="0" max="100" value="<%=request.getSession(true).getAttribute("clientProb")%>">
        <label id="currentClientProb"><%=request.getSession(true).getAttribute("clientProb") + "%"%></label>
      </form>
      <form>
        <label for="restaurantProb">Restaurant Spawn Probability</label>
        <input type="range" id="restaurantProb" min="0" max="100" value="<%=request.getSession(true).getAttribute("restaurantProb")%>">
        <label id="currentRestaurantProb"><%=request.getSession(true).getAttribute("restaurantProb") + "%"%></label>
      </form>
      <form>
        <label for="providerProb">Provider Spawn Probability</label>
        <input type="range" id="providerProb" min="0" max="100" value="<%=request.getSession(true).getAttribute("providerProb")%>">
        <label id="currentProviderProb"><%=request.getSession(true).getAttribute("providerProb") + "%"%></label>
      </form>
      <form>
        <label for="serviceProb">Service Spawn Probability</label>
        <input type="range" id="serviceProb" min="0" max="100" value="<%=request.getSession(true).getAttribute("serviceProb")%>">
        <label id="currentServiceProb"><%=request.getSession(true).getAttribute("serviceProb") + "%"%></label>
      </form>
      <form>
        <label for="workerProb">Worker Spawn Probability</label>
        <input type="range" id="workerProb" min="0" max="100" value="<%=request.getSession(true).getAttribute("workerProb")%>">
        <label id="currentWorkerProb"><%=request.getSession(true).getAttribute("workerProb") + "%"%></label>
      </form>
      <form>
        <label for="taxes">Company Taxes</label>
        <input type="range" id="taxes" min="0" max="100" value="<%=request.getSession(true).getAttribute("taxes")%>">
        <label id="currentTaxes"><%=request.getSession(true).getAttribute("taxes") + "%"%></label>
      </form>
      <label for="text-area"></label>
      <table id="simulableCountTable">
        <tr>
          <th>Clients Count</th>
          <th>Restaurants Count</th>
          <th>Providers Count</th>
          <th>Service Companies Count</th>
          <th>Workers Count</th>
        </tr>
        <tr>
          <td>0</td>
          <td>0</td>
          <td>0</td>
          <td>0</td>
          <td>0</td>
        </tr>
      </table>
      <textarea id="text-area" rows="10" cols="50">
      </textarea>
      <h3>Search Simulable</h3>
      <form>
        <br>
        <label for="simulableOptions">Search</label>
        <select onchange="changeOptions()" id="simulableOptions">
          <option value="person" selected>Person</option>
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
            <option value="Salary">Salary</option>
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
    </div>
  </body>
</html>
