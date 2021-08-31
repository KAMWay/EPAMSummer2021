package practice7.adapter;

import practice7.classes.Attributes;
import practice7.constants.XMLFowersConstants;
import practice7.classes.Flower;
import practice7.classes.Flowers;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriterStAX {

    Flowers flowers;
    Attributes attributes;

    private static final Logger LOG = Logger.getLogger(WriterStAX.class.getName());

    public WriterStAX() {

    }

    public void setFlowers(Flowers flowers) {
        this.flowers = flowers;
    }

    public void saveToXML(Flowers flowers, String fileName) {
        setFlowers(flowers);
        flowers.getFlowerList().sort(Flower::compareTo);

        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            XMLStreamWriter writer = factory.createXMLStreamWriter(fileWriter);
            writer.writeStartDocument();
            setXMLAttributes(writer);

            writeToXML(writer);

            writer.writeEndElement();
        } catch (XMLStreamException | IOException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
    }

    private void setXMLAttributes(XMLStreamWriter xmlStreamWriter) {
        try {
            xmlStreamWriter.writeStartElement(XMLFowersConstants.FLOWERS);
//            xmlStreamWriter.writeAttribute("xmlns:tns", "http://www.example.org/input");
//            xmlStreamWriter.writeAttribute("xmlns:xs", "http://www.w3.org/2001/XMLSchema-instance");
//            xmlStreamWriter.writeAttribute("xs:schemaLocation", "http://www.example.org/input input.xsd");
        } catch (XMLStreamException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
    }

    private void writeToXML(XMLStreamWriter writer) throws XMLStreamException {
        for (Flower flower : flowers.getFlowerList()) {
            attributes = flower.getAttributes();
            writerChildToXML(writer, flower, XMLFowersConstants.FLOWER);
        }
    }

    private <E> void writerChildToXML(XMLStreamWriter writer, E object, String title) throws XMLStreamException {
        if (object == null) {
            return;
        }

        writer.writeStartElement(title);
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = getFieldValue(field, object);
            if (!field.getName().equals("attributes")) {
                writeByTypeChild(writer, field, value);
            }
        }
        writer.writeEndElement();
    }

    private <E> Object getFieldValue(Field field, E object) {
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }

        return null;
    }

    private <E> void writeByTypeChild(XMLStreamWriter writer, Field field, E object) throws XMLStreamException {
        if (isSimpleTypes(field.getType())) {
            writer.writeStartElement(field.getName());
            writeAttributes(writer, field);

            writeSimpleChild(writer, object);

            writer.writeEndElement();
        } else {
            writerChildToXML(writer, object, field.getName());
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

    private void writeAttributes(XMLStreamWriter writer, Field field) {
        Map<String, String> mapAttributes = getAttributesMap(field.getName());
        if (mapAttributes == null) {
            return;
        }

        for (Map.Entry<String, String> entry : mapAttributes.entrySet()) {
            try {
                writer.writeAttribute(entry.getKey(), entry.getValue());
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
    }

    private Map<String, String> getAttributesMap(String fieldName) {
        Field attributeField = getFieldByName(attributes.getClass(), fieldName);
        if (attributeField == null) {
            return null;
        }

        try {
            return (Map<String, String>) getFieldValue(attributeField, attributes);
        } catch (Exception e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
        return null;
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

    private void writeSimpleChild(XMLStreamWriter writer, Object object) throws XMLStreamException {
        if (object != null)
            writer.writeCharacters(object.toString());
    }
}
