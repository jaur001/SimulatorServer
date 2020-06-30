<%@ page import="backend.model.simulation.timeLine.Speed" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Restaurant Simulator</title>
    <link rel="stylesheet" type="text/css" href="CSS/style.css">
    <script src="JQuery/jquery-3.4.1.min.js"></script>
    <script src="JS/mainPageAdministrator.js"></script>
  </head>
  <body>
    <div class="main-header">
      <div class="main-header__container">
        <h1 class="header">Bill Data Generator</h1>
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
      <div class="container__content left-content">
        <div class="main-controller">
          <h2>Main Controller</h2>
          <form class="range-button">
            <label class="range-block" for="speed">Speed</label>
            <input type="range" id="speed" min="1" max="100"
                   value="<%=request.getSession(true).getAttribute(Speed.class.getSimpleName())%>">
            <label id="currentSpeed"><%=request.getSession(true).getAttribute(Speed.class.getSimpleName()) + "%"%></label>
          </form>
          <form class="range-button">
            <label class="range-block" for="clientProb">Client Spawn Probability</label>
            <input type="range" id="clientProb" min="0" max="100"
                   value="<%=request.getSession(true).getAttribute("clientProb")%>">
            <label id="currentClientProb"><%=request.getSession(true).getAttribute("clientProb") + "%"%></label>
          </form>
          <form class="range-button">
            <label class="range-block" for="restaurantProb">Restaurant Spawn Probability</label>
            <input type="range" id="restaurantProb" min="0" max="100"
                   value="<%=request.getSession(true).getAttribute("restaurantProb")%>">
            <label id="currentRestaurantProb"><%=request.getSession(true).getAttribute("restaurantProb") + "%"%></label>
          </form>
          <form class="range-button">
            <label class="range-block" for="providerProb">Provider Spawn Probability</label>
            <input type="range" id="providerProb" min="0" max="100"
                   value="<%=request.getSession(true).getAttribute("providerProb")%>">
            <label id="currentProviderProb"><%=request.getSession(true).getAttribute("providerProb") + "%"%></label>
          </form>
          <form class="range-button">
            <label class="range-block" for="serviceProb">Service Spawn Probability</label>
            <input type="range" id="serviceProb" min="0" max="100"
                   value="<%=request.getSession(true).getAttribute("serviceProb")%>">
            <label id="currentServiceProb"><%=request.getSession(true).getAttribute("serviceProb") + "%"%></label>
          </form>
          <form class="range-button">
            <label class="range-block" for="workerProb">Worker Spawn Probability</label>
            <input type="range" id="workerProb" min="0" max="100"
                   value="<%=request.getSession(true).getAttribute("workerProb")%>">
            <label id="currentWorkerProb"><%=request.getSession(true).getAttribute("workerProb") + "%"%></label>
          </form>
          <form class="range-button">
            <label class="range-block" for="taxes">Company Taxes</label>
            <input type="range" id="taxes" min="0" max="100" value="<%=request.getSession(true).getAttribute("taxes")%>">
            <label id="currentTaxes"><%=request.getSession(true).getAttribute("taxes") + "%"%></label>
          </form>
          <form class="inline-button">
            <input type="hidden" id="startCommand" value="StartCommand">
            <input type="button" id="start" value="Start/Stop">
          </form>
          <form class="inline-button">
            <input type="hidden" id="restartCommand" value="RestartCommand">
            <input type="button" id="restart" value="Restart">
          </form>
        </div>
        <div class="bottom-content">
          <div class="bottom search-side">
            <form class="inline-button">
              <div class="searcher">
                <h2>Search Simulable</h2>
                <label class="range-block" for="simulableOptions">Search</label>
                <select onchange="changeOptions()" id="simulableOptions">
                  <option value="person" selected>Person</option>
                  <option value="company">Company</option>
                </select>
                <label class="range-block" for="searchText">Text to search</label>
                <input type="text" id="searchText" value="">
                <label>
                  <label class="range-block" for="searchOptions">Search by</label>
                  <select id="searchOptions">
                    <option value="NIF">NIF</option>
                    <option value="Name">Name</option>
                    <option value="Job">Job</option>
                    <option value="Salary">Salary</option>
                  </select>
                </label>
                <br><br>
                <input type="hidden" id="searchCommand" value="SearchCommand">
                <input type="button" id="search" value="Search">
                <input type="button" id="deleteSearch" value="End Search">
              </div>
              <div class="search-table" id="divSearchTable">
              </div>
            </form>
          </div>
        </div>
      </div>
      <div class="container__content right-content">
        <div class="left">
          <h2>Event Reporter</h2>
          <label for="eventBox"></label>
          <div class="event-box" id="eventBox">
          </div>
        </div>
        <div id="followedSimulables" class="right">
          <h2>Followed Agents</h2>
          <div class="simulable-table" id="divPersonTable">
            <h3>People</h3>
            <table id="personTable">
            </table>
          </div>
          <div class="simulable-table" id="divCompanyTable">
            <h3>Company</h3>
            <table id="companyTable">
            </table>
          </div>
        </div>
        <div class="down bottom count-table">
          <h2>Agents Counter</h2>
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
        </div>
      </div>
    </div>
  </body>
</html>