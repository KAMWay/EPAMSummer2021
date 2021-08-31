package practice7.factory;

import practice7.adapter.ReaderDOM;
import practice7.adapter.WriterDOM;
import practice7.adapter.WriterStAX;
import practice7.constants.XMLConstants;
import practice7.classes.Flowers;

public class DOMFactory {

    private Flowers flowers;
    private final ReaderDOM readerDOM;
    private final WriterDOM writerDOM;
    private final WriterStAX writerStAX;

    public DOMFactory(String xmlFilePath) {
        this.readerDOM = new ReaderDOM(xmlFilePath);
        this.writerDOM = new WriterDOM();
        this.writerStAX = new WriterStAX();
        this.flowers = new Flowers();
    }

    public void parseFromXML() {
        flowers = readerDOM.getFlowers();
    }

    public void parseToXML() {
//        writerDOM.saveToXML(flowers, XMLConstants.DOM_RESULT);
        writerStAX.saveToXML(flowers, XMLConstants.DOM_RESULT);
    }


}