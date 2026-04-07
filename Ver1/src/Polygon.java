public class Polygon extends Shape {
    private final Point[] ps;

    @SuppressWarnings({"unused", ""})
    public Polygon()
    {
        super(new Style("none", "black", 1));
        ps = new Point[0];
    }

    public Polygon(Point[] points, Style style) {
        super(new Style(style));
        Point[] tmp = new Point[points.length];
        for (int i = 0; i < points.length; ++i)
            tmp[i] = new Point(points[i]);
        ps = tmp;
    }
    public Polygon(Point[] points) {
        this(points, new Style("none", "black", 1));
    }
    public Polygon(Polygon pol)
    { this(pol.ps, new Style("none", "black", 1)); }

    @SuppressWarnings({"unused", ""})
    public Point getPoint(int index)
    { return new Point(ps[index]); }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder("Polygon[ ");
        for (int i = 0; i < ps.length; ++i)
            tmp.append(ps[i].toString(true)).append(i < ps.length - 1 ? ", " : " ]");
        return tmp.toString();
    }

    @Override
    public String toSvg()
    {
        //<polygon points="220,10 300,210 170,250 123,234" style="fill:white;stroke:black;stroke-width:1" />
        StringBuilder tmp = new StringBuilder("<polygon points=\"");
        for (int i = 0; i < ps.length; ++i)
            tmp.append(ps[i].getX()).append(",").append(ps[i].getY()).append(i < ps.length - 1 ? " " : "\" " + style.toSvg() + " />");
        return tmp.toString();
    }

    @Override
    public BoundingBox boundingBox()
    {
        float minX = ps[0].getX(), maxX = ps[0].getX(),
              minY = ps[0].getY(), maxY = ps[0].getY();
        for (Point p : ps) {
            if (minX > p.getX())
                minX = p.getX();
            if (maxX < p.getX())
                maxX = p.getX();

            if (minY > p.getY())
                minY = p.getY();
            if (maxY < p.getY())
                maxY = p.getY();
        }
        return new BoundingBox(minX, minY, maxX - minX, maxY - minY);
    }

    public static Polygon Square(Segment intersection)
    {
        var minX = Math.min(intersection.getPoint1().getX(), intersection.getPoint2().getX());
        var maxX = Math.max(intersection.getPoint1().getX(), intersection.getPoint2().getX());

        var minY = Math.min(intersection.getPoint1().getY(), intersection.getPoint2().getY());
        var maxY = Math.max(intersection.getPoint1().getY(), intersection.getPoint2().getY());

        return new Polygon(new Point[] {
                new Point(minX, minY),
                new Point(maxX, minY),
                new Point(maxX, maxY),
                new Point(minX, maxY)
            });
    }
}

