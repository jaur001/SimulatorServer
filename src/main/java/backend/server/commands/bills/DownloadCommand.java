package backend.server.commands.bills;

import backend.model.bill.generator.XMLBill;
import backend.model.simulation.administration.data.SimulationBillAdministrator;
import backend.server.servlets.FrontCommand;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class DownloadCommand extends FrontCommand {

    @Override
    public void process() {
        String fileID = request.getParameter("ID");
        XMLBill bill = SimulationBillAdministrator.getBillPage(getBillPage()).stream()
                .filter(xmlBill -> hasTheUUID(fileID, xmlBill)).findFirst().orElse(new XMLBill());
        prepareDownload(bill);
        download(bill);
    }

    private int getBillPage() {
        return (int)request.getSession(true).getAttribute("billPage");
    }

    private boolean hasTheUUID(String fileID, XMLBill xmlBill) {
        return xmlBill.getUUID()==Integer.parseInt(fileID);
    }

    private void prepareDownload(XMLBill bill) {
        response.setContentType("text/html");
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition","attachment; filename=\"" + bill.getFileName() + "\"");
    }


    private void download(XMLBill bill) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(bill.getFilePath() + bill.getFileName());
            PrintWriter out = response.getWriter();
            int i;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
            fileInputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
