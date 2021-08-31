package practice7.factory;

import practice7.adapter.ReaderSTAX;
import practice7.adapter.WriterDOM;
import practice7.adapter.WriterStAX;
import practice7.constants.XMLConstants;
import practice7.classes.Flowers;

public class STAXFactory {

    private final ReaderSTAX readerSTAX;

    private final WriterStAX writerSTAX;

    private Flowers flowers;

    public STAXFactory(String xmlFilePath) {
        this.readerSTAX = new ReaderSTAX(xmlFilePath);
        this.writerSTAX = new WriterStAX();
        this.flowers = new Flowers();
    }

    public void parseFromXML() {
        flowers = readerSTAX.getFlowers();
    }

    public void parseToXML() {
        writerSTAX.saveToXML(flowers, XMLConstants.STAX_RESULT);
    }

}
