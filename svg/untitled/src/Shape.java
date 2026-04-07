public abstract class Shape {
    protected Style style;

    protected Shape(Style style)
    {
        this.style = style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Style getStyle() {
        return style;
    }

    public abstract String toSvg();
    public abstract BoundingBox boundingBox();
}
