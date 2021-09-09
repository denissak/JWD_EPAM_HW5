package parser;

import bean.Device;
import bean.Type;
import bean.mapping.DeviceMapping;
import bean.mapping.TypeMapping;
import exception.ParseException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StaxParser implements Parse{

    private String getText(XMLStreamReader reader) throws XMLStreamException {
        if(!reader.hasNext())return null;
        reader.next();
        return reader.getText();
    }

    private Device createDevice(XMLStreamReader reader) throws XMLStreamException {
        Device.Builder deviceBuilder =Device.create();
        Type.Builder typeBuilder = Type.create();

        String elementName;
        while(reader.hasNext()){
            reader.next();
            if(reader.isStartElement()) {
                elementName = reader.getLocalName();
                switch (elementName) {
                    case DeviceMapping.ID:
                        deviceBuilder.setId(Integer.parseInt(Objects.requireNonNull(getText(reader))));
                        break;
                    case DeviceMapping.NAME:
                        deviceBuilder.setName(getText(reader));
                        break;
                    case DeviceMapping.ORIGIN:
                        deviceBuilder.setOrigin(getText(reader));
                        break;
                    case DeviceMapping.PRICE:
                        deviceBuilder.setPrice(Float.parseFloat(Objects.requireNonNull(getText(reader))));
                        break;
                    case TypeMapping.IS_PERIFIRAL:
                        typeBuilder.setIsPeriferal(Boolean.parseBoolean(getText(reader)));
                        break;
                    case TypeMapping.ENERGY_CONSUMPTION:
                        typeBuilder.setEnergyConsumption(Integer.parseInt(Objects.requireNonNull(getText(reader))));
                        break;
                    case TypeMapping.IS_COOLER:
                        typeBuilder.setIsCooler(Boolean.parseBoolean(getText(reader)));
                        break;
                    case TypeMapping.GROUP_OF_COMPONENT:
                        typeBuilder.setGroupofcomponent(getText(reader));
                        break;
                    case TypeMapping.PORT:
                        typeBuilder.setPort(getText(reader));
                        break;
                    case DeviceMapping.IS_CRITICAL:
                        deviceBuilder.setIsCritical(Boolean.parseBoolean(getText(reader)));
                        break;
                    default:
                        break;
                }
            }else if(reader.isEndElement()){
                elementName = reader.getLocalName();
                if(elementName.equals(TypeMapping.CLASS_NAME)) {
                    deviceBuilder.setType(typeBuilder.build());
                }else if(elementName.equals(DeviceMapping.CLASS_NAME)){
                    return deviceBuilder.build();
                }
            }
        }
        return deviceBuilder.build();
    }
    @Override
    public List<Device> pars(InputStream stream) throws ParseException {
        if(stream == null) return null;
        List<Device> investments = new ArrayList<>();
        try {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(stream);

            while (reader.hasNext()) {
                int type = reader.next();
                if(type == XMLStreamConstants.START_ELEMENT){
                    if(reader.getLocalName().equals(DeviceMapping.CLASS_NAME)){
                        investments.add(createDevice(reader));
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new ParseException();
        }
        return investments;
    }
}
