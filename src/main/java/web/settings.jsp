<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Settings</title>
        <link rel="stylesheet" type="text/css" href="CSS/general.css">
    </head>
    <body>
        <h1 class="header">Bill Data Generator</h1>
        <h2>General Settings</h2>
        <div>
            <form method="post" action="FrontControllerServlet">
                <div>
                    <h3>Number of Simulables</h3>
                    <div>
                        <label>Number of Clients</label><br>
                        <label>
                            <select name="clients">
                                <option value="100">100</option>
                                <option value="500">500</option>
                                <option value="1000">1000</option>
                                <option value="5000" selected>5000</option>
                                <option value="10000">10000</option>
                                <option value="50000">50000</option>
                                <option value="100000">100000</option>
                            </select>
                        </label>
                    </div>
                    <div>
                        <label>Number of Restaurants</label><br>
                        <label>
                            <select name="restaurants">
                                <option value="10">10</option>
                                <option value="50">50</option>
                                <option value="100" selected>100</option>
                                <option value="250">250</option>
                                <option value="500">500</option>
                            </select>
                        </label>
                    </div>
                    <div>
                        <label>Number of Providers</label><br>
                        <label>
                            <select name="providers">
                                <option value="100">100</option>
                                <option value="500">500</option>
                                <option value="1000" selected>1000</option>
                                <option value="2500">2500</option>
                                <option value="5000">5000</option>
                            </select>
                        </label>
                    </div>
                </div>
                <br>
                <div>
                    <input type="hidden" name="command" value="SaveSettingsCommand">
                    <input type="submit"  value="Save">
                </div>
            </form>
            <form method="post" action="FrontControllerServlet">
                <input type="hidden" name="command" value="CancelCommand">
                <input type="submit"  value="Cancel">
            </form>
        </div>
        <br>
        <h2>Advanced Settings</h2>
        <div>
            <form method="post" action="FrontControllerServlet">
                <div>
                    <h3>Clients Settings</h3>
                </div>
                <div>
                    <h3>Restaurant Settings</h3>
                </div>
                <div>
                    <h3>Provider Settings</h3>
                </div>
                <div>
                    <h3>Bill Settings</h3>
                </div>
                <div>
                    <input type="hidden" name="command" value="SaveSettingsCommand">
                    <input type="submit"  value="Save">
                </div>
            </form>
            <form method="post" action="FrontControllerServlet">
                <input type="hidden" name="command" value="CancelCommand">
                <input type="submit"  value="Cancel">
            </form>
        </div>
        <div class="fixed-footer">
            <div class="container">Author: Juan Alberto Ureña Rodriguez</div>
        </div>
    </body>
    <footer>
        <div class="footer">
            <div class="container">Author: Juan Alberto Ureña Rodriguez</div>
        </div>
    </footer>
</html>
