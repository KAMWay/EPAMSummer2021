package practice7.factory;

import practice7.adapter.ReaderSAX;
import practice7.adapter.WriterStAX;
import practice7.constants.XMLConstants;
import practice7.classes.Flowers;

public class SAXFactory {

    private final ReaderSAX readerSAX;

    private final WriterStAX writerStAX;

    private Flowers flowers;

    public SAXFactory(String xmlFilePath) {
        this.readerSAX = new ReaderSAX(xmlFilePath);
        this.writerStAX =new WriterStAX();
        this.flowers = new Flowers();
    }

    public void parseFromXML() {
        flowers = readerSAX.getFlowers();
    }

    public void parseToXML() {
        writerStAX.saveToXML(flowers, XMLConstants.SAX_RESULT);
    }

}
