package backend.server.commands.simulables;

import backend.model.simulables.company.Company;
import backend.model.simulables.person.client.Client;
import backend.utils.EuroFormatter;
import backend.utils.SearchUtils;
import backend.server.searcher.Search;
import backend.server.searcher.SearchBy;
import backend.server.servlets.FrontCommand;
import backend.view.loaders.database.DatabaseManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SearchCommand extends FrontCommand {

    @Override
    public void process() {
        String text = request.getParameter("searchText");
        SearchBy searchBy = SearchBy.valueOf(request.getParameter("searchBy"));
        String type = request.getParameter("type");
        try {
            PrintWriter out = response.getWriter();
            out.println("<table id='searchTable'>");
            if(type.equals("person")) createTablePerson(getPeople(searchBy,text),out);
            if(type.equals("company")) createTableCompany(getCompanies(searchBy,text),out);
            out.println("</table>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Client> getPeople(SearchBy searchBy, String text) {
        Search<Client> search = SearchUtils.getPersonFilter(searchBy);
        return Search.searchPeople(search.search(text));
    }

    private List<Company> getCompanies(SearchBy searchBy, String text) {
        Search<Company> search = SearchUtils.getCompanyFilter(searchBy);
        return Search.searchCompanies(search.search(text));
    }

    private void createTablePerson(List<Client> simulables, PrintWriter out) {
        out.println("<tr>");
        out.println("<th style= rowspan='7' align='center' bgcolor='#f8f8f8'>NIF</th>");
        out.println("<th style= rowspan='7' align='center' bgcolor='#f8f8f8'>Full Name</th>");
        out.println("<th style= rowspan='7' align='center' bgcolor='#f8f8f8'>Salary</th>");
        out.println("<th style= rowspan='7' align='center' bgcolor='#f8f8f8'>Job</th>");
        out.println("</tr>");
        simulables.stream()
                .limit(DatabaseManager.getPageLength())
                .forEach(simulable -> appendPerson(simulable,out));
    }

    private void createTableCompany(List<Company> simulables, PrintWriter out) {
        out.println("<tr>");
        out.println("<th style= rowspan='7' align='center' bgcolor='#f8f8f8'>NIF</th>");
        out.println("<th style= rowspan='7' align='center' bgcolor='#f8f8f8'>Company Name</th>");
        out.println("<th style= rowspan='7' align='center' bgcolor='#f8f8f8'>Benefits</th>");
        out.println("<th style= rowspan='7' align='center' bgcolor='#f8f8f8'>Treasury</th>");
        out.println("</tr>");
        simulables.stream()
                .limit(DatabaseManager.getPageLength())
                .forEach(simulable -> appendCompany(simulable,out));
    }

    private void appendPerson(Client client, PrintWriter out) {
        out.println("<tr>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + client.getNIF() + "</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + client.getName() + "</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + client.getSalaryToShow() + "</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + client.getJob() + "</td>");
        out.println("</tr>");
    }

    private void appendCompany(Company company, PrintWriter out) {
        out.println("<tr>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + company.getNIF() + "</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + company.getName() + "</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>"+ EuroFormatter.format(company.getFinancialData().getLastMonthBenefits())+"</td>");
        out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>"+ EuroFormatter.format(company.getFinancialData().getTreasury())+"</td>");
        out.println("</tr>");
    }
}
