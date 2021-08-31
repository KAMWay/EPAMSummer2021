package practice7.adapter;

import practice7.classes.*;
import com.epam.rd.java.basic.practice7.constants.XMLFowersConstants;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderSTAX {

    private static final Logger LOG = Logger.getLogger(ReaderSTAX.class.getName());
    private final String xmlFilePath;
    private final Flowers flowers;
    private Flower flower;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;

    public ReaderSTAX(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
        this.flowers = new Flowers();
    }

    public Flowers getFlowers() {
        getFromXML();
        return flowers;
    }

    public void getFromXML() {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = getEventReader(factory);
        try {
            parse(eventReader);
        } catch (XMLStreamException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
    }

    private XMLEventReader getEventReader(XMLInputFactory factory) {
        XMLEventReader eventReader = null;
        try {
            eventReader = factory.createXMLEventReader(new StreamSource(xmlFilePath));
        } catch (XMLStreamException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
        return eventReader;
    }

    private void parse(XMLEventReader eventReader) throws XMLStreamException {
        if (eventReader == null)
            throw new NullPointerException();

        while (eventReader.hasNext()) {
            XMLEvent xmlEvent = eventReader.nextEvent();
            if (xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();
                if (startElement.getName().getLocalPart().equals(XMLFowersConstants.FLOWER)) {
                    flower = new Flower();
                }
                setAttributes(startElement);
                setValues(startElement, eventReader);
            }
            if (xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();

                switch (endElement.getName().getLocalPart()) {
                    case XMLFowersConstants.FLOWER:
                        flowers.addFlower(flower);
                        break;
                    case "visualParameters":
                        setValue(flower, "visualParameters", visualParameters);
                        break;
                    case "growingTips":
                        setValue(flower, "growingTips", growingTips);
                        break;
                    default:
                }
            }
        }
    }

    private void setAttributes(StartElement startElement) {
        String qName = startElement.getName().getLocalPart();

        if (flower == null || qName.equals("attributes")) {
            return;
        }

        Field field = getFieldByName(flower.getAttributes().getClass(), qName);
        if (field == null) {
            return;
        }

        Map<String, String> valueMap;
        try {
            valueMap = (Map<String, String>) getValueFromField(field, flower.getAttributes());
        } catch (Exception e) {
            return;
        }

        Iterator<Attribute> attributesIt = startElement.getAttributes();
        while (attributesIt.hasNext()) {
            Attribute attribute = attributesIt.next();
            valueMap.put(
                    attribute.getName().getLocalPart(),
                    attribute.getValue()
            );

            setValue(flower.getAttributes(), qName, valueMap);
        }
    }

    private void setValues(StartElement startElement, XMLEventReader eventReader) throws XMLStreamException {
        String qName = startElement.getName().getLocalPart();
        XMLEvent xmlEvent;

        switch (qName) {
            case "visualParameters":
                visualParameters = new VisualParameters();
                break;
            case "growingTips":
                growingTips = new GrowingTips();
                break;
            case "name":
            case "soil":
            case "origin":
            case "multiplying":
                xmlEvent = eventReader.nextEvent();
                setValue(flower, qName, xmlEvent.asCharacters().getData());
                break;
            case "stemColour":
            case "leafColour":
            case "aveLenFlower":
                xmlEvent = eventReader.nextEvent();
                setValue(visualParameters, qName, xmlEvent.asCharacters().getData());
                break;
            case "tempreture":
            case "watering":
                xmlEvent = eventReader.nextEvent();
                setValue(growingTips, qName, xmlEvent.asCharacters().getData());
                break;
            default:
        }
    }

    private <E> Object getValueFromField(Field field, E element) {
        Object value = null;
        try {
            value = field.get(element);
        } catch (IllegalAccessException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }

        return value;
    }

    private boolean isValidValue4Integer(String value) {
        try {
            Integer.valueOf(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private <E> void setValue(E element, String fieldName, Object value) {
        if (value == null) {
            return;
        }

        Field field = getFieldByName(element.getClass(), fieldName);
        if (field == null) {
            return;
        }

        if (isValidValue4Integer(value.toString())) {
            setValueToField(field, element, Integer.valueOf(value.toString()));
        } else {
            setValueToField(field, element, value);
        }
    }

    private Field getFieldByName(Class<?> clazz, String fieldName) {
        if (!isExistFieldInClass(clazz, fieldName)) {
            return null;
        }

        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
        return field;
    }

    boolean isExistFieldInClass(Class<?> clazz, String fieldName) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(fieldName)) {
                return true;
            }
        }

        return false;
    }

    private <E> void setValueToField(Field field, E element, Object value) {
        try {
            field.set(element, value);
        } catch (IllegalAccessException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
    }

}