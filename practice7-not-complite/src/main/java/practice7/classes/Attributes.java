package practice7.classes;

import java.util.HashMap;
import java.util.Map;

public class Attributes {

    private Map<String, String> aveLenFlower;
    private Map<String, String> tempreture;
    private Map<String, String> lighting;
    private Map<String, String> watering;

    public Attributes() {
        this.aveLenFlower = new HashMap<>();
        this.tempreture = new HashMap<>();
        this.lighting = new HashMap<>();
        this.watering = new HashMap<>();
    }

    public Map<String, String> getAveLenFlower() {
        return aveLenFlower;
    }

    public void setAveLenFlower(Map<String, String> aveLenFlower) {
        this.aveLenFlower = aveLenFlower;
    }

    public Map<String, String> getTempreture() {
        return tempreture;
    }

    public void setTempreture(Map<String, String> tempreture) {
        this.tempreture = tempreture;
    }

    public Map<String, String> getLighting() {
        return lighting;
    }

    public void setLighting(Map<String, String> lighting) {
        this.lighting = lighting;
    }

    public Map<String, String> getWatering() {
        return watering;
    }

    public void setWatering(Map<String, String> watering) {
        this.watering = watering;
    }

    @Override
    public String toString() {
        return "{" +
                "aveLenFlower=" + aveLenFlower +
                ", tempreture=" + tempreture +
                ", lighting=" + lighting +
                ", watering=" + watering +
                '}';
    }
}
