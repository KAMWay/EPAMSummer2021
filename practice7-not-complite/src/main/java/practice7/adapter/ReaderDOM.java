package practice7.adapter;

import practice7.classes.*;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderDOM {

    private static final Logger LOG = Logger.getLogger(ReaderDOM.class.getName());

    private final String urlXMLFile;
    private Document document;
    private final Flowers flowers;
    private Flower flower;


    public ReaderDOM(String urlXMLFile) {
        this.urlXMLFile = urlXMLFile;
        this.flowers = new Flowers();
    }

    private DocumentBuilderFactory getFactory() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        setFactoryParameters(factory);
        return factory;
    }

    private void setFactoryParameters(DocumentBuilderFactory builderFactory) {
        try {
            builderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            builderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            builderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            builderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        } catch (ParserConfigurationException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }

        builderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        builderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        builderFactory.setXIncludeAware(false);
        builderFactory.setExpandEntityReferences(false);
    }

    private void setParammeters4BuilderFactory(DocumentBuilderFactory factory) {
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(urlXMLFile);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
    }

    public Flowers getFlowers() {
        parseXML();
        return flowers;
    }

    public void parseXML() {
        DocumentBuilderFactory builderFactory = getFactory();
        setParammeters4BuilderFactory(builderFactory);

        NodeList operators = document.getElementsByTagName("flower");
        for (int i = 0; i < operators.getLength(); i++) {
            NodeList nodeList = operators.item(i).getChildNodes();
            flower = new Flower();
            parseNodeList(flower, nodeList);
            flowers.addFlower(flower);
        }
    }

    private <E> void parseNodeList(E element, NodeList nodeList) {
        for (int index = 0; index < nodeList.getLength(); index++) {
            Node node = nodeList.item(index);
            parceNode(element, node);
        }
    }

    private <E> void parceNode(E element, Node node) {
        setAttributes(node);

        String qName = node.getNodeName();
        Field field = getFieldByName(element.getClass(), qName);
        if (field == null) {
            return;
        }

        if (isSimpleTypes(field.getType())) {
            set4SimpleTypeElement(element, qName, node.getTextContent());
        } else {
            set4ComplexTypeElement(element, node);
        }
    }

    private boolean isSimpleTypes(Class<?> clazz) {
        if (clazz == null) return false;

        for (SimpleTypes simpleClazz : SimpleTypes.values()) {
            if (simpleClazz.clazz.equals(clazz))
                return true;
        }
        return false;
    }

    private void setAttributes(Node node) {
        Field field = getFieldByName(flower.getAttributes().getClass(), node.getNodeName());
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

        NamedNodeMap attributes = node.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node atrNode = attributes.item(i);
            valueMap.put(atrNode.getNodeName(), atrNode.getNodeValue());
        }

        set4SimpleTypeElement(flower.getAttributes(), field.getName(), valueMap);
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

    private <E> void set4SimpleTypeElement(E element, String fieldName, Object value) {
        if (value == null || value == "") {
            return;
        }

        Field field = getFieldByName(element.getClass(), fieldName);
        if (field == null) {
            return;
        }

        if (isValidValue4Integer(value.toString()))
            setValueToField(field, element, Integer.valueOf(value.toString()));
        else
            setValueToField(field, element, value);
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

    private <E> void setValueToField(Field field, E element, Object value) {
        try {
            field.set(element, value);
        } catch (IllegalAccessException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
    }


    private <E> void set4ComplexTypeElement(E element, Node node) {
        Field field = getFieldByName(element.getClass(), node.getNodeName());
        if (field==null) {
            return;
        }

        Object object = getValueFromField(field,element);
        parseNodeList(object, node.getChildNodes());
        set4SimpleTypeElement(element, node.getNodeName(), object);
    }

    private boolean isValidValue4Integer(String value) {
        try {
            Integer.valueOf(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
