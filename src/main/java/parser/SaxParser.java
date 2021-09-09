package parser;

import bean.Device;
import bean.Type;
import bean.mapping.DeviceMapping;
import bean.mapping.TypeMapping;
import exception.ParseException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SaxParser implements Parse{

    private List<Device> devices = new ArrayList<>();
    @Override
    public List<Device> pars(InputStream stream) throws ParseException {
        if(stream == null)return  null;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler( new DeviceHandler());
            reader.parse(new InputSource(stream));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ParseException();
        }
        return devices;
    }

    public class DeviceHandler extends DefaultHandler {

        private String lastElement;
        boolean value;

        private Device.Builder deviceBuilder;
        private Type.Builder typeBuilder;

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (value) {
                String elementEntry = new String(ch, start, length);
                switch (lastElement) {
                    case DeviceMapping.ID:
                        deviceBuilder.setId(Integer.parseInt(elementEntry));
                        break;
                    case DeviceMapping.NAME:
                        deviceBuilder.setName(elementEntry);
                        break;
                    case DeviceMapping.ORIGIN:
                        deviceBuilder.setOrigin(elementEntry);
                        break;
                    case DeviceMapping.PRICE:
                        deviceBuilder.setPrice(Float.parseFloat(elementEntry));
                        break;
                    case TypeMapping.IS_PERIFIRAL:
                        typeBuilder.setIsPeriferal(Boolean.parseBoolean(elementEntry));
                        break;
                    case TypeMapping.ENERGY_CONSUMPTION:
                        typeBuilder.setEnergyConsumption(Integer.parseInt(elementEntry));
                        break;
                    case TypeMapping.IS_COOLER:
                        typeBuilder.setIsCooler(Boolean.parseBoolean(elementEntry));
                        break;
                    case TypeMapping.GROUP_OF_COMPONENT:
                        typeBuilder.setGroupofcomponent(elementEntry);
                        break;
                    case TypeMapping.PORT:
                        typeBuilder.setPort(elementEntry);
                        break;
                    case DeviceMapping.IS_CRITICAL:
                        deviceBuilder.setIsCritical(Boolean.parseBoolean(elementEntry));
                        break;
                    default:
                        break;
                }
            }
            super.characters(ch, start, length);
        }


        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            value = false;
            if (qName.equals(DeviceMapping.CLASS_NAME)) {
                devices.add(deviceBuilder.build());
            } else if (qName.equals(TypeMapping.CLASS_NAME)) {
                deviceBuilder.setType(typeBuilder.build());
            }
            super.endElement(uri, localName, qName);
        }

    }
}
