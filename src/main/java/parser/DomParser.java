package parser;

import bean.Device;
import bean.Type;
import bean.mapping.DeviceMapping;
import bean.mapping.TypeMapping;
import exception.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements Parse{

    @Override
    public List<Device> pars(InputStream iStream) throws ParseException {

        if(iStream == null){
            return null;
        }

        List<Device> devicesList = new ArrayList<>();
        DocumentBuilder documentBuilder = null;

        try{
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }catch (ParserConfigurationException e){
            throw new ParseException();
        }

        Document document = null;
        try {
            document = documentBuilder.parse(iStream);
        } catch (SAXException | IOException e) {
            throw new ParseException();
        }

        Element element = document.getDocumentElement();
        NodeList list = element.getElementsByTagName(DeviceMapping.CLASS_NAME);
        for (int i =0; i < list.getLength(); i++){
            Element value = (Element) list.item(i);
            devicesList.add(buildDevice(value));
        }
        return devicesList;
    }

    private String getTextContext(Element element,String name){
        NodeList list= element.getElementsByTagName(name);
        Node node= list.item(0);
        return node.getTextContent();
    }

    private Device buildDevice(Element value){
        Element type = (Element) value.getElementsByTagName(TypeMapping.CLASS_NAME).item(0);
        return Device.create()
                .setId(Integer.parseInt(getTextContext(value, DeviceMapping.ID)))
                .setName(getTextContext(value,DeviceMapping.NAME))
                .setOrigin(getTextContext(value,DeviceMapping.ORIGIN))
                .setPrice(Float.parseFloat(getTextContext(value,DeviceMapping.PRICE)))
                .setType(Type.create()
                        .setIsPeriferal(Boolean.parseBoolean(getTextContext(type,TypeMapping.IS_PERIFIRAL)))
                        .setEnergyConsumption(Integer.parseInt(getTextContext(type,TypeMapping.ENERGY_CONSUMPTION)))
                        .setIsCooler(Boolean.parseBoolean(getTextContext(type,TypeMapping.IS_COOLER)))
                        .setGroupofcomponent(getTextContext(type,TypeMapping.GROUP_OF_COMPONENT))
                        .setPort(getTextContext(type,TypeMapping.PORT))
                        .build()
                )
                .setIsCritical(Boolean.parseBoolean(value.getAttribute(DeviceMapping.IS_CRITICAL)))
                .build();
    }
}
