package practice7.classes;

public class GrowingTips implements Comparable<GrowingTips> {

    private int tempreture;
    private String lighting;
    private int watering;

    public GrowingTips() {
    }

    public GrowingTips(int tempreture,   int watering) {
        this.tempreture = tempreture;

        this.watering = watering;
    }

    public int getTempreture() {
        return tempreture;
    }

    public void setTempreture(int tempreture) {
        this.tempreture = tempreture;
    }

    public String getLighting() {
        return lighting;
    }

    public void setLighting(String lighting) {
        this.lighting = lighting;
    }

    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    @Override
    public int compareTo(GrowingTips o) {
        int cmp = Integer.compare(this.tempreture, o.tempreture);
        if (cmp != 0) {
            return cmp;
        }

        return Integer.compare(this.watering, o.watering);
    }

    @Override
    public String toString() {
        return "<GrowingTips>" + System.lineSeparator() +
                "tempreture = " + tempreture + System.lineSeparator() +
                "lighting = " + lighting + System.lineSeparator() +
                "watering = " + watering + System.lineSeparator() +
                "</GrowingTips>";
    }
}
