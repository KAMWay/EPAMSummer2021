package practice7.adapter;

import practice7.constants.XMLConstants;
import practice7.classes.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ReaderDOMTest {

    private Flowers expectedFlowers;

    @Before
    public void setUp() {
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

        flower = new Flower(
                "Bambusa",
                "дерново-подзолистая",
                "China",
                new VisualParameters("green", "green", 1100),
                new GrowingTips(30, 250),
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
    }

    @Test
    public void equalsReadContentFromXMLByReaderTAX() {
        ReaderDOM readerDOM = new ReaderDOM(XMLConstants.VALID_XML_FILE);
        Flowers flowers = readerDOM.getFlowers();
        Flower f1 = expectedFlowers.getFlowerList().get(1);
        Flower f2 = flowers.getFlowerList().get(0);
        assertEquals(f1.toString(), f2.toString());
    }

}
