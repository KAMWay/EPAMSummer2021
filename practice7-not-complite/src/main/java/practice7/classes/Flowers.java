package practice7.classes;

import java.util.ArrayList;
import java.util.List;

public class Flowers {

    List<Flower> flowerList;

    public Flowers() {
        this.flowerList = new ArrayList<>();
    }

    public void addFlower(Flower flower) {
        flowerList.add(flower);
    }

    public List<Flower> getFlowerList() {
        return flowerList;
    }

    public void setFlowerList(List<Flower> flowerList) {
        this.flowerList = flowerList;
    }

    @Override
    public String toString() {
        if (this.flowerList == null) return "[]";

        StringBuilder out = new StringBuilder();
        for (Flower flower : flowerList) {
            out.append(flower).append(System.lineSeparator());
        }

        return out.toString();
    }

}