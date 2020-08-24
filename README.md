# Simulator Web Application
This project was the my final project and part of a Big data project developed by 4 Junior Java developers and a Senior developer that guided us. It is a Web application that simulate a virtual economic environment formed by companies that buy and sell products and service. These companies act in function of certain parameters and strategies to get the highest benefits and maintaining the rules and laws in the reality (e.g. Minimum salaries for the employers). All these Economy of Market works based of demands.

During the execution the simulator will generate a high volume of invoices or bills that can be analyzed by other programs or processed by Big data applications, like in the case of this project. The rest of the modules of the main project use these invoices to generate reports about taxes automatically or to calculate the financial state of the companies. the bills will be saved in the XMLFiles folder located in "src/main/java/web/" folder.

The Web application allows the user to change the settings to get a simulation adaptated to their needs. Besides, it allows to check what is happening inside the simulation by the Event Box that shows all the events that happened during the execution and decide which Companies, Clients or Worker to track to show the events only of these ones. The user can download all the bills generated he wants from the Web client to read them or to process with other programms.

This project was focused on the Backend (more than 250 classes) side to get the most reliability and realistic Virtual Economic Simulation and to be scalable using the base Arquitecture developed.


## Install/Execute
* The project has no Framework dependency, so it is necessary to have the server downloaded apart.
* The project uses the Tomcat Server, so the server downloaded must be Tomcat.
* Then, it is only need to clone and execute the project with the Tomcat server.


## Built with
#### Backend
* Java - Language
* [Tomcat Server](http://tomcat.apache.org/) - Server for Backend
* [SQLite Browse](https://sqlitebrowser.org/) - SQL Relational DB Management System Aplication
* [Maven](https://maven.apache.org/) - Dependency Management
#### Frontend
* JSP, JavaScript and CSS - Languages for Web designing
* [Jquery](https://jquery.com/) - Library to control the Web Requests and Data-Flow


## Simulation - Events published

![Simulation](https://github.com/jaur001/SimulatorServer/blob/release/GIF/Simulation.gif)



## Simulation - Search Simulables

![searchSimulable](https://github.com/jaur001/SimulatorServer/blob/release/GIF/searchSimulable.gif)



## Simulation - Download Bills

![downloadBills](https://github.com/jaur001/SimulatorServer/blob/release/GIF/downloadBills.gif)



## Simulation - Agents actives in the Simulation

![simulables](https://github.com/jaur001/SimulatorServer/blob/release/GIF/simulables.gif)



## Simulation - Bills

![bills](https://github.com/jaur001/SimulatorServer/blob/release/GIF/bills.gif)
