public abstract class Shape {
    protected Style style;

    protected Shape(Style style)
    {
        this.style = style;
    }

    @SuppressWarnings({"unused", ""})
    public void setStyle(Style style) {
        this.style = style;
    }

    @SuppressWarnings({"unused", ""})
    public Style getStyle() {
        return style;
    }

    public abstract String toSvg();
    public abstract BoundingBox boundingBox();
}
