@SuppressWarnings({"unused", ""})
public class Ellipse extends Shape {
    Point center;
    float radius;

    public Ellipse(Point center, float radius, Style style) {
        super(style);
        this.center = center;
        this.radius = radius;
    }
    public Ellipse(Point center, float radius) {
        this(center, radius, new Style("none", "black", 1));
    }

    @Override
    public String toSvg() {
        return "<ellipse cx=\"" + center.getX() + "\" cy=\"" + center.getX() + "\" rx=\"" +
            radius + "\" ry=\"" + radius + "\" style=\"" + style.toSvg() + "\" />";
    }

    @Override
    public BoundingBox boundingBox() {
        return new BoundingBox(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
    }
}
