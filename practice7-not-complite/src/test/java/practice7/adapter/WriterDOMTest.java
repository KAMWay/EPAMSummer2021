package practice7.adapter;

import practice7.classes.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class WriterDOMTest {
//
//    private static final Logger LOG = Logger.getLogger(WriterDOMTest.class.getName());
//
//    private StringBuilder expectedString;
//    private final String fileName = "test.output.dom.xml";
//
//    @Before
//    public void setUp() {
//        Flowers expectedFlowers = new Flowers();
//        Flower flower;
//        flower = new Flower(
//                "Rose",
//                "дерново-подзолистая",
//                "China",
//                new VisualParameters("green", "green", 10),
//                new GrowingTips(10, 10),
//                "черенки");
//        expectedFlowers.addFlower(flower);
//        expectedFlowers.addFlower(flower);
//
//        WriterDOM writerDOM = new WriterDOM();
//        writerDOM.saveToXML(expectedFlowers, fileName);
//
//        expectedString = new StringBuilder();
//        expectedString.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>").append(System.lineSeparator());
//        expectedString.append("<flowers>").append(System.lineSeparator());
//        expectedString.append("<flower>").append(System.lineSeparator());
//        expectedString.append("<name>Rose</name>").append(System.lineSeparator());
//        expectedString.append("<soil>дерново-подзолистая</soil>").append(System.lineSeparator());
//        expectedString.append("<origin>China</origin>").append(System.lineSeparator());
//
//        expectedString.append("<visualParameters>").append(System.lineSeparator());
//        expectedString.append("<stemColour>green</stemColour>").append(System.lineSeparator());
//        expectedString.append("<leafColour>green</leafColour>").append(System.lineSeparator());
//        expectedString.append("<aveLenFlower measure=\"cm\">10</aveLenFlower>").append(System.lineSeparator());
//        expectedString.append("</visualParameters>").append(System.lineSeparator());
//        expectedString.append("<growingTips>").append(System.lineSeparator());
//
//        expectedString.append("<tempreture measure=\"celcius\">25</tempreture>").append(System.lineSeparator());
//        expectedString.append("<lighting lightRequiring=\"yes\"/>").append(System.lineSeparator());
//        expectedString.append("<watering measure=\"mlPerWeek\">10</watering>").append(System.lineSeparator());
//        expectedString.append("</growingTips>").append(System.lineSeparator());
//        expectedString.append("<multiplying>черенки</multiplying>").append(System.lineSeparator());
//        expectedString.append("</flower>").append(System.lineSeparator());
//        expectedString.append("</flowers>").append(System.lineSeparator());
//    }
//
//    @Test
//    public void equalsWriteContentToXMLByWriterTAX() {
//        StringBuilder sb = new StringBuilder();
//        try {
//            Scanner scanner = new Scanner(new File(fileName), "UTF-8");
//            while (scanner.hasNextLine()) {
//                sb.append(scanner.nextLine()).append(System.lineSeparator());
//            }
//            scanner.close();
//
//        } catch (IOException ex) {
//            LOG.log(Level.INFO, ex.getMessage());
//        }
//        File file = new File(fileName);
//        file.deleteOnExit();
//        assertEquals(expectedString.toString(), sb.toString());
//    }

}
