package practice7.adapter;

import org.junit.Before;
import org.junit.Test;
import practice7.classes.*;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class WriterStAXTest {

    private static final Logger LOG = Logger.getLogger(WriterDOMTest.class.getName());
    private StringBuilder expectedString;
    private final String fileName = "test.output.stax.xml";

    @Before
    public void setUp() {
        Flowers expectedFlowers = new Flowers();
        expectedFlowers = new Flowers();
        Flower flower;
        Attributes attributes;

        flower = new Flower(
                "Rose",
                "дерново-подзолистая",
                "China",
                new VisualParameters("green", "green", 10),
                new GrowingTips(10, 10),
                "черенки");
        attributes = new Attributes();
        attributes.setAveLenFlower(new HashMap<String,String>() {{
            put("measure", "cm");
        }});
        attributes.setTempreture(new HashMap<String,String>() {{
            put("measure", "celcius");
        }});
        attributes.setLighting(new HashMap<String,String>() {{
            put("lightRequiring", "yes");
        }});
        attributes.setWatering(new HashMap<String,String>() {{
            put("measure", "mlPerWeek");
        }});
        flower.setAttributes(attributes);
        expectedFlowers.addFlower(flower);

        WriterStAX writerStAX = new WriterStAX();
        writerStAX.saveToXML(expectedFlowers, fileName);
        expectedString = new StringBuilder();
        expectedString.append("<?xml version=\"1.0\" ?>");
        expectedString.append("<flowers>");
        expectedString.append("<flower>");
        expectedString.append("<name>Rose</name>");
        expectedString.append("<soil>дерново-подзолистая</soil>");
        expectedString.append("<origin>China</origin>");

        expectedString.append("<visualParameters>");
        expectedString.append("<stemColour>green</stemColour>");
        expectedString.append("<leafColour>green</leafColour>");
        expectedString.append("<aveLenFlower measure=\"cm\">10</aveLenFlower>");
        expectedString.append("</visualParameters>");
        expectedString.append("<growingTips>");

        expectedString.append("<tempreture measure=\"celcius\">10</tempreture>");
        expectedString.append("<lighting lightRequiring=\"yes\"></lighting>");
        expectedString.append("<watering measure=\"mlPerWeek\">10</watering>");
        expectedString.append("</growingTips>");
        expectedString.append("<multiplying>черенки</multiplying>");
        expectedString.append("</flower>");
        expectedString.append("</flowers>");
        expectedString.append(System.lineSeparator());
    }

    @Test
    public void equalsWriteContentToXMLByWriterStAX() {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "UTF-8");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();

        } catch (IOException ex) {
            LOG.log(Level.INFO, ex.getMessage());
        }
        File file = new File(fileName);
        file.deleteOnExit();
        assertEquals(expectedString.toString(), sb.toString());
    }

}
