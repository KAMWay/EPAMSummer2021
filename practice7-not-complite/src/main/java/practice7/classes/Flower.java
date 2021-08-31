package practice7.classes;

public class Flower implements Comparable<Flower> {

    private String name;
    private String soil;
    private String origin;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private String multiplying;

    private Attributes attributes;

    public Flower() {
        this.attributes = new Attributes();
        this.visualParameters = new VisualParameters();
        this.growingTips = new GrowingTips();
    }

    public Flower(String name,
                  String soil,
                  String origin,
                  VisualParameters visualParameters,
                  GrowingTips growingTips,
                  String multiplying
    ) {
        this.name = name;
        this.soil = soil;
        this.origin = origin;
        this.visualParameters = visualParameters;
        this.growingTips = growingTips;
        this.multiplying = multiplying;
        this.attributes = new Attributes();
    }

    public String getName1() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public String getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(String multiplying) {
        this.multiplying = multiplying;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public int compareTo(Flower o) {
        int cmp = this.name.compareTo(o.name);
        if (cmp != 0) {
            return cmp;
        }

        cmp = this.soil.compareTo(o.soil);
        if (cmp != 0) {
            return cmp;
        }

        cmp = this.origin.compareTo(o.origin);
        if (cmp != 0) {
            return cmp;
        }

        cmp = this.visualParameters.compareTo(o.visualParameters);
        if (cmp != 0) {
            return cmp;
        }

        cmp = this.growingTips.compareTo(o.growingTips);
        if (cmp != 0) {
            return cmp;
        }

        return this.multiplying.compareTo(o.multiplying);
    }

    @Override
    public String toString() {
        return "name = " + name + System.lineSeparator() +
                "soil = " + soil + System.lineSeparator() +
                "origin = " + origin + System.lineSeparator() +
                visualParameters + System.lineSeparator() +
                growingTips + System.lineSeparator() +
                "multiplying = " + multiplying + System.lineSeparator() +
                "Attributes:" + System.lineSeparator() +
                attributes;
    }

}