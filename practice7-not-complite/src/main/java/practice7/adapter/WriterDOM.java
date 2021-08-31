package practice7.adapter;

import practice7.constants.XMLFowersConstants;
import practice7.classes.Flower;
import practice7.classes.Flowers;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriterDOM {

    private static final Logger LOG = Logger.getLogger(WriterDOM.class.getName());
    private Flowers flowers;
    public WriterDOM() {
    }

    public void setFlowers(Flowers flowers) {
        this.flowers = flowers;
    }

    public void saveToXML(Flowers flowers, String fileName) {
        setFlowers(flowers);

        try {
            StreamResult result = new StreamResult(new File(fileName));

            TransformerFactory tf = TransformerFactory.newInstance();
            javax.xml.transform.Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");

            t.transform(new DOMSource(createDocument()), result);
        } catch (ParserConfigurationException | TransformerException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
    }

    private Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root = document.createElement(XMLFowersConstants.FLOWERS);

        document.appendChild(root);

        for (Flower flower : flowers.getFlowerList()) {
            Element childElement = getChild4Document(document, flower, XMLFowersConstants.FLOWER);
            root.appendChild(childElement);
        }

        return document;
    }

    private <E> Element getChild4Document(Document document, E object, String title) {
        if (object == null) {
            return null;
        }

        Element currElement = document.createElement(title);
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = getFieldValue(field, object);
            Element childElement = getChildElement(document, field, value);
            currElement.appendChild(childElement);
        }

        return currElement;
    }

    private <E> Element getChildElement(Document document, Field field, E object) {
        if (isSimpleTypes(field.getType())) {
            return get4SimpleChild(document, object, field.getName());
        } else {
            return get4ComplexChild(document, object, field);
        }
    }

    private boolean isSimpleTypes(Class<?> clazz) {
        for (SimpleTypes simpleClazz : SimpleTypes.values()) {
            if (simpleClazz.clazz.equals(clazz))
                return true;
        }
        return false;
    }

    private <E> Object getFieldValue(Field field, E object) {
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    private <E> Element get4SimpleChild(Document document, E object, String title) {
        Element childElement = document.createElement(title);
        if (object != null) {
            childElement.setTextContent(object.toString());
        }
        return childElement;
    }

    private <E> Element get4ComplexChild(Document document, E object, Field field) {
//        if (!field.getType().isAnnotationPresent(ToAttribute.class)) {
//            return getChild4Document(document, object, field.getName());
//        }
//
        Element childElement = document.createElement(field.getName());
//        ToAttribute annotation = field.getType().getAnnotation(ToAttribute.class);
//        Class<?> clazz = object.getClass();
//
//        for (Field tmpField : clazz.getDeclaredFields()) {
//            tmpField.setAccessible(true);
//            Object value = getFieldValue(tmpField, object);
//            if (value != null)
//                if (tmpField.getName().equals(annotation.fieldName())) {
//                    childElement.setAttribute(tmpField.getName(), value.toString());
//                } else {
//                    childElement.setTextContent(value.toString());
//                }
//        }
//

        return childElement;
    }

}
