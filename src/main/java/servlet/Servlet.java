package servlet;

import bean.Device;
import bean.mapping.DeviceMapping;
import bean.mapping.TypeMapping;
import exception.ParseException;
import parser.DomParser;
import parser.Parse;
import parser.SaxParser;
import parser.StaxParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet
public class Servlet {

    private static final String DOM_PARSER = "DOM";
    private static final String STAX_PARSER = "StAX";
    private static final String SAX_PARSER = "SAX";

    private static final String PARSER_TYPE_PARAMETER = "parsType";
    private static final String FILE_PARAMETER = "file";


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String parserType = request.getParameter(PARSER_TYPE_PARAMETER);
        Part filePart = request.getPart(FILE_PARAMETER);
        InputStream fileContent = filePart.getInputStream();

        List<Device> devices = null;
        Parse parser = null;
        switch (parserType) {
            case DOM_PARSER:
                parser = new DomParser();
                break;
            case STAX_PARSER:
                parser = new StaxParser();
                break;
            case SAX_PARSER:
                parser = new SaxParser();
                break;
            default:
                break;
        }
        if (parser != null) {
            try {
                devices = parser.pars(fileContent);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.printf("%s", DeviceMapping.ID);
            System.out.printf("%s", DeviceMapping.NAME);
            System.out.printf("%s", DeviceMapping.ORIGIN);
            System.out.printf("%s", DeviceMapping.PRICE);
            System.out.printf("%s", TypeMapping.IS_PERIFIRAL);
            System.out.printf("%s", TypeMapping.ENERGY_CONSUMPTION);
            System.out.printf("%s", TypeMapping.IS_COOLER);
            System.out.printf("%s", TypeMapping.GROUP_OF_COMPONENT);
            System.out.printf("%s", TypeMapping.PORT);
            System.out.printf("%s", DeviceMapping.IS_CRITICAL);

            if (devices != null) {
                for (Device device : devices) {
                    System.out.println("\n");
                    System.out.printf("%d", device.getId());
                    System.out.printf("%s", device.getName());
                    System.out.printf("%s", device.getOrigin());
                    System.out.printf("%f", device.getPrice());
                    System.out.printf("%b", device.getType().getIsPeriferal());
                    System.out.printf("%d", device.getType().getEnergyConsumption());
                    System.out.printf("%b", device.getType().getIsCooler());
                    System.out.printf("%s", device.getType().getGroupofcomponent());
                    System.out.printf("%s", device.getType().getPort());
                    System.out.printf("%b", device.getIsCritical());
                }
            }
        }
    }
}
