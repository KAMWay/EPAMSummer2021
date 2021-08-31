package practice7;

import practice7.factory.DOMFactory;
import practice7.factory.SAXFactory;
import practice7.factory.STAXFactory;

public final class Main {

    public static void main(final String[] args) {
        DOMFactory domController = new DOMFactory(args[0]);
        domController.parseFromXML();
        domController.parseToXML();

        SAXFactory saxController = new SAXFactory(args[0]);
        saxController.parseFromXML();
        saxController.parseToXML();

        STAXFactory staxController = new STAXFactory(args[0]);
        staxController.parseFromXML();
        staxController.parseToXML();
    }

}
