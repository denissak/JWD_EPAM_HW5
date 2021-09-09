package parser;

import bean.Device;
import exception.ParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import java.util.List;

public interface Parse {
    List<Device> pars (InputStream iStream) throws ParseException;
}
