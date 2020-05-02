package backend.server.commands.simulables;

import backend.model.simulables.company.Company;
import backend.model.simulables.person.client.Client;
import backend.server.EJB.SearchControllerStatelessBean;
import backend.server.EJB.SearchStatelessBean;
import backend.server.searcher.Search;
import backend.server.searcher.SearchBy;
import backend.server.servlets.FrontCommand;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SearchCommand extends FrontCommand {

    SearchControllerStatelessBean searchController;
    {
        try {
            searchController = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/SearchControllerStatelessEJB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process() {
        String text = request.getParameter("searchText");
        SearchBy searchBy = SearchBy.valueOf(request.getParameter("searchBy"));
        String type = request.getParameter("type");
        try {
            PrintWriter out = response.getWriter();
            out.println("<table>");
            if(type.equals("person")) createTablePerson(getPeople(searchBy,text),out);
            if(type.equals("company")) createTableCompany(getCompanies(searchBy,text),out);
            out.println("</table>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Client> getPeople(SearchBy searchBy, String text) {
        Search<Client> search = searchController.getPersonFilter(searchBy);
        return Search.searchPeople(search.search(text));
    }

    private List<Company> getCompanies(SearchBy searchBy, String text) {
        Search<Company> search = searchController.getCompanyFilter(searchBy);
        return Search.searchCompanies(search.search(text));
    }

    private void createTablePerson(List<Client> simulables, PrintWriter out) {
        out.println("<tr>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>NIF</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>Full Name</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>Salary</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>Job</td>");
        out.println("</tr>");
        simulables.forEach(simulable -> appendPerson(simulable,out));
    }

    private void createTableCompany(List<Company> simulables, PrintWriter out) {
        out.println("<tr>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>NIF</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>Company Name</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>Benefits</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>Treasury</td>");
        out.println("</tr>");
        simulables.forEach(simulable -> appendCompany(simulable,out));
    }

    private void appendPerson(Client simulable, PrintWriter out) {
        out.println("<tr>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + simulable.getNIF() + "</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + simulable.getName() + "</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + simulable.getSalary() + "</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + simulable.getJob() + "</td>");
        out.println("</tr>");
    }

    private void appendCompany(Company company, PrintWriter out) {
        out.println("<tr>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + company.getNIF() + "</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + company.getName() + "</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>"+company.getFinancialData().getBenefits()+"</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>"+company.getFinancialData().getTreasury()+"</td>");
        out.println("</tr>");
    }
}
