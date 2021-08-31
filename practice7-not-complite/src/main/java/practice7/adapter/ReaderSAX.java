package practice7.adapter;

import com.epam.rd.java.basic.practice7.constants.XMLFowersConstants;
import practice7.classes.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderSAX extends DefaultHandler {

    private static final Logger LOG = Logger.getLogger(ReaderSAX.class.getName());
    private final String urlXMLFile;
    private final Flowers flowers;
    private String information;
    private Flower flower;

    public ReaderSAX(String urlXMLFile) {
        this.urlXMLFile = urlXMLFile;
        this.flowers = new Flowers();
    }

    public Flowers getFlowers() {
        getFromXML();
        return flowers;
    }

    public void getFromXML() {
        SAXParserFactory factory = getFactory();
        setParameters(factory);
    }

    private SAXParserFactory getFactory() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(true);
        return factory;
    }

    private void setParameters(SAXParserFactory factory) {
        javax.xml.parsers.SAXParser parser;
        try {
            parser = factory.newSAXParser();
            parser.parse(urlXMLFile, this);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals(XMLFowersConstants.FLOWER)) {
            flower = new Flower();
        }
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
            LOG.log(Level.WARNING, e.getMessage());
            return;
        }

        for (int i = 0; i < attributes.getLength(); i++) {
            valueMap.put(attributes.getLocalName(i), attributes.getValue(i));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        information = new String(ch, start, length);
        information = information.replace("\n", "").trim();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (information == null) {
            return;
        }
        switch (qName) {
            case "name":
            case "soil":
            case "origin":
                setValue(flower, qName, information);
                break;
            case "stemColour":
            case "leafColour":
            case "aveLenFlower":
                setValue(flower.getVisualParameters(), qName, information);
                break;
            case "tempreture":
            case "lighting":
            case "watering":
                setValue(flower.getGrowingTips(), qName, information);
                break;
            case "multiplying":
                setValue(flower, qName, information);
                flowers.addFlower(flower);
                break;
            default:
        }
        information = null;

    }

    private Field getFieldByName(Class<?> clazz, String fieldName) {
        if (!isExistFieldInClass(clazz, fieldName)) {
            return null;
        }

        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }

        return null;
    }

    boolean isExistFieldInClass(Class<?> clazz, String fieldName) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(fieldName)) {
                return true;
            }
        }

        return false;
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

    <E> void setValue(E element, String fieldName, String value) {
        Field field = getFieldByName(element.getClass(), fieldName);
        if (field == null) {
            return;
        }

        if (isValidValue4Integer(value)) {
            setValueToField(field, element, Integer.valueOf(value));
        } else {
            setValueToField(field, element, value);
        }
    }

    private <E> void setValueToField(Field field, E element, Object value) {
        try {
            field.set(element, value);
        } catch (IllegalAccessException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
    }

}
