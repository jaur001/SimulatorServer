package backend.server.commands;

import backend.model.bill.generator.XMLBill;
import backend.model.simulation.Simulation;
import backend.server.servlets.FrontCommand;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class DownloadCommand extends FrontCommand {
    @Override
    public void process() {
        String fileID = request.getParameter("ID");
        XMLBill bill = Simulation.getBillList(Simulation.getActualPage()).stream()
                .filter(xmlBill -> BillHaveTheUUID(fileID, xmlBill)).findFirst().orElse(null);
        response.setContentType("text/html");
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition","attachment; filename=\"" + bill.getFileName() + "\"");
        download(bill);
    }

    private boolean BillHaveTheUUID(String fileID, XMLBill xmlBill) {
        return xmlBill.getUUID()==Integer.parseInt(fileID);
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
