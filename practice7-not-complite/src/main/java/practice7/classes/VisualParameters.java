package practice7.classes;

public class VisualParameters implements Comparable<VisualParameters> {

    private String stemColour;

    private String leafColour;

    private int aveLenFlower;

    public VisualParameters() {
    }

    public VisualParameters(String stemColour, String leafColour, int aveLenFlower) {
        this.stemColour = stemColour;
        this.leafColour = leafColour;
        this.aveLenFlower = aveLenFlower;
    }

    public String getStemColour() {
        return stemColour;
    }

    public void setStemColour(String stemColour) {
        this.stemColour = stemColour;
    }

    public String getLeafColour() {
        return leafColour;
    }

    public void setLeafColour(String leafColour) {
        this.leafColour = leafColour;
    }

    public int getAveLenFlower() {
        return aveLenFlower;
    }

    public void setAveLenFlower(int aveLenFlower) {
        this.aveLenFlower = aveLenFlower;
    }

    @Override
    public int compareTo(VisualParameters o) {
        int cmp = this.stemColour.compareTo(o.stemColour);
        if (cmp != 0) {
            return cmp;
        }

        cmp = this.leafColour.compareTo(o.leafColour);
        if (cmp != 0) {
            return cmp;
        }

        return Integer.compare(this.aveLenFlower, o.aveLenFlower);
    }

    @Override
    public String toString() {
        return "<VisualParameters>" +
                " stemColour = " + stemColour + System.lineSeparator() +
                "leafColour = " + leafColour + System.lineSeparator() +
                "aveLenFlower = " + aveLenFlower + System.lineSeparator() +
                "</VisualParameters>";
    }
}
